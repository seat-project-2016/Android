package com.seat.Pages.Home.Interfaces;

import com.seat.Models.ResponseData;

/**
 * Created by Devrath on 27-09-2016.
 */

public interface InterMasterData {

    void isNewUser(boolean isNewUser, ResponseData mData);

    void registrationFailure();

    void setLocalData(String mTolken);

}
