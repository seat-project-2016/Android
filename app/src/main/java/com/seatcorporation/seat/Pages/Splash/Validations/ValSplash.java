package com.seatcorporation.seat.Pages.Splash.Validations;


/**
 * Created by Devrath on 9/5/2016.
 */
public class ValSplash {

    public boolean shouldRegCheckToBeDone(String mPhone,String mTolken) {
        //Check if any of data is not present

        if(mPhone !=null && mTolken !=null &&
                mPhone.length()>0 && mTolken.length()>0){
            return true;
        }
        return false;
    }


}
