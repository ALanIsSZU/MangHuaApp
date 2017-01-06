package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.bean.Author_Sencond_Bean;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.alan.manghuaapp.R.id.recyclerView;

/**
 * Created by Alan on 2016/12/23.
 */

public class AuthorSencondAdapter extends RecyclerView.Adapter<AuthorSencondAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Author_Sencond_Bean.DataBean.FeedsBean> data;
    Context context;
    //添加图片额网址的集合

    public AuthorSencondAdapter(Context context, List<Author_Sencond_Bean.DataBean.FeedsBean> data) {
        this.context = context;
        this.data = data;
        inflater=LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.author_sencond_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //对图片进行裁剪剪成圆形（用户头像）
        Picasso picasso=Picasso.with(context);

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
        picasso.load(data.get(position).getUser().getAvatar_url()).transform(transformation).fit().into(holder.userHead);
        //获得时间
        long updated_at = data.get(position).getUpdated_at();
        SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm");
        Date date=new Date(updated_at);
        String end_time=sdf.format(date);
        holder.time.setText(end_time);

        holder.userName.setText(data.get(position).getUser().getNickname());
        holder.like.setText(data.get(position).getLikes_count()+"");
        holder.say.setText(data.get(position).getComments_count()+"");
        holder.contentTv.setText(data.get(position).getContent().getText());

        //获取图片的内容(在recyclerView里面动态加载图片 )
        String url = data.get(position).getContent().getImage_base();
        List<String> imagesUrl = data.get(position).getContent().getImages();
        List<String> imgs=new ArrayList<>();
        for (String s : imagesUrl) {
            String Http=url+s;
            imgs.add(Http);
            Log.d("TAG", "作者拿到的图片的地址为："+Http);
        }
        Log.d("TAG", "imagesUrl.size():" + imagesUrl.size());

            switch (imagesUrl.size()){
                case 0:
                    break;
                case 1:{
                    holder.recyclerView.setLayoutManager(new GridLayoutManager(context,1));
                }
                break;
                case 4:{
                    holder.recyclerView.setLayoutManager(new GridLayoutManager(context,2));
                }
                break;
                default:{
                    holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                }
                break;
            }
            AuthorDongTaiAdapter adapter=new AuthorDongTaiAdapter(imgs,context);
            holder.recyclerView.setAdapter(adapter);
        }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView userHead;
        TextView userName,contentTv,like,say,time;
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            userHead= (ImageView) itemView.findViewById(R.id.anthorSencodeHead);
            userName= (TextView) itemView.findViewById(R.id.anthorSencodeName);
            contentTv= (TextView) itemView.findViewById(R.id.anthorSencodeContent);
            like= (TextView) itemView.findViewById(R.id.anthorSencodeLike);
            say= (TextView) itemView.findViewById(R.id.anthorSencodSay);
            time= (TextView) itemView.findViewById(R.id.anthorSencodeTime);
            recyclerView= (RecyclerView) itemView.findViewById(R.id.anthorSencodeItemRecycle);

        }
    }

}
