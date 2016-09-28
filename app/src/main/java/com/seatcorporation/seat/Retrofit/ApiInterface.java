package com.seatcorporation.seat.Retrofit;

import com.seatcorporation.seat.Models.ResponseData;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Devrath on 27/09/16.
 */

public interface ApiInterface {


    @POST("register")
    Call<ResponseData> masterData(@Body HashMap<String, String> body);

}
