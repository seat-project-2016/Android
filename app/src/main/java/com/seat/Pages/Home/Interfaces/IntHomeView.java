package com.seat.Pages.Home.Interfaces;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.seat.Models.ResponseData;
import com.seat.Pages.Home.Adapters.Adpt_home;
import com.seat.Pages.Home.Adapters.AdptDocNames;

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
    void notOnline();
}
