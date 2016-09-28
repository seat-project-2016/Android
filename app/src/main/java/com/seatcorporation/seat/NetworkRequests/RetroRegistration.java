package com.seatcorporation.seat.NetworkRequests;

import android.os.Bundle;
import android.util.Log;

import com.seatcorporation.seat.Models.FinalMasterData;
import com.seatcorporation.seat.Models.ResponseData;
import com.seatcorporation.seat.Pages.Home.Interfaces.InterMasterData;
import com.seatcorporation.seat.Retrofit.ApiClient;
import com.seatcorporation.seat.Retrofit.ApiInterface;

import java.util.HashMap;

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

        HashMap<String,String> mMap=new HashMap<>();
        mMap.put("phone_number",mdata.getPhone_number());
        mMap.put("name",mdata.getName());
        mMap.put("device_id",mdata.getDevice_id());
        mMap.put("os_type",mdata.getOs_type());
        mMap.put("documents",mdata.getDocuments());

        Call<ResponseData> call = apiService.masterData(mMap);


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