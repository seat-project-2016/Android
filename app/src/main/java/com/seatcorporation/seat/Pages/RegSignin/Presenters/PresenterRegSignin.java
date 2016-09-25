package com.seatcorporation.seat.Pages.RegSignin.Presenters;


import android.widget.ImageView;

import com.seatcorporation.seat.Pages.RegSignin.Interfaces.IntRegSigninView;
import com.seatcorporation.seat.R;
import com.seatcorporation.seat.UI.Activities.ActRegSignin;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterRegSignin {

    private IntRegSigninView view;
    CarouselView carouselView;

    int[] sampleImages = {R.color.black, R.color.black, R.color.black, R.color.black, R.color.black};


    public PresenterRegSignin(ActRegSignin view, CarouselView carouselView) {

        //Set the user view
        this.view=view;
        this.carouselView=carouselView;
        //Presentation Logic
        initPresenter();


    }

    private void initPresenter() {

        setCourouselListener();

    }

    private void setCourouselListener() {
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(sampleImages[position]);

            view.loadImage(sampleImages[position],imageView);

        }
    };

}
