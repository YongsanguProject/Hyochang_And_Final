package com.smtown.yongsangu_ar_project.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smtown.yongsangu_ar_project.MainActivity;
import com.smtown.yongsangu_ar_project.R;

public class WarningActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_activty);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WarningActivty.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }
}
