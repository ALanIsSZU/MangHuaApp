package com.example.alan.manghuaapp.inte;

import com.example.alan.manghuaapp.Untils.ApiManager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public interface GCContentInterface {

    @GET(ApiManager.SQUARE_URL)
    Call<String> getSquareBean(@Query("catalog_type") String square);

    @GET(ApiManager.SQUARE_MORE_URL)
    Call<String> getMorePage(@Query("catalog_type") String square, @Query("page_num") int page_num);
}
