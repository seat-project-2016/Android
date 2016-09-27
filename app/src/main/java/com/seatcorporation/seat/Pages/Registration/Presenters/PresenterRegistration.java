package com.seatcorporation.seat.Pages.Registration.Presenters;


import com.seatcorporation.seat.ApplicationClass.AppController;
import com.seatcorporation.seat.Constants.ConstantsSharedPreferences;
import com.seatcorporation.seat.Pages.Registration.Interfaces.IntRegistrationView;
import com.seatcorporation.seat.Pages.Registration.Validations.ValRegistration;
import com.seatcorporation.seat.UI.Activities.ActRegistration;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterRegistration {

    private IntRegistrationView view;
    private ValRegistration mVal;

    public PresenterRegistration(ActRegistration view) {

        //Set the user view
        this.view=view;
        //Presentation Logic
        initPresenter();
        //validation Logic
        mVal=new ValRegistration();

    }

    private void initPresenter() {

    }


    public void attemptRegister(String mail, String phone) {
        if(mVal.validateRegistrationEmail(mail)){
            //Email input is not correct
            view.validationEmailFailure();
        }else{
            if(mVal.validateLoginPasswordLessThanTenChars(phone)){
                //Password input is correct
                view.validationPasswordFailure();
            }else{
                //Password input is correct
                view.validationPasswordSuccess();
            }

        }


    }


    public void storeCredentials(String mName, String mNumber) {
        AppController.getSharedPreferences().edit().putString(ConstantsSharedPreferences.STRING_USER_NAME,mName).commit();
        AppController.getSharedPreferences().edit().putString(ConstantsSharedPreferences.STRING_PHONE_NUMBER,mNumber).commit();
    }
}
