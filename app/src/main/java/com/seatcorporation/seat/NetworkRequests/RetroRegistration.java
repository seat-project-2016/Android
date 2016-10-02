package com.seatcorporation.seat.NetworkRequests;

import android.util.Log;

import com.seatcorporation.seat.Models.FinalMasterData;
import com.seatcorporation.seat.Models.ResponseData;
import com.seatcorporation.seat.Pages.Home.Interfaces.InterMasterData;
import com.seatcorporation.seat.Retrofit.ApiClient;
import com.seatcorporation.seat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Devrath on 27/09/16.
 */

public class RetroRegistration {

    private InterMasterData listener;
    FinalMasterData mdata;

    public RetroRegistration(InterMasterData listener, FinalMasterData mdata){
        this.listener=listener;
        this.mdata=mdata;
    }

    public void serverCall() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseData> call = apiService.masterData(mdata);

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseData mData=response.body();

                if(mData==null){
                    failureDataResponse();
                }else{
                    if(response.isSuccessful()){
                        successDataResponse(mData,mData.getToken());
                    }else{
                        failureDataResponse();
                    }
                }

            }

            private void failureDataResponse() {
                listener.registrationFailure();
            }

            private void successDataResponse(ResponseData mBundleData,String mTolken) {

                if(mBundleData.getErrorCode()==201){
                    //Already Registered
                    listener.isNewUser(false,mBundleData);
                }else if(mBundleData.getErrorCode()==200){
                    //Registration Successful
                    listener.setLocalData(mTolken);
                    listener.isNewUser(true,mBundleData);
                }

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                failureDataResponse();
            }

        });
    }
}