package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> {
            // This method will be executed once the timer is over
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            // close this activity
            finish();
        }, SPLASH_TIME_OUT);
    }
}