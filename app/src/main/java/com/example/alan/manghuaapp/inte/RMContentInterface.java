package com.example.alan.manghuaapp.inte;

import com.example.alan.manghuaapp.Untils.ApiManager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/12/20 0020.
 *
 */
public interface RMContentInterface {

    @GET(ApiManager.WEEK_URL)
    Call<String> getWeekenBean(@Path("weeken") String weeken);

}
