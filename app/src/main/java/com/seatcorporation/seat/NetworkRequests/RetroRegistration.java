package com.seatcorporation.seat.NetworkRequests;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.seatcorporation.seat.ApplicationClass.AppController;
import com.seatcorporation.seat.Models.MasterData;
import com.seatcorporation.seat.Retrofit.ApiClient;
import com.seatcorporation.seat.Retrofit.ApiInterface;
import com.seatcorporation.seat.Utils.CommonFunctions;
import com.seatcorporation.seat.Utils.UtilSnackbar;

import retrofit2.Call;

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
        Log.d("<-AUTH-CODE->", sharedPref.getString(Constants.STR_AUTH_CODE, null));
        mCode=sharedPref.getString(Constants.STR_AUTH_CODE, null);
        serverCallForOtp();
    }

    public void serverCallForOtp() {

        final Bundle bundle = new Bundle();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MasterData> call = apiService.MasterData(mCode);


        call.enqueue(new Callback<MasterData>() {
            @Override
            public void onResponse(Call<MasterData> call, Response<MasterData> response) {
                MasterData mData=response.;

                if(mData.getStatusCode()==Constants.STATUS_CODE_AUTHCODE_EXPIRED){
                    //AuthCodeExpired
                    CommonFunctions.clearSharedPreferences(mApplicationContext);
                    CommonFunctions.startActivityWithBackStackClear(mApplicationContext,ActPhoneRegistration.class);
                }else{
                    //ValidRequest
                    if (response.isSuccessful() == true) {
                        //Server call successful
                        if (response.body().isSuccess() == true) {
                            if (response.body().getStatusCode() == Constants.STATUS_CODE_INT_AUTHENTICATION_FAILED) {
                                Log.d("SUCCESS", "AUTHENTICATION_FAILED");
                                bundleDataToBeReturned(mData, false);
                                UtilSnackbar.showSnakbarTypeOne(mRootId, "AUTH_FAILED");
                            } else  if (response.body().getStatusCode() == Constants.STATUS_CODE_INT_DOC_CASES_API_ERROR) {
                                Log.d("SUCCESS", "API_ERROR");
                                bundleDataToBeReturned(mData, false);
                                UtilSnackbar.showSnakbarTypeOne(mRootId, "API_ERROR");
                            } else if (response.body().getStatusCode() == Constants.STATUS_CODE_INT_SUCCESS) {
                                //New user
                                Log.d("SUCCESS", "DOC_CASES_SUCCESSFUL");
                                bundleDataToBeReturned(mData,true);
                                //UtilSnackbar.showSnakbarTypeOne(mRootId, "SUCCESS");
                            } else if (response.body().getStatusCode() == Constants.STATUS_CODE_INT_OTP_VERIFY_NOT_SUCCESS) {
                                //Existing User
                                Log.d("FAILURE", "DOC_CASES_NOT_SUCCESSFUL");
                                UtilSnackbar.showSnakbarTypeOne(mRootId, response.body().getMessage());
                                bundleDataToBeReturned(mData, false);
                            } else if (response.body().getStatusCode() == Constants.STATUS_CODE_INT_NOT_FOUND) {
                                //Existing User
                                Log.d("FAILURE", "DOC_CASES_NOT_SUCCESSFUL");
                                //UtilSnackbar.showSnakbarTypeOne(mRootId, response.body().getMessage());
                                bundleDataToBeReturned(mData, true);
                            }

                        } else if (response.body().isSuccess() == false){
                            Log.d("FAILURE", "DOC_CASES_NOT_SUCCESSFUL");
                            UtilSnackbar.showSnakbarTypeOne(mRootId, response.body().getMessage());
                            bundleDataToBeReturned(mData, false);
                        }
                    } else {
                        //Server call not successful
                        Log.d("FAILURE", "SERVER_CALL_FAILURE");
                        UtilSnackbar.showSnakbarTypeOne(mRootId, "ServerCallFailure");
                        bundleDataToBeReturned(mData, false);
                    }
                }

            }

            private void bundleDataToBeReturned(MasterData mBundleData,boolean isSuccess) {
                bundle.putParcelable("data", mBundleData);
                bundle.putBoolean("isSuccess", isSuccess);
                listener.MasterDataSuccess(bundle);
            }

            @Override
            public void onFailure(Call<MasterData> call, Throwable t) {
                Log.d("FAILURE", "SERVER_CALL_FAILURE");
                UtilSnackbar.showSnakbarTypeOne(mRootId, "ServerCallFailure");

                Bundle bundle = new Bundle();
                bundle.putBoolean("isSuccess", false);
                listener.MasterDataSuccess(bundle);

            }


        });


    }

}
