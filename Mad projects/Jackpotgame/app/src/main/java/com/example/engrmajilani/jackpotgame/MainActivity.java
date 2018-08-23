package com.example.engrmajilani.jackpotgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txteasy,txtmedium,txthard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txteasy = (TextView)findViewById(R.id.txteasy);
        txtmedium = (TextView)findViewById(R.id.txtmedium);
        txthard = (TextView)findViewById(R.id.txthard);

        txteasy.setOnClickListener(this);
        txtmedium.setOnClickListener(this);
        txthard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==txteasy){
            Intent i = new Intent(MainActivity.this,Easymode.class);
            startActivity(i);
        }
        if(v==txtmedium){
            Intent i = new Intent(MainActivity.this,Mediummode.class);
            startActivity(i);
        }
        if(v==txthard){
            Intent i = new Intent(MainActivity.this,Hardmode.class);
            startActivity(i);
        }

    }
}
