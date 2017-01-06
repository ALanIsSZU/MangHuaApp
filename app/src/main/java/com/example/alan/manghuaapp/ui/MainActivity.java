package com.example.alan.manghuaapp.ui;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.fragement.DiscoverFragment;
import com.example.alan.manghuaapp.fragement.ClassifcationFragment;
import com.example.alan.manghuaapp.fragement.ManHuaFragment;
import com.example.alan.manghuaapp.fragement.MineFragment;
import com.example.alan.manghuaapp.fragement.VSheQuFragment;

public class MainActivity extends AppCompatActivity {

    FragmentTabHost tabHost;
    LayoutInflater inflater;
    String[] titles={
            "漫画","发现","分类","v社区","我的"
    };
    int[] imgs={
            R.drawable.tab_manhua,R.drawable.tab_discover,R.drawable.tab_fenlei,R.drawable.tab_shequ,R.drawable.tab_mine
    };
    private Class[] fragments = {ManHuaFragment.class, DiscoverFragment.class, ClassifcationFragment.class,
            VSheQuFragment.class, MineFragment.class};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
    }

    private void setUpView() {
        inflater=LayoutInflater.from(this);
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,getSupportFragmentManager(),R.id.activity_fragment);

        for (int i = 0; i < titles.length; i++) {
            TabHost.TabSpec tabItem=tabHost.newTabSpec(i+"");
            tabItem.setIndicator(getItemView(i));

            tabHost.addTab(tabItem,fragments[i],null);
//            tabHost去掉边线
            tabHost.getTabWidget().setDividerDrawable(android.R.color.transparent);
        }
    }

    private View getItemView(int index) {

        View view=inflater.inflate(R.layout.tab_item_layout,null);
        ImageView iv= (ImageView) view.findViewById(R.id.tab_item_iv);
        TextView tv= (TextView) view.findViewById(R.id.tab_item_tv);

        iv.setImageResource(imgs[index]);
        tv.setText(titles[index]);
        return view;
    }
}
