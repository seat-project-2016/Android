package com.seat.Retrofit;

import com.seat.Models.FinalMasterData;
import com.seat.Models.ResponseData;
import com.seat.Models.ResponseUserRegd;
import com.seat.Models.UserRegdData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Devrath on 27/09/16.
 */

public interface ApiInterface {

    @POST("register")
    Call<ResponseData> masterData(@Body FinalMasterData body);

    @POST("getmystatus")
    Call<ResponseUserRegd> masterUserRegd(@Body UserRegdData body);





}
