package com.seat.UI.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.seat.R;
import com.seat.Utils.UtilActivitiesNavigation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Devrath on 02/10/16.
 */

public class ActMessage extends AppCompatActivity {


    @BindView(R.id.txt_notification)
    TextView txt_notification;

    @BindView(R.id.btnLoginId)
    Button btnLoginId;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_message);
        //Inject views from butter-knife
        ButterKnife.bind(this);
        //Get data from previous screen
        getDataFromPrevScreen();

    }

    @OnClick(R.id.btnLoginId)
    public void login() {
        UtilActivitiesNavigation.startActivityWithBackStackClear(ActMessage.this, ActLogin.class);
        finish();
    }

    @OnClick(R.id.btn_reg_id)
    public void register() {
        UtilActivitiesNavigation.startActivityWithBackStackClear(ActMessage.this, ActRegistration.class);
        finish();
    }




    private void getDataFromPrevScreen() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mMsg = extras.getString("message");
            txt_notification.setText(mMsg);
           /* boolean isPointToLogin = extras.getBoolean("isPointToLogin",false);
            if(isPointToLogin==true){

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 3 seconds
                        UtilActivitiesNavigation.startActivityWithBackStackClear(ActMessage.this, ActRegSignin.class);
                    }
                }, 3000);

            }*/
        }

    }


}
