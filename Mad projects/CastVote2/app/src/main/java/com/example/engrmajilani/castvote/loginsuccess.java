package com.example.engrmajilani.castvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginsuccess extends AppCompatActivity implements View.OnClickListener{
    TextView txtback;
    Button btncast,btnhelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);

        txtback = (TextView)findViewById(R.id.txtback);

        btncast = (Button)findViewById(R.id.btncast);
        btnhelp = (Button)findViewById(R.id.btnhelp);

        btncast.setOnClickListener(this);
        btnhelp.setOnClickListener(this);

        txtback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btncast){
            Intent i = new Intent(loginsuccess.this,partylistview.class);
            startActivity(i);
            finish();
        }
        if(v==btnhelp){
            Intent i = new Intent(loginsuccess.this,Help.class);
            startActivity(i);
        }
        if(v==txtback){
            finish();
        }
    }
}
