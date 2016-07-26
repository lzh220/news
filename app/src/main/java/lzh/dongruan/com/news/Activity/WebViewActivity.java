package lzh.dongruan.com.news.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import lzh.dongruan.com.news.Base.BaseActivity;
import lzh.dongruan.com.news.DB.NewsDb;
import lzh.dongruan.com.news.R;
import lzh.dongruan.com.news.Until.News;


/**
 * Created by Administrator on 2016/7/17.
 */
public class WebViewActivity extends BaseActivity{
    WebView webview;
    String url;
    ImageView shoucang_img;
    ProgressBar  bar;
    Intent intent;
    News news;
    NewsDb newsDb;
    @Override
    public void setLayout() {
        setContentView(R.layout.webview_xml);
    }

    @Override
    public void indata() {
        if (getIntent()!=null){
            Bundle b =getIntent().getExtras();
            if (b!=null){
                url = b.getString("url");
                news= (News) b.getSerializable("news");
            }
        }


    }

    @Override
    public void getview() {
        bar= (ProgressBar) findViewById(R.id.web_progressbar);
        shoucang_img = (ImageView) findViewById(R.id.web_view_shoucangImg);
        webview = (WebView) findViewById(R.id.web_view);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        webview.loadUrl(url);
        //设置Web视图
        webview.setWebViewClient(new HelloWebViewClient ());
    }

    @Override
    public void setview() {

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
            }

        });
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {  //表示按返回键时的操作
                        webview.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });

        shoucang_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsDb =new NewsDb(WebViewActivity.this);
                newsDb.Add(news.getTitle(),news.getThumbnail_pic_s(),news.getUrl(),news.getDate(),news.getAuthor_name(),news.getThumbnail_pic_s03());
                Toast.makeText(WebViewActivity.this,"收藏",Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
