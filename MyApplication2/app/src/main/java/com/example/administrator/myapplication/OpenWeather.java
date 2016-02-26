package com.example.administrator.myapplication;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2016/2/26.
 */
public interface OpenWeather {
    @GET("/data/2.5/weather")
    Call<JSONObject> weather(@Query("q") String q, @Query("appid") String appid);
    //void weather(@Query("q") String place, @Query("appid") String appId, Callback<JSONObject> cb);
}