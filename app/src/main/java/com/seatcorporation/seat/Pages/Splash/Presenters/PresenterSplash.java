package com.seatcorporation.seat.Pages.Splash.Presenters;


import com.seatcorporation.seat.ApplicationClass.AppController;
import com.seatcorporation.seat.Constants.ConstantsSharedPreferences;
import com.seatcorporation.seat.Models.UserRegdData;
import com.seatcorporation.seat.NetworkRequests.RetroCheckRegistered;
import com.seatcorporation.seat.Pages.Splash.Interfaces.IntSplashView;
import com.seatcorporation.seat.Pages.Splash.Validations.ValSplash;
import com.seatcorporation.seat.UI.Activities.ActSplash;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterSplash  {

    private IntSplashView view;
    ValSplash mValSplash;

    public PresenterSplash(ActSplash view) {

        //Set the user view
        this.view=view;
        //Validation instance initilization
        mValSplash=new ValSplash();
        //Presentation Logic
        initPresenter();

    }

    private void initPresenter() {
        try{
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    view.splashSuccess();
                }
            }, 5000);
        }catch (Exception ex){
            ex.printStackTrace();
            view.splashFailure(ex.getMessage());
        }

    }


    public boolean isRegChkToBeDone() {

        return mValSplash.shouldRegCheckToBeDone(AppController.getSharedPreferences().getString(ConstantsSharedPreferences.STRING_PHONE_NUMBER,null),
                                        AppController.getSharedPreferences().getString(ConstantsSharedPreferences.STRING_AUTH_TOLKEN,null));

    }

    public void isUserRegistered(){

        UserRegdData mData=new UserRegdData();
        mData.setAuthtoken(AppController.getSharedPreferences().getString(ConstantsSharedPreferences.STRING_AUTH_TOLKEN,null));
        mData.setPhone_number(AppController.getSharedPreferences().getString(ConstantsSharedPreferences.STRING_PHONE_NUMBER,null));

        RetroCheckRegistered isUsrRgd=new RetroCheckRegistered(PresenterSplash.this,mData);
        isUsrRgd.serverCall();

    }
    public void failureDataResponse() {
        view.registeredCheckFailure();
    }

    public void successDataResponse(int mCode,String mMsg) {
        if(mCode==200){
            //Registration Successful
            view.registeredCheckSuccess(true,mMsg);
        }else{
            view.registeredCheckFailure();
        }

    }



}