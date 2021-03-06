package com.seat.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.seat.Constants.Constants;
import com.seat.Models.ResponseData;
import com.seat.Pages.Home.Adapters.AdptDocNames;
import com.seat.Pages.Home.Adapters.Adpt_home;
import com.seat.Pages.Home.Interfaces.IntHomeView;
import com.seat.Pages.Home.Presenters.PresenterHome;
import com.seat.R;
import com.seat.Utils.UtilActivitiesNavigation;
import com.seat.Utils.UtilSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Devrath on 10-09-2016.
 */
public class ActHome extends AppCompatActivity implements IntHomeView {

    @BindView(R.id.spnDocsId)
    Spinner spnDocsId;
    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.grid_view)
    RecyclerView grid_view;
    @BindView(R.id.rootView)
    LinearLayout rootView;


    PresenterHome presenter;
    Toolbar mToolbar;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        //Inject views from butter-knife
        ButterKnife.bind(this);
        //Set up Toolbar
        initToolbar();
        presenter = new PresenterHome(this, spnDocsId);

        /*TapTargetView.showFor(this,                 // `this` is an Activity
                TapTarget.forView(findViewById(R.id.action_upload), "This is a target", "We have the best targets, believe me")
                        // All options below are optional
                        .outerCircleColor(R.color.amber_400)      // Specify a color for the outer circle
                        .targetCircleColor(R.color.white)   // Specify a color for the target circle
                        .textColor(R.color.colorPrimary)            // Specify a color for text
                        //.textTypeFace(Typeface.SANS_SERIF)  // Specify a typeface for the text
                        .dimColor(R.color.black)            // If set, will dim behind the view with 30% opacity of the given color
                        .drawShadow(true)                   // Whether to draw a drop shadow or not
                        .cancelable(false)                  // Whether tapping outside the outer circle dismisses the view
                        .tintTarget(true)  ,                 // Whether to tint the target view's color
                        //.icon(Drawable),                    // Specify a custom drawable to draw as the target
                new TapTargetView.Listener() {          // The listener can listen for regular clicks, long clicks or cancels
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);      // This call is optional
                        //doSomething();
                    }
                });*/
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("Upload Documents");
        mToolbar.showOverflowMenu();
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_upload) {
            //Start Upload Process
            presenter.uploadData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void selectImage() {
        Intent inten = new Intent(this, ActProfileImageSelection.class);
        startActivityForResult(inten, Constants.INTENT_REQUEST_GET_N_IMAGES);
    }

    @Override
    public void noImageSelected() {
        UtilSnackbar.showSnakbarTypeOne(rootView, getResources().getString(R.string.noImageSelected));
    }

    @Override
    public void setUpDocNames(AdptDocNames adapter) {
        spnDocsId.setAdapter(adapter);

    }

    @Override
    public void setUpRecyclerView(StaggeredGridLayoutManager view) {
        grid_view.setLayoutManager(view);
    }

    @Override
    public void setGridViewDocsAdapter(Adpt_home adapter) {
        grid_view.setAdapter(adapter);
    }

    @Override
    public void displayTheProofNameToBeShown(String mName) {
        UtilSnackbar.showSnakbarTypeOne(rootView, "Please add " + mName);
    }

    @Override
    public void isNewUser(boolean isNewUser, ResponseData mData) {

        if (isNewUser == true) {
            //New User
            //UtilSnackbar.showSnakbarTypeOne(rootView, mData.getErrorMessage());
            UtilActivitiesNavigation.startActivityWithClassDataWithBackStackClear(ActHome.this, ActMessage.class,mData.getErrorMessage());
        } else if (isNewUser == false) {
            //Existing User
            //UtilSnackbar.showSnakbarTypeOne(rootView, mData.getErrorMessage());
            UtilActivitiesNavigation.startActivityWithClassDataWithBackStackClear(ActHome.this, ActMessage.class,mData.getErrorMessage());

        }

    }

    @Override
    public void registrationFailed() {
        //Registration Failed
        UtilSnackbar.showSnakbarTypeOne(rootView, getResources().getString(R.string.txt_reg_failure));
    }

    @Override
    public void notOnline() {
        UtilSnackbar.showSnakbarTypeOne(rootView, getResources().getString(R.string.conn_noconnectivity));
    }


    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);

        if (resuleCode == Activity.RESULT_OK) {
            if (requestCode == Constants.INTENT_REQUEST_GET_N_IMAGES) {
                presenter.getImageUri();
            }
        }

    }

    @Override
    public void onBackPressed() {
        handleExitApp();
    }

    private void handleExitApp() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        doubleBackToExitPressedOnce = true;
        showSnackbar(getResources().getString(R.string.txt_press_back_to_exit));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar
                .make(rootView, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }


    public void deleteDocument(int position) {
        presenter.deleteDocument(position);
    }

    public void addDocument(int position) {
        presenter.addDocument(position);
    }


}
