package com.example.alan.manghuaapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.GoToBean;
import com.example.alan.manghuaapp.fragement.GoToFristContent;
import com.example.alan.manghuaapp.fragement.GoToSencondContent;
import com.example.alan.manghuaapp.fragement.TextModel2;
import com.example.alan.manghuaapp.fragement.TextmODEL1;
import com.example.alan.manghuaapp.inte.ICFContet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Alan on 2016/12/20.
 */

public class Count_Activity extends AppCompatActivity{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> list;
    ImageView manhuaIv;
    TextView gzTv;
    TextView likeTv;
    TextView sayTv;
    //充进来的fragement
    GoToFristContent frist;
    GoToSencondContent sencond;
    FragmentPagerAdapter adapter;

    //传过来的id
    int id;

    String[] titles={
            "简介","内容"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiao_count_layout);
        setupView();
//        拿到传过来的id
        Intent intent=getIntent();
        id = intent.getIntExtra(KeyWord.ID, 782);
        //加载数据
        getData(id);

    }

    private void setupView() {

        tabLayout= (TabLayout) findViewById(R.id.goto_tabLayout);
        viewPager= (ViewPager) findViewById(R.id.goto_ViewPager);

        manhuaIv= (ImageView) findViewById(R.id.manHua_iv);
        gzTv = (TextView) findViewById(R.id.gztv);
        list=new ArrayList<>();
        likeTv= (TextView) findViewById(R.id.gooLike);
        sayTv= (TextView) findViewById(R.id.gotoSay);

        for (int i = 0; i < titles.length; i++) {
            if (i==0){
                frist=new GoToFristContent();
                list.add(frist);
            }else if(i==1){
                sencond=new GoToSencondContent();
                list.add(sencond);
            }
//            TextModel2 model2=new TextModel2();
//            list.add(model2);
        }
        adapter =new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        Log.d("TAG", "进入了初始化的方法");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    //    网咯获数据
    private void getData(int id) {
        Log.d("TAG", "进入下载数据");
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIMnager.TIAO_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICFContet contet=retrofit.create(ICFContet.class);
        Call<GoToBean> call =contet.getAniting(id+"");
        call.enqueue(new Callback<GoToBean>() {
            @Override
            public void onResponse(Call<GoToBean> call, Response<GoToBean> response) {
                GoToBean bean=response.body();
                Log.d("TAG", "获取数据");
                //将数据传到两个fragemnet
                frist.getFridtInfo(bean);
                sencond.getSencondInfo(bean);
                //中间的图片
                Picasso.with(Count_Activity.this).load(bean.getData().getCover_image_url()).fit().into(manhuaIv);
                //标题
                //设置toolsBar标题的文字
                toolbar= (Toolbar) findViewById(R.id.toolbar);
                toolbar.setTitle(bean.getData().getTitle());
                setSupportActionBar(toolbar);
                //关注的人数
                if (bean.getData().getFav_count()>10000){
                    int number=bean.getData().getFav_count()/10000;
                    gzTv.setText(number+"万人关注");
                }else {
                    gzTv.setText(bean.getData().getFav_count()+"");
                }
                //点赞数量
                if (bean.getData().getLikes_count()>10000){
                    int number2=bean.getData().getLikes_count()/10000;
                    likeTv.setText(number2+"万");
                }else {
                    likeTv.setText(bean.getData().getLikes_count()+"");
                }
                //评论的数量
                if (bean.getData().getComments_count()>10000){
                    int number3=bean.getData().getComments_count()/10000;
                    sayTv.setText(number3+"万");
                }else {
                    sayTv.setText(bean.getData().getComments_count()+"");
                }
            }

            @Override
            public void onFailure(Call<GoToBean> call, Throwable t) {

            }
        });
    }
    //退出界面
    public void back(View view) {
        finish();
    }
}
