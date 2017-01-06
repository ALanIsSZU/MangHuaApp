package com.example.alan.manghuaapp.widget;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.DiscoverBean;
import com.example.alan.manghuaapp.ui.Count_Activity;
import com.example.alan.manghuaapp.ui.WebActivity;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ly on 2016/12/21.
 * <p>
 * 图片轮播
 */

public class BannerView extends FrameLayout {

    Banner banner;

    List<DiscoverBean.DataBean.InfosBean.BannersBean> bannersBeen;


    public BannerView(Context context) {
        super(context);
        init();
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        bannersBeen = new ArrayList<>();

        //加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.banner_layout, this, true);
        //初始化控件
        banner = (Banner) findViewById(R.id.banner);

        //==========banner属性设置============
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new PicassoImageLoader());
//        //设置图片集合
//        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Stack);
//        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(Arrays.asList(titles));
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //点击事件
        banner.setClickable(true);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                if (position - 1 > bannersBeen.size()) {
                    return;
                }
                int target_id = bannersBeen.get(position - 1).getTarget_id();
                int itemViewType = bannersBeen.get(position - 1).getType();
                if (itemViewType == 2) {
                    Intent intent = new Intent(getContext(), Count_Activity.class);
                    intent.putExtra(KeyWord.ID, target_id);
                    getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), WebActivity.class);
                    intent.putExtra(KeyWord.URL, "http://www.kuaikanmanhua.com/web/comic/" + target_id + "/");
                    getContext().startActivity(intent);
                }
            }
        });
    }

    /**
     * 图片加载器 Glide
     */
    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            Log.d("GlideImageLoader", path.toString());
            //把path转换成ImageView
            Glide.with(getContext()).load(path).placeholder(R.drawable.ic_common_placeholder_l_750).into(imageView);
        }
    }

    /**
     * 图片加载器 Picasso
     */
    class PicassoImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            Log.d("GlideImageLoader", path.toString());
            //把path转换成ImageView
            Picasso.with(getContext()).load(((String) path)).resize(640, 320).placeholder(R.drawable.ic_common_placeholder_l_750).into(imageView);
        }
    }

    /**
     * 设置轮播数据
     */
    public void setBannerData(DiscoverBean bean) {

        if (bean.getData().getInfos().isEmpty()) {
            Toast.makeText(getContext(), "轮播图数据加载失败", Toast.LENGTH_SHORT).show();
            return;
        }

        bannersBeen.clear();
        bannersBeen.addAll(bean.getData().getInfos().get(0).getBanners());

        List<String> imgUrl = new ArrayList<>();
        for (DiscoverBean.DataBean.InfosBean.BannersBean ban : bannersBeen) {
            imgUrl.add(ban.getPic());
        }
        Log.d("BannerView", "===========imgUrl:" + imgUrl);
        banner.setImages(imgUrl);
        //开始轮播
        banner.start();

    }

}
