package com.seatcorporation.seat.ApplicationClass;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Devrath on 10-09-2016.
 */
public class AppController extends Application {

    public static String PACKAGE_NAME;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();
    public static String ACTIVIY_NAME;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        mInstance = this;////
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Fabric.with(this, new Crashlytics());
        //Set the package name
        PACKAGE_NAME = getApplicationContext().getPackageName();
        setPackageNameForRef(PACKAGE_NAME);

    }

    public static String getPackageNameForRef() {
        return PACKAGE_NAME;
    }

    public static void setPackageNameForRef(String packageName) {
        PACKAGE_NAME = packageName;
    }

    public static SharedPreferences getSharedPreferences() {
        return mInstance.getSharedPreferences(AppController.getPackageNameForRef(), Context.MODE_PRIVATE);
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static String getActiviyName() {
        return ACTIVIY_NAME;
    }

    public static void setActiviyName(String activiyName) {
        ACTIVIY_NAME = activiyName;
    }


}
