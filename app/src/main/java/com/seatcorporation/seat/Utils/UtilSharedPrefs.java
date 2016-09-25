package com.seatcorporation.seat.Utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.seatcorporation.seat.ApplicationClass.AppController;

/**
 * Created by Devrath on 10-09-2016.
 */
public class UtilSharedPrefs {

    public static Activity context;

    /*********************************************Network Check*********************************/
    public static void clearSharedPreferences(Activity _context) {
        context=_context;
        SharedPreferences.Editor editor = context.getSharedPreferences(AppController.getPackageNameForRef(), context.MODE_PRIVATE).edit();
        editor.clear().commit();
    }
    /*********************************************Network Check*********************************/


}
