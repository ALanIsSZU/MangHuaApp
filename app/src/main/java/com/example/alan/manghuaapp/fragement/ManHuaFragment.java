package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.alan.manghuaapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class ManHuaFragment extends Fragment{

    private RadioGroup rg;
    private Button bt_search;
    private ViewPager viewPager;
    private RelativeLayout mh_rl_head;
    private List<Fragment> fragments;
    private ReMenFragment rmf;
    private GZFragment gzf;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mh_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        mh_rl_head = (RelativeLayout) view.findViewById(R.id.mh_rl_head);
        bt_search = (Button) view.findViewById(R.id.bt_search);
        rg = (RadioGroup) view.findViewById(R.id.rg);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        fragments = new ArrayList<>();
        rmf = new ReMenFragment();
        gzf = new GZFragment();
        fragments.add(rmf);
        fragments.add(gzf);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showItemView(checkedId);
            }
        });

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:{
                        rg.check(R.id.rb1);
                    }break;
                    case 1:{
                        rg.check(R.id.rb2);
                    }break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    Animation anim ;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String bool){
        Log.d("TAGSS", "bool:" + bool);
        if (bool.equals("2")){
            anim = AnimationUtils.loadAnimation(getActivity(),R.anim.activity_exit);
            mh_rl_head.startAnimation(anim);
            mh_rl_head.setVisibility(View.GONE);
        }else if (bool.equals("3")){
            anim = AnimationUtils.loadAnimation(getActivity(),R.anim.activity_enter);
            mh_rl_head.startAnimation(anim);
            mh_rl_head.setVisibility(View.VISIBLE);
        }

    }

    private void showItemView(int checkedId) {

        switch (checkedId){
            case R.id.rb1:{
                viewPager.setCurrentItem(0);
            }break;
            case R.id.rb2:{
                viewPager.setCurrentItem(1);
            }break;
        }
    }
}
