package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.DensityUtil;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.ui.WebActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Alan on 2016/12/23.
 */

public class AuthorDongTaiAdapter extends RecyclerView.Adapter<AuthorDongTaiAdapter.ViewHodler>{

    LayoutInflater inflater;
    List<String> list;
    Context context;

    public AuthorDongTaiAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    //监听事件
    public interface  SencondAdapterItemClickListener{
        void onItemClick(View view,int position);
    } ;

    private SencondAdapter.SencondAdapterItemClickListener onItemClickListener;

    public void setOnItemClickLisetener(SencondAdapter.SencondAdapterItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void onItemClick(View view,int position){
        EventBus.getDefault().post(list.get(position));
    }


    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.dongdtai_picture_item,null);
        return new ViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ViewHodler holder, final int position) {
        Picasso.with(context).load(list.get(position)).resize(DensityUtil.dip2px(context,200),DensityUtil.dip2px(context,150)).placeholder(R.drawable.ic_common_placeholder_s).into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder{
        ImageView iv;
        public ViewHodler(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.dongTaiIvItem);
        }
    }
}
