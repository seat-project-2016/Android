package com.seatcorporation.seat.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Devrath on 10-09-2016.
 */
public class UtilActivitiesNavigation {


    /**********************************StartActivity ******************************/
    public static void startActivityWithBackStackClear(Activity mSourceActivity, Class<?> mDestinationActivity) {
        Intent mIntent  = new Intent(mSourceActivity,mDestinationActivity);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mSourceActivity.startActivity(mIntent);
        mSourceActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    /**********************************StartActivity ******************************/

    /**********************************StartActivity ******************************/
    public static void startActivityWithClassDataWithBackStackClear(Activity mSourceActivity, Class<?> mDestinationActivity, Bundle mDataToBeSent) {
        Intent mIntent  = new Intent(mSourceActivity,mDestinationActivity);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mIntent.putExtra("android.intent.extra.INTENT", mDataToBeSent);
        mSourceActivity.startActivity(mIntent);
        mSourceActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
    /**********************************StartActivity ******************************/



}
