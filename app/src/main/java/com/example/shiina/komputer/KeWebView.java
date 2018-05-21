package com.example.shiina.komputer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class KeWebView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_web_view);



        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://pemrograman-web.ti.ulm.ac.id/Kelompok13/pages/Faq/Template-FAQ/");
        WebSettings webSettings = myWebView.getSettings();
        myWebView.clearCache(true);
        webSettings.setJavaScriptEnabled(true);
    }
}
