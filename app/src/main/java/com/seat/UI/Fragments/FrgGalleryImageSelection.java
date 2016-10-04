package com.seat.UI.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.naver.android.helloyako.imagecrop.view.ImageCropView;
import com.seat.Constants.Constants;
import com.seat.R;
import com.seat.UI.Activities.ActHome;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Devrath on 11-09-2016.
 */
public class FrgGalleryImageSelection extends Fragment {

    private static final String TAG = FrgGalleryImageSelection.class.getSimpleName();
    private ImageCropView imageCropView;
    private ImageAdapter adapter;
    private ArrayList<String> images;

    public static FrgGalleryImageSelection newInstance() {
        FrgGalleryImageSelection fragment = new FrgGalleryImageSelection();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frg_gallery_image_selection, container, false);
        setHasOptionsMenu(true);
        GridView gridView = (GridView) rootView.findViewById(R.id.gallery_grid);
        imageCropView = (ImageCropView) rootView.findViewById(R.id.previewImage);

        adapter = new ImageAdapter(getActivity());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(images.get(position), bmOptions);

                int height = bitmap.getHeight(), width = bitmap.getWidth();

                if (height > 1280 && width > 960) {
                    bmOptions.inSampleSize = 2;
                    Bitmap imgbitmap = BitmapFactory.decodeFile(images.get(position), bmOptions);
                    imageCropView.setImageBitmap(imgbitmap);
                    Log.e("Need to resize", "Need to resize");

                } else {
                    imageCropView.setImageBitmap(bitmap);
                    Log.e("WORKS", "WORKS");
                }
                imageCropView.setAspectRatio(4, 3);
            }
        });


        FrameLayout framelayout = (FrameLayout) rootView.findViewById(R.id.preview_image_layout);
        setLayoutParams(framelayout);
        setInitialPreviewImage();

        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_image_select, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_ok) {
            convertBitmapToFile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setInitialPreviewImage() {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(images.get(0), bmOptions);
        imageCropView.setImageBitmap(bitmap);
        imageCropView.setAspectRatio(4, 3);
    }

    public File convertBitmapToFile() {
        Bitmap croppedImage = imageCropView.getCroppedImage();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        croppedImage.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        File bitmapFile = null;
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "TownseeGallery");
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        Constants.CAMERA_FOLDER_PATH = Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg"));
        if (mediaFile.exists()) {
            mediaFile.delete();
        }

        try {
            mediaFile.createNewFile();
            FileOutputStream fo = new FileOutputStream(mediaFile);
            fo.write(bytes.toByteArray());
            fo.close();
            Intent intent = new Intent(getActivity(), ActHome.class);
            getActivity().setResult(getActivity().RESULT_OK, intent);
            getActivity().finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapFile;
    }

    private void setLayoutParams(FrameLayout framelayout) {
        int deviceWidth = getScreenWidth();
        double dHeight = (double) deviceWidth * 0.75;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) framelayout.getLayoutParams();
        params.height = (int) dHeight;
        params.width = deviceWidth;
        framelayout.setLayoutParams(params);
    }

    /**
     * The Class ImageAdapter.
     */
    private class ImageAdapter extends BaseAdapter {

        private Activity context;
        private float imageWidth;

        public ImageAdapter(Activity localContext) {
            context = localContext;
            images = getAllShownImagesPath(context);

            int screenWidth = getScreenWidth();
            int paddingValues = 4;
            int availableGridWidth = screenWidth - paddingValues;
            int numberOfColumns = 3;
            imageWidth = (float) (availableGridWidth / numberOfColumns);
        }

        public int getCount() {
            return images.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            ImageView picturesView;
            if (convertView == null) {
                picturesView = new ImageView(context);
                picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                picturesView
                        .setLayoutParams(new GridView.LayoutParams((int) imageWidth, (int) imageWidth));

            } else {
                picturesView = (ImageView) convertView;
            }

            Glide.with(context).load(images.get(position))
                    .placeholder(R.drawable.grey_placholder_img).centerCrop()
                    .into(picturesView);

            return picturesView;
        }

        /**
         * Getting All Images Path.
         *
         * @param activity the activity
         * @return ArrayList with images Path
         */
        private ArrayList<String> getAllShownImagesPath(Activity activity) {
            Uri uri;
            Cursor cursor;
            int column_index_data, column_index_folder_name;
            ArrayList<String> listOfAllImages = new ArrayList<String>();
            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            String orderBy = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
            cursor = activity.getContentResolver().query(uri, projection, null,
                    null, orderBy);

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_folder_name = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);

                listOfAllImages.add(absolutePathOfImage);
            }
            return listOfAllImages;
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}