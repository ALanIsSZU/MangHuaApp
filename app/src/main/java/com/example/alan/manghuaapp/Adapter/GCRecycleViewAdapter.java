package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.DensityUtil;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Administrator on 2016/12/23 0023.
 */
public class GCRecycleViewAdapter extends RecyclerView.Adapter<GCRecycleViewAdapter.ViewHolder> {

    List<String> data ;
    LayoutInflater inflater;
    Context context;

    public GCRecycleViewAdapter(Context context,List<String> data) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public List<String> getList(){
        return data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.img_recycle,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String img = data.get(position);
        Picasso.with(context).load(img)
                .resize(DensityUtil.dip2px(context,100),DensityUtil.dip2px(context,100))
                .placeholder(R.drawable.ic_common_placeholder_l)
                .error(R.drawable.img_error)
                .into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgListener!=null){
                    imgListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size()==0?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_recycle);
        }
    }

    /**
     * item 点击事件
     */
    public interface ImageOnItemClickListener{
        public void onItemClick(View view, int position);
    }

    private ImageOnItemClickListener imgListener;
    public void setOnItemClickListener(ImageOnItemClickListener imgListener){
        this.imgListener = imgListener;
    }
}
