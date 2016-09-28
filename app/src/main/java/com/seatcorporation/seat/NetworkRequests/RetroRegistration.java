package com.seatcorporation.seat.NetworkRequests;

import android.os.Bundle;
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
    String mCode;
    FinalMasterData mdata;

    public RetroRegistration(InterMasterData listener, FinalMasterData mdata){
        this.listener=listener;
        this.mdata=mdata;
        serverCallForOtp(mdata);
    }

    public void serverCallForOtp(FinalMasterData mdata) {

        final Bundle bundle = new Bundle();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<ResponseData> call = apiService.masterData(mdata.getPhone_number(),mdata.getName(),mdata.getDevice_id(),mdata.getOs_type(),mdata.getDocuments());


        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseData mData=response.body();

                if(mData==null){
                    failureDataResponse();
                }else{
                    if(response.isSuccessful()){
                        successDataResponse(mData,true);
                    }else{
                        failureDataResponse();
                    }
                }



            }

            private void failureDataResponse() {
                listener.registrationFailure();
            }

            private void successDataResponse(ResponseData mBundleData,boolean isSuccess) {

                if(mBundleData.getErrorCode()==201){
                    //Already Registered
                    listener.isNewUser(false);
                }else if(mBundleData.getErrorCode()==200){
                    //Registration Successful
                    listener.isNewUser(true);
                }

                bundle.putParcelable("data", mBundleData);
                bundle.putBoolean("isSuccess", isSuccess);
                listener.masterData(bundle);


            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                failureDataResponse();
            }


        });


    }

}