package com.seat.Pages.RegSignin.Interfaces;

import android.widget.ImageView;

/**
 * Created by Devrath on 10-09-2016.
 */
public interface IntRegSigninView {
    void registrationSuccess();
    void registrationFailure();
    void loadImage(int sampleImage, ImageView imageView);
}
