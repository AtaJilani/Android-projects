package com.example.engrmajilani.castvote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.URISyntaxException;

public class CastSuccess extends AppCompatActivity implements View.OnClickListener {
    TextView txtclose,txtvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_success);
        txtvalue = (TextView)findViewById(R.id.txtvalue);
        txtclose = (TextView)findViewById(R.id.txtclose);

        //txtvalue.setOnClickListener(this);
        txtclose.setOnClickListener(this);
        txtvalue.setText(getIntent().getStringExtra("party"));
    }

    @Override
    public void onClick(View v) {

        if(v==txtclose){
            finish();
        }
    }
}
