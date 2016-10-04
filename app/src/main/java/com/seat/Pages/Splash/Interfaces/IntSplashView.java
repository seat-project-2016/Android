package com.seat.Pages.Splash.Interfaces;

/**
 * Created by Devrath on 10-09-2016.
 */
public interface IntSplashView {
    void splashSuccess();
    void splashFailure(String message);
    void registeredCheckSuccess(boolean mVal,String mMsg);
    void registeredCheckFailure();

}
