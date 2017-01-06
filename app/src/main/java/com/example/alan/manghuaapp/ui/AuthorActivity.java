package com.example.alan.manghuaapp.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.Author_bean;
import com.example.alan.manghuaapp.fragement.Author_First_fra;
import com.example.alan.manghuaapp.fragement.Author_Sencond_fra;
import com.example.alan.manghuaapp.fragement.GoToSencondContent;
import com.example.alan.manghuaapp.inte.ICFContet;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Alan on 2016/12/22.
 */

public class AuthorActivity extends AppCompatActivity{

    CollapsingToolbarLayout coTolbar;
    int id;
    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments;
    //充技巧的两个fragement
    Author_First_fra first;
    Author_Sencond_fra sencond;
    //主要的控件
    ImageView userHead;
    TextView fsNumber;
    TextView content;

    String[] titles={
        "资料","动态"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_activity);
        //设置标题的文字的样式和内容
        coTolbar= (CollapsingToolbarLayout) findViewById(R.id.author_coToolbar);
        coTolbar.setCollapsedTitleTextColor(Color.WHITE);
        coTolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));
        coTolbar.setCollapsedTitleTextAppearance(R.style.textStyle);
        coTolbar.setExpandedTitleTextAppearance(R.style.textStyle);
//        coTolbar.setTitle("金秋");
        //拿到id
        Intent intent = getIntent();
        id=intent.getIntExtra(KeyWord.AUTHOR,1);
        //初始化数据
        setupView();
        //获取数据
        getInfo(id);
    }

    //初始化数据
    private void setupView() {

        userHead= (ImageView) findViewById(R.id.author_userHead);
        fsNumber= (TextView) findViewById(R.id.author_fsNumber);
        content = (TextView) findViewById(R.id.author_content);

        tabLayout= (TabLayout) findViewById(R.id.author_tabLayout);
        viewPager= (ViewPager) findViewById(R.id.author_ViewPager);
        fragments=new ArrayList<>();

        //两个fragement
        for (int i = 0; i < 2; i++) {
            if (i==0){
                first=new Author_First_fra();
                fragments.add(first);
            }else if(i==1) {
                sencond =new Author_Sencond_fra();
                Bundle bundle=new Bundle();
                bundle.putInt(KeyWord.AUTHOR_life,id);
                sencond.setArguments(bundle);
                fragments.add(sencond);
            }
        }

        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    //获取数据
    private void getInfo(int id) {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIMnager.AUTHOR_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ICFContet contet=retrofit.create(ICFContet.class);
        Call<Author_bean> call=contet.getAuthorInfo(id+"");
        call.enqueue(new Callback<Author_bean>() {
            @Override
            public void onResponse(Call<Author_bean> call, Response<Author_bean> response) {
                Author_bean bean=response.body();
                //fragement获取数据
                first.getAuthorBean(bean);

//                设置控件的内容
                if (bean.getData().getFollower_cnt()>10000){
                    fsNumber.setText(bean.getData().getFollower_cnt()/10000+"万粉丝");
                }else {
                    fsNumber.setText(bean.getData().getFollower_cnt()+"");
                }

                //设置控件的宽度
                int widthPixels = getResources().getDisplayMetrics().widthPixels;
                ViewGroup.LayoutParams params = content.getLayoutParams();
                params.width=widthPixels-50;
                content.requestLayout();
                content.setText(bean.getData().getU_intro());
                //设置标题
                coTolbar.setTitle(bean.getData().getNickname());

                //对图片进行裁剪剪成圆形（用户头像）
                Picasso picasso=Picasso.with(AuthorActivity.this);

                Transformation transformation=new Transformation() {
                    @Override
                    public Bitmap transform(Bitmap source) {
                        int width=source.getWidth();
                        int height=source.getHeight();
                        //圆形图片的半径
                        int radius=Math.min(width,height)/2;
                        Bitmap blankBitmap=Bitmap.createBitmap(radius*2,radius*2,Bitmap.Config.ARGB_8888);
                        Canvas canvas=new Canvas(blankBitmap);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        canvas.drawCircle(radius, radius, radius, paint);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                        canvas.drawBitmap(source,0,0,paint);
                        if (source !=null && !source.isRecycled()){
                            source.recycle();
                        }
                        return blankBitmap;
                    }

                    @Override
                    public String key() {
                        return "picasso";
                    }
                };
                picasso.load(bean.getData().getAvatar_url()).transform(transformation).fit().into(userHead);


            }

            @Override
            public void onFailure(Call<Author_bean> call, Throwable t) {
                Log.d("TAg", "AuthorActivity获取数据失败");
            }
        });
    }

    //退出按键的方法
    public void author_back(View view) {
        finish();
    }
}
