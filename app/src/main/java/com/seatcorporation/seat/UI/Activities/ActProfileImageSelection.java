package com.seatcorporation.seat.UI.Activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.astuetz.PagerSlidingTabStrip;
import com.seatcorporation.seat.Pages.Home.Adapters.AdptImageSelection;
import com.seatcorporation.seat.R;

/**
 * Created by Devrath on 11-09-2016.
 */
public class ActProfileImageSelection extends AppCompatActivity {


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private AdptImageSelection adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_profile_image_selection);

        toolbar=setToolBarForLayout();
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new AdptImageSelection(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);
    }

    private Toolbar setToolBarForLayout() {
        //Set up the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.select_image));
        return toolbar;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}