package com.seat.Pages.Login.Presenters;


import android.app.Activity;
import android.app.ProgressDialog;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import com.seat.Models.LoginData;
import com.seat.NetworkRequests.RetroLogin;
import com.seat.Pages.Login.Interfaces.IntLoginView;
import com.seat.Pages.Login.Validations.ValLogin;
import com.seat.R;
import com.seat.UI.Activities.ActLogin;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterLogin {

    private IntLoginView view;
    private ValLogin mVal;
    private String blockCharacterSet = "~#^|$%&*!";
    ProgressDialog pd;
    Activity mActivity;

    public PresenterLogin(ActLogin view) {

        //Set the user view
        this.view=view;
        mActivity=view;
        //validation Logic
        mVal=new ValLogin();
        setUpProgress();

    }

    public void setUpProgress(){
        pd = new ProgressDialog(mActivity);
        pd.setMessage(mActivity.getResources().getString(R.string.txt_loading));
    }

    public void initPresenter(EditText edt_phone_id) {
        //edt_phone_id.setFilters(new InputFilter[] { filter });
    }


    private InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    public void attemptRegister(String number, String password) {

        if(number!=null && password !=null && number.length()>0 && password.length()>0){
            //Password input is correct
            view.validationPasswordSuccess();
        }

        /*if(mVal.validateRegistrationEmail(mail)){
            //Email input is not correct
            view.validationEmailFailure();
        }else{
            if(mVal.validateLoginPasswordLessThanTenChars(phone)){
                //Password input is correct
                view.validationPasswordFailure();
            }else{

            }

        }*/


    }

    public void login(String mName,String mNumber){

        //Continue with the flow
        RetroLogin mNetworkData=new RetroLogin(PresenterLogin.this,prepareData(mName,mNumber));
        pd.show();
        mNetworkData.serverCall();



    }

    public void loginResonse(String mMsg,int mCode) {
        pd.dismiss();
        view.loginSuccess(mMsg,mCode);
    }

    public void loginFailure() {
        pd.dismiss();
        view.loginFailure();
    }


    public LoginData prepareData(String mName,String mNumber){

        LoginData mData=new LoginData(mNumber,mName);
        return mData;

    }


}
