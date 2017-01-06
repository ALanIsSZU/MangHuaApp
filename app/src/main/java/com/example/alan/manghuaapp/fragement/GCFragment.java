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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/21 0021.
 *
 */
public class GCFragment extends Fragment {

    static final String SQUARE_URLS = "square";
    private TabLayout gc_tab;
    private ViewPager gc_viewPager;
    private List<Fragment> fragments;
    private String[] table = {"热门","最新"};
    private String[] apiVsq = {"2","1"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gc_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    private void setupView(View view) {
        gc_tab = (TabLayout) view.findViewById(R.id.gc_tablayout);
        gc_viewPager = (ViewPager) view.findViewById(R.id.gc_viewPager);

        fragments = new ArrayList<>();
        for (int i=0;i<apiVsq.length;i++){
            GCContentFragment gccf = new GCContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SQUARE_URLS,apiVsq[i]);
            gccf.setArguments(bundle);
            fragments.add(gccf);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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
                return table[position];
            }
        };
        gc_viewPager.setAdapter(adapter);
        gc_tab.setupWithViewPager(gc_viewPager);

    }
}
