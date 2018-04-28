package com.example.farhan.myspecialist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by raj on 14/3/18.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(SplashActivity.this, SignUpActivity.class);
                startActivity(i);
                finish();

            }
        },SPLASH_TIMEOUT);

    }
}