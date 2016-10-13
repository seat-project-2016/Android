package com.seat.UI.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.seat.Pages.Login.Interfaces.IntLoginView;
import com.seat.Pages.Login.Presenters.PresenterLogin;
import com.seat.R;
import com.seat.Utils.UtilActivitiesNavigation;
import com.seat.Utils.UtilSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Devrath on 12/10/16.
 */

public class ActLogin extends AppCompatActivity implements IntLoginView {

    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.edt_name_id)
    EditText edt_name_id;
    @BindView(R.id.edt_phone_id) EditText edt_phone_id;
    @BindView(R.id.rootId)
    LinearLayout rootId;



    PresenterLogin presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        //Inject views from butter-knife
        ButterKnife.bind(this);

        presenter=new PresenterLogin(this);
        presenter.initPresenter(edt_phone_id);
    }


    @OnClick(R.id.btn_login)
    public void registration() {
        //SignUp to server
        presenter.attemptRegister(edt_name_id.getText().toString(),
                edt_phone_id.getText().toString());
    }


    @Override
    public void loginSuccess(String mMsg,int mCode) {

        if(mCode==200){
            UtilActivitiesNavigation.startActivityWithBackStackClear(ActLogin.this, ActHome.class);
        }else if(mCode==201){
            UtilSnackbar.showSnakbarTypeOne(rootId, mMsg);
        }


    }

    @Override
    public void loginFailure() {

        UtilSnackbar.showSnakbarTypeOne(rootId, getResources().getString(R.string.login_failure));

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
        /*presenter.storeCredentials(edt_name_id.getText().toString(),edt_phone_id.getText().toString());
        startActivity(new Intent(getApplicationContext(), ActHome.class));*/

        presenter.login(edt_name_id.getText().toString(),
                        edt_phone_id.getText().toString());
    }
}