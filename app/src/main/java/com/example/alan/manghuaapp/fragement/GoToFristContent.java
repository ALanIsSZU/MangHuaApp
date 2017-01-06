package com.example.alan.manghuaapp.fragement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.bean.GoToBean;
import com.example.alan.manghuaapp.ui.AuthorActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.w3c.dom.Text;

/**
 * Created by Alan on 2016/12/20.
 */

public class GoToFristContent extends Fragment{

    GoToBean bean;
    TextView content;
    ImageView userHeand;
    TextView userName;
    RelativeLayout layout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.goto_frist,container,false);
        bean=new GoToBean();
        //内容
        content= (TextView) view.findViewById(R.id.goToFristContent);
        Log.d("TAG", "初始化frist里面的元素");

        //用户名
        userName= (TextView) view.findViewById(R.id.goToFristUserName);
        userHeand= (ImageView) view.findViewById(R.id.goToFristUserHead);
        //找到布局
        layout= (RelativeLayout) view.findViewById(R.id.goToRelative);
        return view;
    }


    public void getFridtInfo(final GoToBean bean){
        this.bean=bean;
        userName.setText(bean.getData().getUser().getNickname());
        content.setText(bean.getData().getDescription());

        //对图片进行裁剪剪成圆形（用户头像）
        Picasso picasso=Picasso.with(getActivity());

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
        picasso.load(bean.getData().getUser().getAvatar_url()).transform(transformation).fit().placeholder(R.drawable.ic_common_placeholder_s).into(userHeand);

        //作者布局点击事件
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AuthorActivity.class);
                intent.putExtra(KeyWord.AUTHOR,bean.getData().getUser().getId());
                startActivity(intent);
            }
        });
    }

}
