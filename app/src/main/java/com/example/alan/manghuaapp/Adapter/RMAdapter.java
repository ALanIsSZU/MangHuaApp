package com.example.alan.manghuaapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public abstract class RMAdapter<T> extends BaseAdapter {

    List<T> data;
    LayoutInflater inflater;
    int[] layoutIds ;

    public RMAdapter(Context context,List<T> data, int... layoutIds) {
        this.data = data;
        this.layoutIds = layoutIds;
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
        return layoutIds.length;
    }

    //抽象绑定数据的方法
    public abstract void bandData(int position,ViewHolder holder);
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(layoutIds[0],parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        bandData(position,holder);

        return convertView;
    }

    public static class ViewHolder{
        View view;

        public ViewHolder(View view) {
            this.view = view;
        }
        public View findViewById(int viewId){
            return view.findViewById(viewId);
        }
    }
}
