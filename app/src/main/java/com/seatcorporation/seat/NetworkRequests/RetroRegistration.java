package com.seatcorporation.seat.NetworkRequests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.seatcorporation.seat.ApplicationClass.AppController;
import com.seatcorporation.seat.Pages.Home.Interfaces.InterMasterData;

/**
 * Created by Devrath on 27/09/16.
 */

public class RetroRegistration {

    private InterMasterData listener;
    private Activity mApplicationContext;
    private View mRootId;
    String mCode;

    public RetroRegistration(InterMasterData listener, View rootId, Activity c){
        this.listener=listener;
        mApplicationContext=c;
        mRootId=rootId;
        SharedPreferences sharedPref = mApplicationContext.getSharedPreferences(AppController.PACKAGE_NAME, Context.MODE_PRIVATE);
        //serverCallForOtp();
    }

    /*public void serverCallForOtp(String ) {

        final Bundle bundle = new Bundle();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<MasterData> call = apiService.masterData(mCode);


        call.enqueue(new Callback<MasterData>() {
            @Override
            public void onResponse(Call<MasterData> call, Response<MasterData> response) {
                MasterData mData=response.body();

                if(response.isSuccessful()){
                    successDataResponse(mData,true);
                }else{
                    failureDataResponse();
                }

            }

            private void failureDataResponse() {
                Bundle bundle = new Bundle();
                bundle.putBoolean("isSuccess", false);
                listener.masterData(bundle);
            }

            private void successDataResponse(MasterData mBundleData,boolean isSuccess) {
                bundle.putParcelable("data", mBundleData);
                bundle.putBoolean("isSuccess", isSuccess);
                listener.masterData(bundle);
            }

            @Override
            public void onFailure(Call<MasterData> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                failureDataResponse();
            }


        });


    }*/

}