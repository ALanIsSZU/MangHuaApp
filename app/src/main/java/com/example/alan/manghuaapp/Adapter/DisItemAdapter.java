package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.DiscoverBean;
import com.example.alan.manghuaapp.ui.Count_Activity;
import com.example.alan.manghuaapp.ui.WebActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ly on 2016/12/21.
 */

public class DisItemAdapter extends RecyclerView.Adapter<DisItemAdapter.MyViewHolder> {

    Context context;
    //数据
    List<DiscoverBean.DataBean.InfosBean.TopicsBean> topicsBeen;
    List<DiscoverBean.DataBean.InfosBean.BannersBean> bannersBeen;
    /**
     * 每周点击排行榜  2,      新作出炉  5,      人气飙升  4,        主编力推  4,
     * 1分钟轻松短漫！  5,     少年热血  4,      耽美BL    4,        少女纯爱  4,
     * 官方活动       6,      绝美古风  4,      恐怖悬疑  4,        轻松爆笑  4,    完结佳作  5,
     */
    int[] types = {2, 4, 5, 6};
    /**
     * 传入的类型
     */
    int type;

    public DisItemAdapter(Context context, List<DiscoverBean.DataBean.InfosBean.TopicsBean> topicsBeen, List<DiscoverBean.DataBean.InfosBean.BannersBean> bannersBeen, int type) {
        this.context = context;
        this.type = type;
        Log.d("DisItemAdapter", "==================type:" + type);
        this.topicsBeen = topicsBeen;
        this.bannersBeen = bannersBeen;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type) {
            case 2: {
                MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dis_two_item, parent, false));
                return holder;
            }
            case 4: {
                MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dis_four_item, parent, false));
                return holder;
            }
            case 5: {
                MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dis_five_item, parent, false));
                return holder;
            }
            case 6: {
                MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.dis_six_item, parent, false));
                return holder;
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        int target_id = 0;
        switch (type) {
            case 2: {
                DiscoverBean.DataBean.InfosBean.TopicsBean bean = topicsBeen.get(position);
                Picasso.with(context).load(bean.getPic()).resize(256, 128).
                        placeholder(R.drawable.ic_common_placeholder_s).into(holder.iv);
                holder.author.setText(bean.getUser().getNickname());
                holder.ranking.setText(position + 1 + "");
                holder.title.setText(bean.getTitle());
                target_id = bean.getTarget_id();
            }
            break;
            case 4: {
                DiscoverBean.DataBean.InfosBean.TopicsBean bean = topicsBeen.get(position);
                Picasso.with(context).load(bean.getPic()).resize(256, 512).
                        placeholder(R.drawable.ic_common_placeholder_s).into(holder.iv);
                holder.author.setText(bean.getUser().getNickname());
                holder.title.setText(bean.getTitle());
                target_id = bean.getTarget_id();
            }
            break;
            case 5: {
                DiscoverBean.DataBean.InfosBean.TopicsBean bean = topicsBeen.get(position);
                Picasso.with(context).load(bean.getPic()).resize(256, 512).
                        placeholder(R.drawable.ic_common_placeholder_s).into(holder.iv);
                holder.author.setText(bean.getUser().getNickname());
                holder.title.setText(bean.getTitle());
                holder.description.setText(bean.getDescription());
                target_id = bean.getTarget_id();
            }
            break;
            case 6: {
                DiscoverBean.DataBean.InfosBean.BannersBean bean = bannersBeen.get(position);
                Picasso.with(context).load(bean.getPic()).resize(512, 256).
                        placeholder(R.drawable.ic_common_placeholder_s).into(holder.iv);
                holder.title.setText(bean.getTarget_title());
                target_id = bean.getTarget_id();
            }
            break;
        }

        //给图片设置点击事件
        holder.iv.setOnClickListener(new itemClicklisten(itemViewType, target_id));

    }


    class itemClicklisten implements View.OnClickListener {

        int itemViewType, target_id;

        public itemClicklisten(int itemViewtype, int target_id) {
            this.target_id = target_id;
            this.itemViewType = itemViewtype;
        }

        @Override
        public void onClick(View view) {
            if (target_id == 0) {
                return;
            }
            if (itemViewType == 2) {
                Intent intent = new Intent(context, Count_Activity.class);
                intent.putExtra(KeyWord.ID, target_id);
                context.startActivity(intent);
            } else {
            Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra(KeyWord.URL, "http://www.kuaikanmanhua.com/web/comic/" + target_id + "/");
                context.startActivity(intent);
            }
        }
    }


    @Override
    public int getItemCount() {
        return type == 6 ? bannersBeen.size() : topicsBeen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type == 6 ? bannersBeen.get(position).getType() : topicsBeen.get(position).getType();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView ranking;
        TextView title;
        TextView author;
        TextView description;

        public MyViewHolder(View view) {
            super(view);
            switch (type) {
                case 2: {
                    iv = (ImageView) view.findViewById(R.id.dis_two_iv);
                    ranking = (TextView) view.findViewById(R.id.dis_two_ranking);
                    title = (TextView) view.findViewById(R.id.dis_two_title);
                    author = (TextView) view.findViewById(R.id.dis_two_author);
                }
                break;
                case 4: {
                    iv = (ImageView) view.findViewById(R.id.dis_four_iv);
                    title = (TextView) view.findViewById(R.id.dis_four_title);
                    author = (TextView) view.findViewById(R.id.dis_four_author);
                }
                break;
                case 5: {
                    iv = (ImageView) view.findViewById(R.id.dis_five_iv);
                    title = (TextView) view.findViewById(R.id.dis_five_title);
                    author = (TextView) view.findViewById(R.id.dis_five_author);
                    description = (TextView) view.findViewById(R.id.dis_five_description);
                }
                break;
                case 6: {
                    iv = (ImageView) view.findViewById(R.id.dis_six_iv);
                    title = (TextView) view.findViewById(R.id.dis_six_title);
                }
                break;
            }

        }
    }

}
