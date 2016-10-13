package com.seat.NetworkRequests;

import android.util.Log;

import com.seat.Models.LoginData;
import com.seat.Models.ResponseData;
import com.seat.Pages.Login.Presenters.PresenterLogin;
import com.seat.Retrofit.ApiClient;
import com.seat.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Devrath on 13/10/16.
 */

public class RetroLogin {


    private PresenterLogin listener;
    LoginData mdata;

    public RetroLogin(PresenterLogin listener, LoginData mdata){
        this.listener=listener;
        this.mdata=mdata;
    }

    public void serverCall() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ResponseData> call = apiService.loginData(mdata);

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
                listener.loginFailure();
            }

            private void successDataResponse(ResponseData mBundleData,String mTolken) {

                listener.loginResonse(mBundleData.getErrorMessage(),mBundleData.getErrorCode());

            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                failureDataResponse();
            }

        });
    }


}
