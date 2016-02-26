package com.example.administrator.myapplication.retrofit;

import com.example.administrator.myapplication.Weatherget;

import org.json.JSONObject;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Administrator on 2016/2/26.
 */
public interface OpenWeather {
    @GET("/data/2.5/weather")
    Call<Weatherget> weather(@Query("q") String q, @Query("appid") String appid);
    //void weather(@Query("q") String place, @Query("appid") String appId, Callback<JSONObject> cb);
}