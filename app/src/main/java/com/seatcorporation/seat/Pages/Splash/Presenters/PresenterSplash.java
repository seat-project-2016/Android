package com.seatcorporation.seat.Pages.Splash.Presenters;


import com.seatcorporation.seat.ApplicationClass.AppController;
import com.seatcorporation.seat.Constants.ConstantsSharedPreferences;
import com.seatcorporation.seat.Pages.Splash.Interfaces.IntSplashView;
import com.seatcorporation.seat.UI.Activities.ActSplash;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterSplash  {

    private IntSplashView view;

    public PresenterSplash(ActSplash view) {

        //Set the user view
        this.view=view;
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

    public void isUserRegistered(){
        if(AppController.getSharedPreferences().getBoolean(ConstantsSharedPreferences.BOOL_IS_USER_REGISTERED,false)){
            //Existing User
            view.isUserRegestered(true);
        }else{
            //New user
            view.isUserRegestered(false);
        }

    }


}