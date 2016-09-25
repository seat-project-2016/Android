package com.seatcorporation.seat.Pages.Home.Presenters;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.seatcorporation.seat.Constants.Constants;
import com.seatcorporation.seat.Constants.RequestCodes;
import com.seatcorporation.seat.Models.ItemObjects;
import com.seatcorporation.seat.Pages.Home.Adapters.AdptDocNames;
import com.seatcorporation.seat.Pages.Home.Adapters.Adpt_home;
import com.seatcorporation.seat.Pages.Home.Interfaces.IntHomeView;
import com.seatcorporation.seat.R;
import com.seatcorporation.seat.UI.Activities.ActHome;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Devrath on 9/5/2016.
 */
public class PresenterHome implements AdapterView.OnItemSelectedListener{

    private IntHomeView view;
    private StaggeredGridLayoutManager gridLayoutManager;
    private Adpt_home rcAdapter;
    private Activity context;
    List<ItemObjects> gaggeredList;
    Spinner spnDocsId;
    TypedArray docNames,docImgs;
    Uri mImageUriLoc=null;
    HashSet<Uri> mMedia;
    int mDocPosition;

    LinkedList<ItemObjects> listViewItems;


    public PresenterHome(ActHome view, Spinner spnDocsId) {

        //Set the user view
        this.view=view;
        this.context=view;
        this.spnDocsId=spnDocsId;

        //Presentation Logic
        initPresenter();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context, docNames.getResourceId(position,R.string.app_name), Toast.LENGTH_LONG).show();
        if(position!=0)
        selectImage(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void initPresenter() {
        //Set Spinner Selected values
        setDocImages();
        setDocNames();
    }

    private void setDocSelectors() {
        docImgs =context.getResources().obtainTypedArray(R.array.doc_logos);
        docNames =context.getResources().obtainTypedArray(R.array.doc_names);
    }

    private void setDocNames() {
        //Set Spinner Selected values
        setDocSelectors();
        //Set the listener for spinner
        spnDocsId.setOnItemSelectedListener(this);
        //Set the adapter for the spinner
        AdptDocNames customAdapter=new AdptDocNames(context, docImgs, docNames);
        view.setUpDocNames(customAdapter);
    }

    private void setDocImages() {
        //Set up data for adapter
        setDataForAdapter();
        //staggeredGridviewSetup
        staggeredViewSetup();
    }

    private void setDataForAdapter() {
        gaggeredList = getListItemData();
    }

    private void staggeredViewSetup() {
        gridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        view.setUpRecyclerView(gridLayoutManager);

        rcAdapter = new Adpt_home(context, gaggeredList);
        view.setGridViewDocsAdapter(rcAdapter);
    }


    private List<ItemObjects> getListItemData(){
        listViewItems = new LinkedList<>();
        Uri path = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.drawable.ic_launcher);
        listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_yourpic),path));
        listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_driving_licence), path));
        listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_taxi_pic), path));

        return listViewItems;
    }




    private void selectImage(int position) {
        int hasWriteStoragePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int hasCameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);

        if (!(hasWriteStoragePermission == PackageManager.PERMISSION_GRANTED && hasCameraPermission == PackageManager.PERMISSION_GRANTED))
        {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestCodes.PERMISSION_REQUEST_CODE);
        } else {
            mMedia = new HashSet<Uri>();
            mImageUriLoc = null;
            mDocPosition=position;
            view.selectImage();

        }
    }


    public void getImageUri() {
        boolean canServerCallBeMade = false;
        try {
            if (Constants.CAMERA_FOLDER_PATH!=null ){
                //Call the Server call in Profile fragment
                canServerCallBeMade = true;
                mImageUriLoc=Constants.CAMERA_FOLDER_PATH;
                imageCompression(canServerCallBeMade);
            } else {
                view.noImageSelected();
            }

        } catch (Exception ex) {
            Constants.CAMERA_FOLDER_PATH=null;
            ex.printStackTrace();
            //Image has not captured
            canServerCallBeMade=false;
            imageCompression(canServerCallBeMade);
        }

    }

    public void imageCompression(boolean canServerCallBeMade) {
        if (canServerCallBeMade) {
            if(listViewItems.size()==3){
                if(mDocPosition==1){
                    listViewItems.set(mDocPosition-1, new ItemObjects(context.getResources().getString(R.string.doc_name_yourpic), getImageUriLoc()));
                }else if(mDocPosition==2){
                    listViewItems.set(mDocPosition-1, new ItemObjects(context.getResources().getString(R.string.doc_name_driving_licence), getImageUriLoc()));
                }else if(mDocPosition==3){
                    listViewItems.set(mDocPosition-1, new ItemObjects(context.getResources().getString(R.string.doc_name_taxi_pic), getImageUriLoc()));
                }
            }else{
                if(mDocPosition==1){
                    listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_yourpic), getImageUriLoc()));
                }else if(mDocPosition==2){
                    listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_driving_licence), getImageUriLoc()));
                }else if(mDocPosition==3){
                    listViewItems.add(new ItemObjects(context.getResources().getString(R.string.doc_name_taxi_pic), getImageUriLoc()));
                }
            }



            //Refresh GridView
            rcAdapter.notifyDataSetChanged();

            //Reset DocName position in the spinner
            spnDocsId.setSelection(0);


           // new ItemObjects(context.getResources().getString(R.string.doc_name_taxi_pic), R.mipmap.ic_launcher)
            Toast.makeText(context,getImageUriLoc().getLastPathSegment(),Toast.LENGTH_SHORT).show();
            //new AsyncImageCompression(context, getImageUriLoc(), ActProfile.this).execute("");
        }
    }

    public Uri getImageUriLoc() {
        //Return the Uri of the image
        return mImageUriLoc;
    }


    public  void deleteDocument(int position) {
        listViewItems.remove(position);
        //Refresh GridView
        //rcAdapter.notifyDataSetChanged();
        rcAdapter.notifyItemRemoved(position);
        rcAdapter.notifyItemRangeChanged(position,gaggeredList.size());
    }



}
