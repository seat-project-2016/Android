package com.seatcorporation.seat.NetworkRequests;

import android.util.Log;

import com.seatcorporation.seat.Models.ResponseUserRegd;
import com.seatcorporation.seat.Models.UserRegdData;
import com.seatcorporation.seat.Pages.Splash.Presenters.PresenterSplash;
import com.seatcorporation.seat.Retrofit.ApiClient;
import com.seatcorporation.seat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Devrath on 02/10/16.
 */

public class RetroCheckRegistered {

    private PresenterSplash listener;
    UserRegdData mdata;

    public RetroCheckRegistered(PresenterSplash listener, UserRegdData mdata){
        this.listener=listener;
        this.mdata=mdata;
    }

    public void serverCall() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseUserRegd> call = apiService.masterUserRegd(mdata);

        call.enqueue(new Callback<ResponseUserRegd>() {
            @Override
            public void onResponse(Call<ResponseUserRegd> call, Response<ResponseUserRegd> response) {
                ResponseUserRegd mData=response.body();

                if(mData==null){
                    failureDataResponse();
                }else{
                    if(response.isSuccessful()){
                        successDataResponse(mData.getErrorCode(),mData.getErrorMessage());
                    }else{
                        failureDataResponse();
                    }
                }

            }

            private void failureDataResponse() {
                listener.failureDataResponse();
            }

            private void successDataResponse(int mCode,String mMsg) {

                if(mCode==200){
                    //Registration Successful
                    listener.successDataResponse(mCode,mMsg);
                }else{
                    listener.failureDataResponse();
                }

            }

            @Override
            public void onFailure(Call<ResponseUserRegd> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                failureDataResponse();
            }

        });
    }

}
