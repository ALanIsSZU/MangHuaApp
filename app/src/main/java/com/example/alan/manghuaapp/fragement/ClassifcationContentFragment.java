package com.example.alan.manghuaapp.fragement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.Adapter.ManHuaAdapter;
import com.example.alan.manghuaapp.bean.ClassifcationBean;
import com.example.alan.manghuaapp.inte.ICFContet;
import com.example.alan.manghuaapp.ui.Count_Activity;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alan on 2016/12/19.
 */

public class ClassifcationContentFragment extends Fragment{

    ListView lv;
    List<ClassifcationBean.DataBean.TopicsBean> list;
    ManHuaAdapter<ClassifcationBean.DataBean.TopicsBean> adapter;
    //传过来的id
    int key;
    //刷新
    SwipeRefreshLayout refresh;
   //判断上拉加载
    boolean scrollFlag = false;
    boolean isMore = false;
    int since;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.classifcation_content,container,false);
       lv= (ListView) view.findViewById(R.id.CFContent_lv);
        list=new ArrayList<>();
        refresh= (SwipeRefreshLayout) view.findViewById(R.id.fenLeireRresh);
        //获取传过来的数据
        Bundle bundle = getArguments();
        key = bundle.getInt(KeyWord.KEY);
        getInfo(key);
        //下拉刷新
        refresh.setColorSchemeColors(new int[]{Color.BLACK,Color.RED,Color.GREEN});
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getInfo(key);
                refresh.setRefreshing(false);
            }
        });

        //创建一个adpter
       adapter=new ManHuaAdapter<ClassifcationBean.DataBean.TopicsBean>(getContext(),list,R.layout.cfcontent_layout) {
           @Override
           public void bindData(int position, ViewHolder holder) {
               ClassifcationBean.DataBean.TopicsBean bean = list.get(position);
               ImageView content_iv= (ImageView) holder.findViewById(R.id.cdcontent_iv);
               TextView title= (TextView) holder.findViewById(R.id.cfTitle);
               TextView userName= (TextView) holder.findViewById(R.id.userNmae);
               TextView like= (TextView) holder.findViewById(R.id.cf_like);
               TextView say= (TextView) holder.findViewById(R.id.cf_say);

               Picasso.with(getContext()).load(bean.getCover_image_url()).fit().placeholder(R.drawable.ic_common_placeholder_s).into(content_iv);
               title.setText(bean.getTitle());
               userName.setText(bean.getUser().getNickname());
               int likes_count = bean.getLikes_count();
               if (bean.getLikes_count()>10000){
                int number1=likes_count/10000;
                   like.setText(number1+"万");
               }else {
                   like.setText(likes_count+"");
               }

               int comments_count = bean.getComments_count();
               if (comments_count>10000){
                   int number2=comments_count/10000;
                   say.setText(number2+"万");
               }else {
                   say.setText(comments_count+"");
               }



           }
       };
        lv.setAdapter(adapter);
        //item的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int number = list.get(position).getId();
                Intent intent=new Intent(getActivity(), Count_Activity.class);
                intent.putExtra(KeyWord.ID,number);
                startActivity(intent);
            }
        });



        //上拉刷新解决下拉刷新的冲突
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                switch (scrollState){
//                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://屏幕停止滚动式
//                        scrollFlag =false;
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动时
//                        scrollFlag =true;
//                        break;
//                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
//                        scrollFlag=true;
//                        break;
//                }
                if (scrollState==0&&isMore){
                    since+=20;
                    getMorePageData(key,since);
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View childAt = lv.getChildAt(firstVisibleItem);
                if (firstVisibleItem==0&&(childAt==null||childAt.getTop()==0)){
                    refresh.setEnabled(true);
                }else{
                    refresh.setEnabled(false);
                }

                if (firstVisibleItem+visibleItemCount==totalItemCount){
                    isMore = true;
                }else{
                    isMore = false;
                }
            }
        });

        return view;
    }

    //上拉加载数据
    private void getMorePageData(int key, int since) {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIMnager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICFContet contet=retrofit.create(ICFContet.class);
        Call<ClassifcationBean> call = contet.getNewData(key + "", since + "");
        call.enqueue(new Callback<ClassifcationBean>() {
            @Override
            public void onResponse(Call<ClassifcationBean> call, Response<ClassifcationBean> response) {
                ClassifcationBean body = response.body();
                list.addAll(body.getData().getTopics());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassifcationBean> call, Throwable t) {

            }
        });
    }

    //进来的时候加载数据
    private void getInfo(int key) {
        //网络解析数据
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIMnager.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICFContet contet=retrofit.create(ICFContet.class);
        Call<ClassifcationBean> call=contet.getData(key+"");
        call.enqueue(new Callback<ClassifcationBean>() {
            @Override
            public void onResponse(Call<ClassifcationBean> call, Response<ClassifcationBean> response) {
                list.clear();
                ClassifcationBean bean=response.body();
                list.addAll(bean.getData().getTopics());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ClassifcationBean> call, Throwable t) {

            }
        });
    }
}
