package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Alan on 2016/12/19.
 */

public abstract class ManHuaAdapter<T> extends BaseAdapter {

    //数据源
    List<T> data;
    //LayoutInflater
    LayoutInflater inflater;
    //布局资源
    int[] layoutId;

    //构造方法

    public ManHuaAdapter(Context context, List<T> data, int... layoutId) {
        this.layoutId = layoutId;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return layoutId.length;
    }

    //抽象绑定数据的方法
    public abstract void bindData(int position, ViewHolder holder);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //得到当前数据布局类型
        int layoutIndex = getItemViewType(position);
        if (convertView == null) {
            convertView = inflater.inflate(layoutId[layoutIndex], parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //绑定数据
        bindData(position, holder);


        return convertView;
    }

    public static class ViewHolder {
        //保存的控件：是需要设置值的控件
        private View view;

        public ViewHolder(View view) {
            this.view = view;
        }


        //向子类提供一个方法，返回需要设置值的控件
        public View findViewById(int viewId) {
            //根据viewid，找到对应的控件
            return view.findViewById(viewId);
        }

    }

}
