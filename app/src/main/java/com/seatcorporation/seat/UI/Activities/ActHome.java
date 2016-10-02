package com.seatcorporation.seat.UI.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.seatcorporation.seat.Constants.Constants;
import com.seatcorporation.seat.Models.ResponseData;
import com.seatcorporation.seat.Pages.Home.Adapters.AdptDocNames;
import com.seatcorporation.seat.Pages.Home.Adapters.Adpt_home;
import com.seatcorporation.seat.Pages.Home.Interfaces.IntHomeView;
import com.seatcorporation.seat.Pages.Home.Presenters.PresenterHome;
import com.seatcorporation.seat.R;
import com.seatcorporation.seat.Utils.UtilSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Devrath on 10-09-2016.
 */
public class ActHome extends AppCompatActivity implements IntHomeView {

    @BindView(R.id.spnDocsId) Spinner spnDocsId;
    @BindView(R.id.btnUpload) Button btnUpload;
    @BindView(R.id.grid_view) RecyclerView grid_view;
    @BindView(R.id.rootView) LinearLayout rootView;


    PresenterHome presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_home);
        //Inject views from butter-knife
        ButterKnife.bind(this);
        presenter= new PresenterHome(this,spnDocsId);
    }


    @OnClick(R.id.btnUpload)
    public void uploadData() {
        //Start Upload Process
        presenter.uploadData();
    }


    @Override
    public void selectImage() {
        Intent inten=new Intent(this, ActProfileImageSelection.class);
        startActivityForResult(inten, Constants.INTENT_REQUEST_GET_N_IMAGES);
    }

    @Override
    public void noImageSelected() {
        UtilSnackbar.showSnakbarTypeOne(rootView,getResources().getString(R.string.noImageSelected));
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
        UtilSnackbar.showSnakbarTypeOne(rootView,"Please add " + mName);
    }

    @Override
    public void isNewUser(boolean isNewUser, ResponseData mData) {

        if(isNewUser==true){
            //New User
            UtilSnackbar.showSnakbarTypeOne(rootView,mData.getErrorMessage());
        }else if(isNewUser==false){
            //Existing User
            UtilSnackbar.showSnakbarTypeOne(rootView,mData.getErrorMessage());
        }

    }

    @Override
    public void registrationFailed() {
        //Registration Failed
        UtilSnackbar.showSnakbarTypeOne(rootView,getResources().getString(R.string.txt_reg_failure));
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

    public  void deleteDocument(int position) {
        presenter.deleteDocument(position);
    }

    public  void addDocument(int position) {
        presenter.addDocument(position);
    }


}
