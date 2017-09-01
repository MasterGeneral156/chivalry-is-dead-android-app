package themastergeneral.chivalryisdead;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.winsontan520.wversionmanager.library.OnReceiveListener;
import com.winsontan520.wversionmanager.library.WVersionManager;

public class MainActivity extends AppCompatActivity
{
    private static WebView mywebView;
    public final Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VersionChecker.runChecker(this);

        mywebView = (WebView) findViewById(R.id.webview);
        mywebView.setWebChromeClient(new WebChromeClient());
        mywebView.setWebViewClient(new CustomWebViewClient());
        mywebView.getSettings().setDatabaseEnabled(true);
        mywebView.getSettings().setJavaScriptEnabled(true);
        mywebView.getSettings().setDomStorageEnabled(true);
        mywebView.getSettings().setUserAgentString(AppData.UserAgent);
        mywebView.getSettings().setAppCacheEnabled(true);
        mywebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mywebView.loadUrl(AppData.URL);
    }
    @Override
    public void onBackPressed()
    {
        if(mywebView.canGoBack())
        {
            mywebView.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    protected void onRestart()
    {
        super.onRestart();
        VersionChecker.runChecker(this);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        VersionChecker.runChecker(this);
    }
}