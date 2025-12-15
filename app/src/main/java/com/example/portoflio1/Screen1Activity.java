package com.example.portoflio1;

import android.os.Bundle;

public class Screen1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Bio");
        }
    }
}