package com.fa15be.evoteapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenAfterVerifyActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_after_verify);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run (){
                Intent loginIntent = new Intent(SplashScreenAfterVerifyActivity.this, CastVoteOrResultActivity.class);
                startActivity(loginIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
