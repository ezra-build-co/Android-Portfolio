package com.example.portoflio1;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WebViewActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "extra.url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // Ensures links open within the WebView
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript

        String url = getIntent().getStringExtra(EXTRA_URL);
        if (url != null && !url.isEmpty()) {
            webView.loadUrl(url);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Handle the back button press
        onBackPressed();
        return true;
    }
}