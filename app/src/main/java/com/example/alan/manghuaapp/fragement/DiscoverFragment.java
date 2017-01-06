package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.Untils.DensityUtil;
import com.example.alan.manghuaapp.Untils.PagingScrollHelper;
import com.example.alan.manghuaapp.Adapter.DisItemAdapter;
import com.example.alan.manghuaapp.Adapter.DiscoverAdapter;
import com.example.alan.manghuaapp.bean.DiscoverBean;
import com.example.alan.manghuaapp.inte.ICFContet;
import com.example.alan.manghuaapp.widget.BannerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ly on 2016/12/21.
 */

public class DiscoverFragment extends Fragment {

    ListView lv;
    //图片轮播栏
    BannerView bannerView;
    //适配器
    DiscoverAdapter<DiscoverBean.DataBean.InfosBean> adapter;
    //数据源
    List<DiscoverBean.DataBean.InfosBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        bannerView = new BannerView(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViews(view);

        initData();
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIMnager.BaseUrlAll)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICFContet icfContet = retrofit.create(ICFContet.class);
        Call<DiscoverBean> call = icfContet.getRecomment();
        call.enqueue(new Callback<DiscoverBean>() {
            @Override
            public void onResponse(Call<DiscoverBean> call, Response<DiscoverBean> response) {
                List<DiscoverBean.DataBean.InfosBean> infos = response.body().getData().getInfos();
                data.clear();
                data.addAll(infos);
                adapter.notifyDataSetChanged();
                bannerView.setBannerData(response.body());
            }

            @Override
            public void onFailure(Call<DiscoverBean> call, Throwable t) {

            }
        });

    }

    private void setupViews(View view) {
        lv = (ListView) view.findViewById(R.id.discover_lv);
        //隐藏分割线
        lv.setDividerHeight(0);
        lv.addHeaderView(bannerView);

        adapter = new DiscoverAdapter<DiscoverBean.DataBean.InfosBean>(getActivity(), data, R.layout.dis_item_layout) {
            @Override
            public void bindData(int position, ViewHolder holder) {
                int itemType = data.get(position).getItem_type();
                //排除轮播,隐藏item
                if (itemType == 1) {
                    RelativeLayout rl = (RelativeLayout) holder.findViewById(R.id.rl);
                    rl.getLayoutParams().height = 1;
                    return;
                }
                if (itemType == 2) {
                    ImageView rt = (ImageView) holder.findViewById(R.id.discover_right_top);
                    rt.setImageResource(R.drawable.ic_section_week_rank_more);
                }
                TextView title = (TextView) holder.findViewById(R.id.discover_title);
                title.setText(data.get(position).getTitle());
                //======================== 子项数据 ========================
                //子项的adapter
                DisItemAdapter itemAdapter = null;
                itemAdapter = new DisItemAdapter(getActivity(), data.get(position).getTopics(), data.get(position).getBanners(), itemType);
                //根据type设置不同布局
                RecyclerView recyclerView = (RecyclerView) holder.findViewById(R.id.discover_rcl);
                int poff = 0;
                switch (itemType) {
                    case 2: {
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(staggeredGridLayoutManager);
                        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                        layoutParams.height = DensityUtil.dip2px(getActivity(), 220);
                        recyclerView.setAdapter(itemAdapter);
                        //设置页面滚动差值
                        poff = getActivity().getResources().getDisplayMetrics().widthPixels - DensityUtil.dip2px(getActivity(),316);
                    }
                    break;
                    case 4: {
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                        layoutParams.height = DensityUtil.dip2px(getActivity(), 450);
                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.setAdapter(itemAdapter);
                    }
                    break;
                    case 5: {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                        layoutParams.height = DensityUtil.dip2px(getActivity(), 160);
                        recyclerView.setAdapter(itemAdapter);
                        //设置页面滚动差值
                        poff = getActivity().getResources().getDisplayMetrics().widthPixels - DensityUtil.dip2px(getActivity(),296);
                    }
                    break;
                    case 6: {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
                        layoutParams.height = DensityUtil.dip2px(getActivity(), 120);
                        recyclerView.setAdapter(itemAdapter);
                        //设置页面滚动差值
                        poff = getActivity().getResources().getDisplayMetrics().widthPixels - DensityUtil.dip2px(getActivity(),206);
                    }
                    break;
                }
                PagingScrollHelper helper = new PagingScrollHelper();
                helper.setUpRecycleView(recyclerView);
                helper.setPageoffset(poff);
                helper.setOnPageChangeListener(new PagingScrollHelper.onPageChangeListener() {
                    @Override
                    public void onPageChange(int index) {

                    }
                });
            }
        };

        lv.setAdapter(adapter);
    }
}
