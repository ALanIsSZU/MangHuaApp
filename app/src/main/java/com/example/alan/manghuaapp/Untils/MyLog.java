package com.example.alan.manghuaapp.Untils;

import android.util.Log;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public class MyLog {

    /**
     * 统一Log
     */
    private static final boolean DEBUG = true;

    static final String TAG = "xiaohui";

    public static void d(String str){
        if (DEBUG){
            Log.d(TAG,str);
        }
    }

    public static void d(String tag,String str){
        if (DEBUG){
            Log.d(tag,str);
        }
    }

}
