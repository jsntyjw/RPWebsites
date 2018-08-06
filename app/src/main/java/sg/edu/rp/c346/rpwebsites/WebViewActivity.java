package sg.edu.rp.c346.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    WebView wvMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wvMypage = findViewById(R.id.webViewMyPage);

        wvMypage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();
        String url = intentReceived.getStringExtra("url");

        WebSettings webSettings = wvMypage.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        wvMypage.loadUrl(url);
    }
}
