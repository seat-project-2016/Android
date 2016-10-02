package com.seatcorporation.seat.Pages.Home.Interfaces;

import com.seatcorporation.seat.Models.ResponseData;

/**
 * Created by Devrath on 27-09-2016.
 */

public interface InterMasterData {

    public void isNewUser(boolean isNewUser, ResponseData mData);

    public void registrationFailure();

    public void setLocalData(String mTolken);

}
