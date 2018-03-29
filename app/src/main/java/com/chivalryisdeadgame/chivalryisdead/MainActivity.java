package com.chivalryisdeadgame.chivalryisdead;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


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
        mywebView.getSettings().setAllowFileAccess(true);
        mywebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mywebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        mywebView.getSettings().setUserAgentString(AppData.UserAgent);
        mywebView.getSettings().setAppCacheEnabled(true);
        //Allow mixed content on Lollipop and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mywebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mywebView.setWebChromeClient(new WebChromeClient());
        mywebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null && url.startsWith("cid://")) {
                    String url2 = url.replace("cid://", "file:///android_asset/");
                    mywebView.loadUrl(url2);
                    return true;
                } else {
                    mywebView.loadUrl(url);
                    return true;
                }
            }
        });
        mywebView.loadUrl(AppData.URL);
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