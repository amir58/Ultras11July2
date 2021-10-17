package com.amirmohammed.ultras11july2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPref.init(getApplicationContext());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                    boolean rememberMe = preferences.getBoolean("rememberMe", false);

                    SharedPref.read("rememberMe", false);

                    Intent intent;
                    if (rememberMe){
                        intent = new Intent(SplashActivity.this, MainActivity.class);
                    }
                    else{
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }
}