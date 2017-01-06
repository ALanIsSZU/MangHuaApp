package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.Author_bean;
import com.example.alan.manghuaapp.ui.Count_Activity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Alan on 2016/12/23.
 */

public class AuthorFristAdapter extends RecyclerView.Adapter<AuthorFristAdapter.ViewHolder>{

    LayoutInflater inflater;
    List<Author_bean.DataBean.TopicsBean> data;
    Context context;

    //监听事件
    public interface  SencondAdapterItemClickListener{
        void onItemClick(View view,int position);
    } ;

    private SencondAdapter.SencondAdapterItemClickListener onItemClickListener;

    public void setOnItemClickLisetener(SencondAdapter.SencondAdapterItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    //监听能够实现的方法体
    public void onItemClick(View view,int position){
        int url=data.get(position).getId();
        Intent intent=new Intent(context, Count_Activity.class);
        intent.putExtra(KeyWord.ID,url);
        context.startActivity(intent);
    }


    public AuthorFristAdapter(List<Author_bean.DataBean.TopicsBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.anthor_first_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getCover_image_url()).fit().placeholder(R.drawable.ic_common_placeholder_s).into(holder.bookHead);
        holder.bookName.setText(data.get(position).getTitle());
        holder.BookContent.setText(data.get(position).getDescription());

        //监听事件的实现
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookHead;
        TextView bookName,BookContent;
        //对整个item进行监听
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            bookHead= (ImageView) itemView.findViewById(R.id.anthorFirstUserHeand);
            bookName= (TextView) itemView.findViewById(R.id.anthorFirstBookName);
            BookContent= (TextView) itemView.findViewById(R.id.anthorFirstBookContent);

            root=itemView;
        }
    }
}
