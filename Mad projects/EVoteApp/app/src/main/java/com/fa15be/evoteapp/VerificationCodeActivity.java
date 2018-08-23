package com.fa15be.evoteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VerificationCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);

        final Button btnVeri = (Button)findViewById(R.id.VeriBtn);
        btnVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (VerificationCodeActivity.this, SplashScreenAfterVerifyActivity.class);
                startActivity(intent);
            }
        });
    }
}
