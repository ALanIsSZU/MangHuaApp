package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/12/19.
 */

public class ClassifcationFragment extends Fragment{

    String[] titles={
            "全部","恋爱","耽美","恐怖","古风","爆笑","奇幻","校园","都市","少年","治愈","百合","完结"
    };
    int[] id={
            0,20,36,32,46,24,22,47,48,49,27,45,40
    };
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.classifcation_layout,container,false);

        viewPager= (ViewPager) view.findViewById(R.id.classiFcationViewPager);
        tabLayout = (TabLayout) view.findViewById(R.id.classiFcationTablayout);
        fragments=new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            ClassifcationContentFragment content=new ClassifcationContentFragment();
            Bundle bundle=new Bundle();
            bundle.putInt(KeyWord.KEY,id[i]);
            content.setArguments(bundle);
            fragments.add(content);
        }
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
