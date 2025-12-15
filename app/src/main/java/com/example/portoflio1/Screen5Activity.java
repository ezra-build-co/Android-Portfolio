package com.example.portoflio1;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class Screen5Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Credits");
        }


        // Find all the TextViews with URLs
        TextView url1 = findViewById(R.id.textView6);
        TextView url2 = findViewById(R.id.textView7);
        TextView url3 = findViewById(R.id.textView8);
        TextView url4 = findViewById(R.id.textView9);
        TextView url5 = findViewById(R.id.textView10);

        // Make them clickable
        setupClickableURL(url1);
        setupClickableURL(url2);
        setupClickableURL(url3);
        setupClickableURL(url4);
        setupClickableURL(url5);
    }

    private void setupClickableURL(TextView textView) {
        // Use Linkify to find the URL in the text
        Linkify.addLinks(textView, Linkify.WEB_URLS);

        // Create a new SpannableString to customize the click behavior
        SpannableString spannableString = new SpannableString(textView.getText());
        URLSpan[] spans = spannableString.getSpans(0, spannableString.length(), URLSpan.class);

        for (URLSpan urlSpan : spans) {
            // Create our own custom clickable span that launches the WebViewActivity
            MyURLSpan myURLSpan = new MyURLSpan(urlSpan.getURL());
            spannableString.setSpan(myURLSpan, spannableString.getSpanStart(urlSpan), spannableString.getSpanEnd(urlSpan), spannableString.getSpanFlags(urlSpan));
            spannableString.removeSpan(urlSpan); // Remove the default URLSpan
        }

        textView.setText(spannableString);
        textView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
    }

    // Custom URLSpan to launch our WebViewActivity
    private class MyURLSpan extends URLSpan {
        public MyURLSpan(String url) {
            super(url);
        }

        @Override
        public void onClick(View widget) {
            Intent intent = new Intent(Screen5Activity.this, WebViewActivity.class);
            intent.putExtra(WebViewActivity.EXTRA_URL, getURL());
            startActivity(intent);
        }
    }
}