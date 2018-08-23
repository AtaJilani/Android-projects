package com.example.engrmajilani.q2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class toastactivity extends AppCompatActivity {
    TextView txtgetcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toastactivity);

        txtgetcount = (TextView)findViewById(R.id.txtgetcount);
        txtgetcount.setText(getIntent().getStringExtra("count"));
    }
}
