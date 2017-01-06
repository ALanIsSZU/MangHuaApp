package com.example.alan.manghuaapp.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;

/**
 * Created by Alan on 2016/12/26.
 */

public class Web_Sencond_Activity extends AppCompatActivity{

    WebView webView;
    ImageView iv;
    TextView tv;
    AnimationDrawable anim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_sencond);
        Intent intent = getIntent();
        String url = intent.getStringExtra(KeyWord.URL_Tiao);
        Log.d("TAG","拿到的超链接："+ url);
        setupViews(url);

    }

    public void setupViews(String url) {

        webView= (WebView) findViewById(R.id.Tiao_web_sencond);
        iv= (ImageView) findViewById(R.id.web_iv_sencond);
        tv= (TextView) findViewById(R.id.wb_tv_sencond);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d("TAG", "加载的进度:" + newProgress);
                webView.setVisibility(View.INVISIBLE);
                tv.setVisibility(View.VISIBLE);
                iv.setVisibility(View.VISIBLE);
                anim= (AnimationDrawable) iv.getDrawable();
                anim.start();
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                tv.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
                iv.setVisibility(View.INVISIBLE);
                anim= (AnimationDrawable) iv.getDrawable();
                anim.stop();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Intent intent=new Intent(Web_Sencond_Activity.this,WebActivity.class);
                intent.putExtra(KeyWord.URL,url);

                startActivity(intent);
                return true;
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
