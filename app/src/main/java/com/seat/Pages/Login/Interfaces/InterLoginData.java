package com.seat.Pages.Login.Interfaces;

import com.seat.Models.ResponseData;

/**
 * Created by Devrath on 13/10/16.
 */

public interface InterLoginData {

    void isNewUser(boolean isNewUser, ResponseData mData);

    void registrationFailure();

    void setLocalData(String mTolken);

}
