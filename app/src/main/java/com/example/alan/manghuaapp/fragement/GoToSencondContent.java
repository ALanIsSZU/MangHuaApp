package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Adapter.SencondAdapter;
import com.example.alan.manghuaapp.bean.GoToBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/12/20.
 */

public class GoToSencondContent extends Fragment{

    GoToBean bean;
    List<GoToBean.DataBean.ComicsBean> data;
    RecyclerView recyclerView;
    SencondAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.goto_second_lv,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.goToSencondRecyclerView);
        data=new ArrayList<>();
        adapter=new SencondAdapter(data,getContext());
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //点击事件的监听（自己重写）
//        adapter.setOnItemClickLisetener(new SencondAdapter.SencondAdapterItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                String url = data.get(position).getUrl();
//                Intent intent=new Intent(getActivity(),WebActivity.class);
//                intent.putExtra(KeyWord.URL,url);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    public void getSencondInfo(GoToBean bean){
        Log.d("GoToSencondContent", "拿到了second的数据了");
        this.bean=bean;
        data.addAll(bean.getData().getComics());
        adapter.notifyDataSetChanged();
    }
}
