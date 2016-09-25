package com.seatcorporation.seat.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.seatcorporation.seat.Constants.Constants;

import java.io.ByteArrayOutputStream;

/**
 * Created by Devrath on 10-09-2016.
 */
public class UtilEncodeDecodeBase64 {


    /**********************************
     * ConvertIntoBase64Image
     **********************************/
    public static Bitmap decodeBase64ToImage(String mData) throws Exception {
        try {
            byte[] imageByteData = Base64.decode(mData, Base64.DEFAULT);
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(imageByteData, 0, imageByteData.length, options);
            options.inSampleSize = calculateInSampleSize(options, Constants.REQ_WIDTH, Constants.REQ_HEIGHT);
            options.inJustDecodeBounds = false;
            options.inPurgeable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByteData, 0, imageByteData.length, options);
            return imageBitmap;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * ConvertImageIntoBase64
     *
     * @param uriString
     **/
    public static String encodeImageToBase64(String uriString) {

        Bitmap bm = BitmapFactory.decodeFile(uriString);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        //Log.d("BASE-ENCODED-***",Base64.encodeToString(byteArrayImage, Base64.DEFAULT));
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT);

    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
