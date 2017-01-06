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
 * Created by Administrator on 2016/12/20 0020.
 *
 */
public class ReMenFragment extends Fragment {

    static final String API_URLS = "urls";
    private TabLayout rm_tab;
    private ViewPager rm_viewPager;
    private List<Fragment> fragments;
    private String[] tables = {"周一","周二","周三","周四","周五","周六","周日"};
    private String[] apiWeekens = {"0", "1481558400",
            "1481644800", "1481731200", "1481817600", "1481904000", "1"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remen_fragment,container,false);
        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        rm_tab = ((TabLayout) view.findViewById(R.id.rm_tablayout));
        rm_viewPager = ((ViewPager) view.findViewById(R.id.rm_viewPager));

        fragments = new ArrayList<>();
        for (int i=0;i<tables.length;i++){
            RMContentFragment rmcf = new RMContentFragment();
            Bundle bundle = new Bundle();
            bundle.putString(API_URLS,apiWeekens[i]);
            rmcf.setArguments(bundle);
            fragments.add(rmcf);
        }
//        MyLog.d("fragments大小"+fragments.size());

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
                return tables[position];
            }
        };
        rm_viewPager.setAdapter(adapter);
        rm_tab.setupWithViewPager(rm_viewPager);

    }

}
