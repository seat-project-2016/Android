package com.seatcorporation.seat.Pages.Home.Interfaces;

import android.support.v7.widget.StaggeredGridLayoutManager;

import com.seatcorporation.seat.Pages.Home.Adapters.Adpt_home;
import com.seatcorporation.seat.Pages.Home.Adapters.AdptDocNames;

/**
 * Created by Devrath on 10-09-2016.
 */
public interface IntHomeView {
    void homeSuccess();
    void homeFailure();
    void selectImage();
    void noImageSelected();
    void setUpDocNames(AdptDocNames adapter);
    void setUpRecyclerView(StaggeredGridLayoutManager view);
    void setGridViewDocsAdapter(Adpt_home adapter);
    void refreshRecyclerView();

    void displayTheProofNameToBeShown(String mName);
}
