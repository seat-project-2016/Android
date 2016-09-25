package com.seatcorporation.seat.Pages.Registration.Validations;


import android.util.Patterns;

import com.google.common.base.Strings;

/**
 * Created by Devrath on 9/5/2016.
 */
public class ValRegistration {

    public ValRegistration(){
    }

    public boolean validateRegistrationEmail(String mEmail){
        //To check whether the input is email
        return Strings.isNullOrEmpty(mEmail);
    }
    public boolean validateRegistrationPhone(String mPhone){
        //To check whether password is null or empty
        return Patterns.PHONE.matcher(mPhone).matches();
    }
    public boolean validateLoginPasswordLessThanTenChars(String mPassword){
        if(mPassword.length()<10){
            //Password is less than ten
            return true;
        }else{
            //Password is not less than ten
            return false;
        }
    }

}
