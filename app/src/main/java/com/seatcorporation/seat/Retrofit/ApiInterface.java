package com.seatcorporation.seat.Retrofit;

import com.seatcorporation.seat.Models.ResponseData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Devrath on 27/09/16.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseData> masterData(
    @Field("phone_number") String phone_number,
    @Field("name") String name,
    @Field("device_id") String device_id,
    @Field("os_type") String os_type,
    @Field("documents") String documents);

}
