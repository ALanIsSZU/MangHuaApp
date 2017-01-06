package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Adapter.AuthorFristAdapter;
import com.example.alan.manghuaapp.bean.Author_bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/12/22.
 */

public class Author_First_fra extends Fragment{
    Author_bean bean;
    TextView jieSao;
    TextView jieSaoContent;
    RecyclerView recyclerView;
    List<Author_bean.DataBean.TopicsBean> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.anthor_first,container,false);
        list=new ArrayList<>();
        jieSao= (TextView) view.findViewById(R.id.authorFristJieSao);
        jieSaoContent= (TextView) view.findViewById(R.id.authorFristJieSaoContent);
        recyclerView= (RecyclerView) view.findViewById(R.id.authorFirstRecyler);
        AuthorFristAdapter adapter=new AuthorFristAdapter(list,getContext());
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void getAuthorBean(Author_bean bean){
        this.bean=bean;
        list.addAll(bean.getData().getTopics());
        //获取文本的内容(没有内容让控件消失)
        if (bean.getData().getIntro()==null || bean.getData().getIntro()==""){
            jieSao.setVisibility(View.GONE);
            jieSaoContent.setVisibility(View.GONE);
        }else {
            jieSaoContent.setText(bean.getData().getIntro());
        }


    }
}
