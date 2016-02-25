package com.example.administrator.myapplication.retrofit;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Administrator on 2016/2/25.
 */
public interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
}