package com.example.portoflio1;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Portfolio");
        }

        findViewById(R.id.button1).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Screen1Activity.class)));
        findViewById(R.id.button2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Screen2Activity.class)));
        findViewById(R.id.button3).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Screen3Activity.class)));
        findViewById(R.id.button4).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Screen4Activity.class)));
    }
}