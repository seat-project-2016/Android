package com.seatcorporation.seat.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.seatcorporation.seat.Pages.Registration.Interfaces.IntRegistrationView;
import com.seatcorporation.seat.Pages.Registration.Presenters.PresenterRegistration;
import com.seatcorporation.seat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Devrath on 10-09-2016.
 */
public class ActRegistration extends AppCompatActivity implements IntRegistrationView {

    @BindView(R.id.btn_login) Button btn_login;
    @BindView(R.id.edt_name_id) EditText edt_name_id;
    @BindView(R.id.edt_phone_id) EditText edt_phone_id;
    @BindView(R.id.rootId) LinearLayout rootId;



    PresenterRegistration presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_registration);
        //Inject views from butter-knife
        ButterKnife.bind(this);

        presenter=new PresenterRegistration(this);
        presenter.initPresenter(edt_phone_id);
    }


    @OnClick(R.id.btn_login)
    public void registration() {
         //SignUp to server
        presenter.attemptRegister(edt_name_id.getText().toString(),
                edt_phone_id.getText().toString());
    }


    @Override
    public void registrationSuccess() {
        //startActivity(new Intent(getApplicationContext(), ActHome.class));
    }

    @Override
    public void registrationFailure() {

    }

    @Override
    public void validationEmailFailure() {
        edt_name_id.setError("Please enter correct name");
    }

    @Override
    public void validationPasswordFailure() {
        edt_phone_id.setError("Please enter correct phone number");
    }

    @Override
    public void validationPasswordSuccess() {
        presenter.storeCredentials(edt_name_id.getText().toString(),edt_phone_id.getText().toString());
        startActivity(new Intent(getApplicationContext(), ActHome.class));
    }
}
