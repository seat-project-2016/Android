package com.seatcorporation.seat.Pages.Home.Interfaces;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.seatcorporation.seat.Models.ResponseData;
import com.seatcorporation.seat.Pages.Home.Adapters.Adpt_home;
import com.seatcorporation.seat.Pages.Home.Adapters.AdptDocNames;

/**
 * Created by Devrath on 10-09-2016.
 */
public interface IntHomeView {
    void selectImage();
    void noImageSelected();
    void setUpDocNames(AdptDocNames adapter);
    void setUpRecyclerView(StaggeredGridLayoutManager view);
    void setGridViewDocsAdapter(Adpt_home adapter);
    void displayTheProofNameToBeShown(String mName);
    void isNewUser(boolean isNewUser, ResponseData mData);
    void registrationFailed();
}
