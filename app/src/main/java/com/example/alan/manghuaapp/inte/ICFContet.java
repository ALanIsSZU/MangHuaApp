package com.example.alan.manghuaapp.inte;

import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.bean.Author_Sencond_Bean;
import com.example.alan.manghuaapp.bean.Author_bean;
import com.example.alan.manghuaapp.bean.ClassifcationBean;
import com.example.alan.manghuaapp.bean.DiscoverBean;
import com.example.alan.manghuaapp.bean.GoToBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alan on 2016/12/19.
 */

public interface ICFContet {
    //分类的接口
    @GET("get_by_tag?&since=0&count=20&sa_event=eyJkaXN0aW5jdF9pZCI6IkE6YWM5OTVjYTdlMWIwMzZiNyIsImV2ZW50IjoiUmVhZEZpbmRQYWdlIiwib3JpZ2luYWxfaWQiOiJBOmFjOTk1Y2E3ZTFiMDM2YjciLCJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJwcm9wZXJ0aWVzIjp7IkNhdGVnb3J5Ijoi5YWo6YOoIiwiRmluZENhdGVnb3J5VGFiTmFtZSI6IuaBi-eIsSIsIkZpbmRUYWJOYW1lIjoi5YiG57G7IiwiRnJvbUZpbmRDYXRlZ29yeVRhYk5hbWUiOiLmgYvniLEiLCJGcm9tRmluZFRhYk5hbWUiOiLliIbnsbsiLCJIb21lcGFnZVRhYk5hbWUiOiLng63pl6giLCJIb21lcGFnZVVwZGF0ZURhdGUiOjEsIlRyaWdnZXJQY")
    Call<ClassifcationBean> getData(@Query("tag") String tag);
    //跳转的接口
    @GET("v1/topics/{page}?sort=0&sa_event=eyJkaXN0aW5jdF9pZCI6IkE6YWM5OTVjYTdlMWIwMzZiNyIsImV2ZW50IjoiUmVhZFRvcGljIiwib3JpZ2luYWxfaWQiOiJBOmFjOTk1Y2E3ZTFiMDM2YjciLCJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJwcm9wZXJ0aWVzIjp7IkF1dGhvcklEIjoxNDc3NjI4NiwiQ2F0ZWdvcnkiOiLlhajpg6giLCJDb21pY0lEIjowLCJDb21pY05hbWUiOiLml6Dms5Xojrflj5YiLCJDb21pY1BhZ2VJdGVtTmFtZSI6IuaXoOazleiOt-WPliIsIkNvbWljUGFnZVRyaWdnZXJJdGVtIjowLCJGaW5kQ2F0ZWdvcnlUYWJOYW1lIjoi5YWo6YOoIiwiRmluZFRhYk5hbWUiOiLliIbnsbsiLCJIb21lcGFnZVRhYk5hbWUiOiLng63pl6")
    Call<GoToBean> getAniting(@Path("page") String page);
    //跳转作者页面的资料接口
    @GET("v1/users/{tag}?sa_event=eyJkaXN0aW5jdF9pZCI6IkE6YWM5OTVjYTdlMWIwMzZiNyIsImV2ZW50IjoiUmVhZEF1dGhvckhvbWVQYWdlIiwib3JpZ2luYWxfaWQiOiJBOmFjOTk1Y2E3ZTFiMDM2YjciLCJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJwcm9wZXJ0aWVzIjp7IkF1dGhvcklEIjoyOTY3OTQzLCJDYXRlZ29yeSI6IuaXoOazleiOt-WPliIsIkNvbWljSUQiOjAsIkNvbWljTmFtZSI6IuaXoOazleiOt-WPliIsIkNvbWljT3JkZXJOdW1iZXIiOjAsIkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIkhvbWVwYWdlVXBkYXRlRGF0ZSI6MiwiTmlja05hbWUiOiLph5HkuJgiLCJUb3BpY0lEIjo1NDQsIlRvcGljTmFtZSI6IuaVtOWuuea4uO")
    Call<Author_bean> getAuthorInfo(@Path("tag") String tag);
    //跳转作者页面的动态接口
    @GET("v1/feeds/feed_lists?&since=0&page_num=1&catalog_type=3")
    Call<Author_Sencond_Bean> getAuthorDongTai(@Query("uid") String uid);

    //加载更多数据
    @GET("get_by_tag?&count=20&sa_event=eyJkaXN0aW5jdF9pZCI6IkE6YWM5OTVjYTdlMWIwMzZiNyIsImV2ZW50IjoiUmVhZEZpbmRQYWdlIiwib3JpZ2luYWxfaWQiOiJBOmFjOTk1Y2E3ZTFiMDM2YjciLCJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJwcm9wZXJ0aWVzIjp7IkNhdGVnb3J5Ijoi5YWo6YOoIiwiRmluZENhdGVnb3J5VGFiTmFtZSI6IuaBi-eIsSIsIkZpbmRUYWJOYW1lIjoi5YiG57G7IiwiRnJvbUZpbmRDYXRlZ29yeVRhYk5hbWUiOiLmgYvniLEiLCJGcm9tRmluZFRhYk5hbWUiOiLliIbnsbsiLCJIb21lcGFnZVRhYk5hbWUiOiLng63pl6giLCJIb21lcGFnZVVwZGF0ZURhdGUiOjEsIlRyaWdnZXJQY" )
    Call<ClassifcationBean> getNewData(@Query("tag") String tag,@Query("since") String since);
    /**
     * 推荐数据接口
     *
     * @return DiscoverBean
     */
    @GET(APIMnager.RecommentUrl)
    Call<DiscoverBean> getRecomment();
}
