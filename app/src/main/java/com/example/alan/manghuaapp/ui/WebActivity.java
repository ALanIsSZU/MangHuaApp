package com.example.alan.manghuaapp.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alan.manghuaapp.R;
import com.example.alan.manghuaapp.Untils.KeyWord;

/**
 * Created by Alan on 2016/12/21.
 */

public class WebActivity extends AppCompatActivity{

    WebView webView;
    ImageView iv;
    TextView tv;
    AnimationDrawable anim;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);
        Intent intent = getIntent();
        String url = intent.getStringExtra(KeyWord.URL);
        setupViews(url);
    }


    public void setupViews(String url) {
        webView= (WebView) findViewById(R.id.Tiao_web);
        iv= (ImageView) findViewById(R.id.web_iv);
        tv= (TextView) findViewById(R.id.wb_tv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
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

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//
//                Intent intent=new Intent(WebActivity.this,Web_Sencond_Activity.class);
//                intent.putExtra(KeyWord.URL_Tiao,url);
//                startActivity(intent);
//                return true;
//            }
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
