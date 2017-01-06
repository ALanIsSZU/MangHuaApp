package com.example.alan.manghuaapp.fragement;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alan.manghuaapp.Adapter.RMAdapter;
import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.ApiManager;
import com.example.alan.manghuaapp.Untils.DensityUtil;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.Untils.MyLog;
import com.example.alan.manghuaapp.bean.RMBean;
import com.example.alan.manghuaapp.inte.RMContentInterface;
import com.example.alan.manghuaapp.ui.AuthorActivity;
import com.example.alan.manghuaapp.ui.Count_Activity;
import com.example.alan.manghuaapp.ui.WebActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Administrator on 2016/12/20 0020.
 *
 */
public class RMContentFragment extends Fragment implements AbsListView.OnScrollListener {

    public static final String DETAIL_URL = "url";
    private List<RMBean> data;
    private RMAdapter<RMBean> adapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:{
                    adapter.notifyDataSetChanged();
                }break;
            }
        }
    };
    private ListView lv_rmcont;
    private SwipeRefreshLayout refresh;

    private String url;
    private int screenWidth;
    private int screenHeight;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenWidth = DensityUtil.getScreenWidth(getActivity());
        screenHeight = DensityUtil.getScreenHeight(getActivity());
        Bundle bundle = getArguments();
        url = bundle.getString(ReMenFragment.API_URLS);
        getData(url);
        //初始化数据源和adapter
        initData();
    }

    private void initData() {
        data = new ArrayList<>();
        adapter = new RMAdapter<RMBean>(getActivity(),data,R.layout.rm_lv_item) {
            @Override
            public void bandData(int position, ViewHolder holder) {
                final RMBean bean = data.get(position);
                MyLog.d("TAGSEE","添加数据data长度="+data.size());
                int type = bean.getType();
                MyLog.d("type="+type);

                //漫画类型按钮
                Button lable_text = (Button) holder.findViewById(R.id.lable_text);
                lable_text.setText(bean.getLabel_text());
                lable_text.setBackgroundResource(R.drawable.bt_table);
                lable_text.setTextColor(Color.parseColor(bean.getLabel_text_color()));
                lable_text.setBackgroundColor(Color.parseColor(bean.getLabel_color()));
                lable_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentCount = new Intent(getActivity(), Count_Activity.class);
                        intentCount.putExtra(KeyWord.ID,bean.getId());
                        startActivity(intentCount);
                    }
                });
                //标题
                TextView tv_title = (TextView) holder.findViewById(R.id.tv_title);
                tv_title.setText(bean.getTitle());
                //用户名
                TextView tv_user = (TextView) holder.findViewById(R.id.tv_user);
                tv_user.setText(bean.getNickname());
                tv_user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentAuthor = new Intent(getActivity(), AuthorActivity.class);
                        intentAuthor.putExtra(KeyWord.AUTHOR,bean.getAuthor_id());
                        startActivity(intentAuthor);
                    }
                });
                //图片
                ImageView iv_cover = (ImageView) holder.findViewById(R.id.iv_cover);
                Picasso.with(getActivity()).load(bean.getCover_image_url())
                        .resize(screenWidth,DensityUtil.dip2px(getActivity(),210))
                        .placeholder(R.drawable.ic_common_placeholder_l)
                        .error(R.drawable.img_error)
                        .into(iv_cover);
                iv_cover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        MyLog.d("TAGSEE","url="+bean.getDetailUrl());
                        intent.putExtra(DETAIL_URL,bean.getDetailUrl());
                        startActivity(intent);
                    }
                });
                //漫画最新章节标题
                TextView tv_content = (TextView) holder.findViewById(R.id.tv_content);
                tv_content.setText(bean.getContent_title());
                //点赞和评论数量
                final TextView tv_likes = (TextView) holder.findViewById(R.id.likes_count);
                tv_likes.setText(bean.getLikes_count());
                TextView tv_count = (TextView) holder.findViewById(R.id.comments_count);
                tv_count.setText(bean.getComments_count());

                final CheckBox bt_likes = (CheckBox) holder.findViewById(R.id.bt_likes);
                bt_likes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.activity_enter);
                            bt_likes.startAnimation(anim);
                            tv_likes.setText(Integer.valueOf(bean.getLikes_count())+1+"");
                        }else {
                            tv_likes.setText(bean.getLikes_count());
                        }
                    }
                });

            }

            @Override
            public int getItemViewType(int position) {
                int typeNext = data.get(position).getType();
                return typeNext;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rm_content,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        lv_rmcont = ((ListView) view.findViewById(R.id.lv));
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        //下拉刷新
        refresh.setColorSchemeColors(new int[]{Color.YELLOW,Color.BLUE, Color.GREEN});
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(url);
            }
        });
        refresh.post(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(true);
                getData(url);
            }
        });
        ImageView iv_buttom = new ImageView(getActivity());
        iv_buttom.setImageResource(R.drawable.bg_recommend_guide_scroll);
        lv_rmcont.addFooterView(iv_buttom);
        lv_rmcont.setAdapter(adapter);
        lv_rmcont.setOnScrollListener(this);
    }

    private void getData(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RMContentInterface rmcInter = retrofit.create(RMContentInterface.class);
        Call<String> call = rmcInter.getWeekenBean(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                MyLog.d("请求成功"+response.body());
                String values = response.body();
                List<RMBean> list = new ArrayList<RMBean>();

                try {
                    JSONObject json = new JSONObject(values);
                    JSONArray arr = json.getJSONObject("data").getJSONArray("comics");
                    MyLog.d("arr长度="+arr.length());
                    for (int i=0;i< arr.length();i++){
                        JSONObject obj = arr.getJSONObject(i);
                        RMBean rmBean = new RMBean(obj);
                        list.add(rmBean);
                        MyLog.d("bean="+rmBean.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data.clear();
                data.addAll(list);
                handler.sendEmptyMessageDelayed(0,1000);
                refresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.d("请求失败"+t.getMessage());
            }
        });
    }

    boolean scrollFlag = false;
    int lastVisiableItemPosition = 0;
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://屏幕停止滚动式
                scrollFlag = false;

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动时
                scrollFlag = true;
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                scrollFlag = false;
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View firstView = lv_rmcont.getChildAt(firstVisibleItem);
        if (firstVisibleItem==0&&(firstView==null||firstView.getTop()==0)){
            refresh.setEnabled(true);
        }else{
            refresh.setEnabled(false);
        }

        if (scrollFlag){
            if (firstVisibleItem>lastVisiableItemPosition){
                EventBus.getDefault().post("2");
                Log.d("TAGSS",""+"11111111111111");
            } else if (firstVisibleItem<lastVisiableItemPosition) {
                EventBus.getDefault().post("3");
            }else {
                return;
            }
            lastVisiableItemPosition = firstVisibleItem;
        }
    }
}
