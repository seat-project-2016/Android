package com.seatcorporation.seat.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.seatcorporation.seat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Devrath on 02/10/16.
 */

public class ActMessage extends AppCompatActivity {


    @BindView(R.id.txt_notification)
    TextView txt_notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_message);
        //Inject views from butter-knife
        ButterKnife.bind(this);
        //Get data from previous screen
        getDataFromPrevScreen();

    }

    private void getDataFromPrevScreen() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mMsg = extras.getString("message");
            txt_notification.setText(mMsg);
        }
    }


}
