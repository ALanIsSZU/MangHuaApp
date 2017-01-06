package com.example.alan.manghuaapp.fragement;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2016/12/21.
 */

public class TextModel2 extends ListFragment{

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> list=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            list.add("asdasdasdasdasdasd");
        }
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,list);
        setListAdapter(adapter);
    }
}
