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
import com.example.alan.manghuaapp.bean.GoToBean;
import com.example.alan.manghuaapp.ui.WebActivity;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Alan on 2016/12/22.
 */

public class SencondAdapter extends RecyclerView.Adapter<SencondAdapter.ViewHolder>{

    LayoutInflater inflater;
    List<GoToBean.DataBean.ComicsBean> data;
    Context context;

    public SencondAdapter(List<GoToBean.DataBean.ComicsBean> data, Context context) {
        this.data = data;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }


    //监听事件
    public interface  SencondAdapterItemClickListener{
        void onItemClick(View view,int position);
    } ;

    private SencondAdapterItemClickListener onItemClickListener;

    public void setOnItemClickLisetener(SencondAdapterItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public void onItemClick(View view,int position){
        String url = data.get(position).getUrl();
        Intent intent=new Intent(context,WebActivity.class);
        intent.putExtra(KeyWord.URL,url);
        context.startActivity(intent);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.goto_second,null);
        return new ViewHolder(view);
    }
    //绑定数据
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context).load(data.get(position).getCover_image_url()).fit().placeholder(R.drawable.ic_common_placeholder_s).into(holder.contentIv);
        holder.title.setText(data.get(position).getTitle());
        if (data.get(position).getLikes_count()>10000){
            holder.like.setText(data.get(position).getLikes_count()/10000+"万");
        }else {
            holder.like.setText(data.get(position).getLikes_count()+"");
        }
        //获取时间
        int updta_time=data.get(position).getUpdated_at();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
        Date date=new Date(updta_time);
        String end_time=sdf.format(date);
        holder.time.setText(end_time);

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
        TextView title,like,time;
        ImageView contentIv;
        //对整个item进行监听
        View root;
        public ViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.goToSencondTitle);
            like= (TextView) itemView.findViewById(R.id.goToSecondLike);
            time= (TextView) itemView.findViewById(R.id.goToSencondTime);
            contentIv= (ImageView) itemView.findViewById(R.id.goTOSecondIv);

            root=itemView;
        }
    }
}
