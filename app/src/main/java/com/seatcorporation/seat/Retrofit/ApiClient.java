package com.seatcorporation.seat.Retrofit;

import com.seatcorporation.seat.Constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Devrath on 27/09/16.
 */

public class ApiClient {

    public static final String BASE_URL = Constants.BaseURL;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getRequestHeader())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getRequestHeader() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(Constants.mTimeOut, TimeUnit.SECONDS)
                .connectTimeout(Constants.mTimeOut, TimeUnit.SECONDS)
                .build();

        return okHttpClient;

    }

}
