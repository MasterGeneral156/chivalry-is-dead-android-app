package com.chivalryisdeadgame.chivalryisdead;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.logging.Handler;


public class MainActivity extends Activity
{
    WebView mywebView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mywebView = (WebView) findViewById(R.id.webview);
        mywebView.getSettings().setDatabaseEnabled(true);
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setDomStorageEnabled(true);
        mywebView.getSettings().setUserAgentString(AppData.UserAgent);
        mywebView.getSettings().setAppCacheEnabled(true);
        mywebView.loadUrl(AppData.URL);
        mywebView.setWebChromeClient(new WebChromeClient());
        mywebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        VersionChecker.runChecker(this);
    }
    @Override
    public void onBackPressed() {
        if(mywebView.canGoBack()){
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}