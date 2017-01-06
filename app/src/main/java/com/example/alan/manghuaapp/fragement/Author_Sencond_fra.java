package com.example.alan.manghuaapp.fragement;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.APIMnager;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.Adapter.AuthorSencondAdapter;
import com.example.alan.manghuaapp.Untils.MyLog;
import com.example.alan.manghuaapp.bean.Author_Sencond_Bean;
import com.example.alan.manghuaapp.inte.ICFContet;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Alan on 2016/12/22.
 */

public class Author_Sencond_fra extends Fragment{

    RecyclerView recyclerView;
    PtrFrameLayout refresh;
    List<Author_Sencond_Bean.DataBean.FeedsBean> data;
    AuthorSencondAdapter adapter;

    //弹出图片
    Dialog mDialog;
    PhotoView photoView;
    //刷新
    int id;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String url){
        if(url!=null){
            mDialog.show();
            Picasso.with(getContext()).load(url).into(photoView);

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.author_sencond,container,false);
        EventBus.getDefault().register(this);
        data=new ArrayList<>();
        //刷新操作
        refresh= (PtrFrameLayout) view.findViewById(R.id.authorSencondRefresh);

        mDialog = new Dialog(getActivity(), R.style.picture_mode){
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.gc_popu);
                MyLog.d("TAGSSE","oncreate");
                photoView = (PhotoView) findViewById(R.id.photoView);
//                setImg(photoView);

                PhotoViewAttacher pva = new PhotoViewAttacher(photoView);
                pva.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                    @Override
                    public void onViewTap(View view, float x, float y) {
                        hide();
                    }
                });
            }

            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
                    mDialog.dismiss();
                    return true;
                }
                return super.onKeyDown(keyCode, event);
            }
        };

//        refresh.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                Log.d("TAG", "进来了刷新方法");
//                data.clear();
//                getData(id);
//
//            }
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return super.checkCanDoRefresh(frame, recyclerView, header);
//            }
//        });
        //创建recyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.authorSencondRecler);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter=new AuthorSencondAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);
        //拿到传过来的id
        Bundle bundle = getArguments();
        id = bundle.getInt(KeyWord.AUTHOR_life);
        getData(id);
        return view;
    }

    private void getData(int id) {

        final Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIMnager.AUTHOR_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ICFContet contet=retrofit.create(ICFContet.class);
        Call<Author_Sencond_Bean> call=contet.getAuthorDongTai(id+"");
        call.enqueue(new Callback<Author_Sencond_Bean>() {
            @Override
            public void onResponse(Call<Author_Sencond_Bean> call, Response<Author_Sencond_Bean> response) {
               Author_Sencond_Bean bean=response.body();
                data.addAll(bean.getData().getFeeds());
                adapter.notifyDataSetChanged();
//                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<Author_Sencond_Bean> call, Throwable t) {
//                refresh.refreshComplete();
            }
        });
    }
}
