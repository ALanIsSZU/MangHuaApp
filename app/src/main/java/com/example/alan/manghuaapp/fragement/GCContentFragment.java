package com.example.alan.manghuaapp.fragement;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
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

import com.example.alan.manghuaapp.Adapter.GCRecycleViewAdapter;
import com.example.alan.manghuaapp.Adapter.RMAdapter;
import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.ApiManager;
import com.example.alan.manghuaapp.Untils.DensityUtil;
import com.example.alan.manghuaapp.Untils.KeyWord;
import com.example.alan.manghuaapp.Untils.MyLog;
import com.example.alan.manghuaapp.bean.GCBean;
import com.example.alan.manghuaapp.inte.GCContentInterface;
import com.example.alan.manghuaapp.ui.AuthorActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/12/21 0021.
 */
public class GCContentFragment extends Fragment implements AbsListView.OnScrollListener {

    private String squareUrl;
    private SwipeRefreshLayout gc_refresh;
    private ListView gc_lv;
    private List<GCBean> data;
    private RMAdapter<GCBean> adapter;
    //获取屏幕宽度
    private int screenWidth;
    private int screenHeight;

    private Dialog mDialog;
    private String imgUrl;

    private float y1;

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

    int currentPosition = 0;
    PhotoView photoView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenWidth = DensityUtil.getScreenWidth(getActivity());
        screenHeight = DensityUtil.getScreenHeight(getActivity());
        Bundle bundle = getArguments();
        squareUrl = bundle.getString(GCFragment.SQUARE_URLS);
        getData(squareUrl);
        initData();
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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDialog!=null){
            mDialog.dismiss();
        }
    }

    List<String> imgsUrl;
    private void initData() {
        data = new ArrayList<>();
        imgsUrl = new ArrayList<>();
        adapter = new RMAdapter<GCBean>(getActivity(),data,R.layout.gc_lv_item) {
            @Override
            public void bandData(final int position, final ViewHolder holder) {
                final GCBean gcb = data.get(position);
                Log.d("TAGSSSE","data.positon="+position);
                MyLog.d("gcb="+gcb.toString());
                //头像
                ImageView avatar_url = (ImageView) holder.findViewById(R.id.avatar_url);
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
                picasso.load(gcb.getAvatar_url())
                        .transform(transformation).fit().placeholder(R.drawable.ic_common_placeholder_s)
                        .into(avatar_url);
                avatar_url.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentAuthor = new Intent(getActivity(), AuthorActivity.class);
                        intentAuthor.putExtra(KeyWord.AUTHOR,gcb.getAuthor_id());
                        startActivity(intentAuthor);
                    }
                });
                //名字
                TextView gc_nickname = (TextView) holder.findViewById(R.id.gc_nickname);
                gc_nickname.setText(gcb.getNickname());
                //标题
                TextView content_text = (TextView) holder.findViewById(R.id.content_text);
                content_text.setText(gcb.getText());
                //加载图片
                imgsUrl = gcb.getImages();
                MyLog.d("ABDCE","图片张数："+imgsUrl.size());
                RecyclerView recyclerView = (RecyclerView) holder.findViewById(R.id.recyclerView);
                Log.d("GCContentFragment", "++++++++++++");
                if (imgsUrl.size()!=0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    switch (imgsUrl.size()) {
                        case 1:
                        {
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                        }
                        break;
                        case 4:
                        {
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        }
                        break;
                        default:
                        {
                            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                        }
                        break;
                    }
                    final GCRecycleViewAdapter recycleViewAdapter = new GCRecycleViewAdapter(getActivity(), imgsUrl);
                    recyclerView.setAdapter(recycleViewAdapter);
                    recycleViewAdapter.setOnItemClickListener(new GCRecycleViewAdapter.ImageOnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
//                            currentPosition = position;
                            Log.d("TAGSSSE","position="+position);
                            mDialog.show();
                            Picasso.with(getActivity()).load(recycleViewAdapter.getList().get(position)).into(photoView);
                        }
                    });
                }else {
                    recyclerView.setVisibility(View.GONE);
                }

                //关注按钮点击事件
                Button bt_guanzhu = (Button) holder.findViewById(R.id.bt_guanzhu);
                TextView tv_time = (TextView) holder.findViewById(R.id.tv_time);
                //获取时间
                long updated_at = gcb.getUpdated_at();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                Date date = new Date(updated_at);
                String time = sdf.format(date);
                MyLog.d("时间time="+time);
                tv_time.setText(time);
                //点赞，评论数
                final TextView gc_likes_count = (TextView) holder.findViewById(R.id.gc_likes_count);
                gc_likes_count.setText(gcb.getLikes_count());
                TextView gc_comments_count = (TextView) holder.findViewById(R.id.gc_comments_count);
                gc_comments_count.setText(gcb.getComments_count());

                final CheckBox bt_gc_likes = (CheckBox) holder.findViewById(R.id.bt_gc_likes);
                bt_gc_likes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            Animation anim = AnimationUtils.loadAnimation(getActivity(),R.anim.activity_enter);
                            bt_gc_likes.startAnimation(anim);
                            gc_likes_count.setText(Integer.valueOf(gcb.getLikes_count())+1+"");
                        }else {
                            gc_likes_count.setText(gcb.getLikes_count());
                        }
                    }
                });
            }

            @Override
            public int getItemViewType(int position) {
                int feed_type = data.get(position).getFeed_type();
                return feed_type;
            }
        };
    }

    private void getData(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        GCContentInterface rmcInter = retrofit.create(GCContentInterface.class);
        Call<String> call = rmcInter.getSquareBean(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                MyLog.d("请求成功"+response.body());
                String values = response.body();
                List<GCBean> list = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(values);
                    JSONArray arr = json.getJSONObject("data").getJSONArray("feeds");
                    for (int i = 0;i<arr.length();i++){
                        JSONObject obj = arr.getJSONObject(i);
                        GCBean bean = new GCBean(obj);
                        list.add(bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data.clear();
                data.addAll(list);
                gc_refresh.setRefreshing(false);
                handler.sendEmptyMessageDelayed(0,1000);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.d("请求失败"+t.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gc_content,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    private void setupViews(View view) {
        gc_refresh = ((SwipeRefreshLayout) view.findViewById(R.id.gc_refresh));
        gc_refresh.setColorSchemeColors(new int[]{Color.YELLOW,Color.BLUE, Color.GREEN});
        gc_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(squareUrl);
            }
        });
        gc_refresh.post(new Runnable() {
            @Override
            public void run() {
                gc_refresh.setRefreshing(true);
                getData(squareUrl);
            }
        });
        gc_lv = ((ListView) view.findViewById(R.id.gc_lv));
        gc_lv.setAdapter(adapter);

        gc_lv.setOnScrollListener(this);

    }

    boolean scrollFlag = false;
    boolean isMore = false;
    int page = 1;
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

        if (scrollState==0&&isMore){
            page++;
            getMorePageData(squareUrl,page);
        }

    }
    private void getMorePageData(String url,int page) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        GCContentInterface rmcInter = retrofit.create(GCContentInterface.class);
        Call<String> call = rmcInter.getMorePage(url,page);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                MyLog.d("请求成功"+response.body());
                String values = response.body();
                List<GCBean> list = new ArrayList<>();
                try {
                    JSONObject json = new JSONObject(values);
                    JSONArray arr = json.getJSONObject("data").getJSONArray("feeds");
                    for (int i = 0;i<arr.length();i++){
                        JSONObject obj = arr.getJSONObject(i);
                        GCBean bean = new GCBean(obj);
                        list.add(bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data.addAll(list);
                gc_refresh.setRefreshing(false);
                handler.sendEmptyMessageDelayed(0,1000);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                MyLog.d("请求失败"+t.getMessage());
            }
        });
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        View firstView = gc_lv.getChildAt(firstVisibleItem);
        if (firstVisibleItem==0&&(firstView==null||firstView.getTop()==0)){
            gc_refresh.setEnabled(true);
        }else {
            gc_refresh.setEnabled(false);
        }

        if (scrollFlag){
            if (firstVisibleItem>lastVisiableItemPosition){
                EventBus.getDefault().post("1");
                Log.d("TAGSS",""+"11111111111111");
            } else if (firstVisibleItem<lastVisiableItemPosition) {
                EventBus.getDefault().post("0");
            }else {
                return;
            }
            lastVisiableItemPosition = firstVisibleItem;
        }

        if (firstVisibleItem+visibleItemCount==totalItemCount){
            isMore = true;
        }else{
            isMore = false;
        }
    }

}
