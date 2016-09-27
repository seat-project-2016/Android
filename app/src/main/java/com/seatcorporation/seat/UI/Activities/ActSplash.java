package com.seatcorporation.seat.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.seatcorporation.seat.Pages.Splash.Interfaces.IntSplashView;
import com.seatcorporation.seat.Pages.Splash.Presenters.PresenterSplash;
import com.seatcorporation.seat.R;
import com.seatcorporation.seat.Utils.UtilActivitiesNavigation;
import com.seatcorporation.seat.Utils.UtilSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActSplash extends AppCompatActivity implements IntSplashView {


    @BindView(R.id.rootView)
    RelativeLayout rootView;

    PresenterSplash presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        //Inject views from butter-knife
        ButterKnife.bind(this);
        //Presentation Logic
        presenter=new PresenterSplash(this);
        //Toast.makeText(ActSplash.this,"10-Second Magic",Toast.LENGTH_LONG).show();
    }

    @Override
    public void splashSuccess() {
        //Proceed to next screen
        presenter.isUserRegistered();
        /*UtilActivitiesNavigation.startActivityWithBackStackClear(ActSplash.this, ActRegSignin.class);
        finish();*/
    }

    @Override
    public void splashFailure(String message) {
        //Stay in the current screen
        UtilSnackbar.showSnakbarTypeOne(rootView,message);
    }

    @Override
    public void isUserRegestered(boolean isRegd) {
        if(isRegd){
            //Existing User
            UtilActivitiesNavigation.startActivityWithBackStackClear(ActSplash.this, ActHome.class);
        }else{
            //New User
            UtilActivitiesNavigation.startActivityWithBackStackClear(ActSplash.this, ActRegSignin.class);
        }
    }


}
