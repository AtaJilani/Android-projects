package com.example.engrmajilani.q4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class nightmode extends AppCompatActivity implements View.OnClickListener {

    TextView txtcount1,txtcount2,txtmode;
    Button btnsub1,btnsub2,btnadd1,btnadd2;
    int count = 0,count2 = 0;
    String countkey1 = "countkey1",countkey2 ="countkey2";
    SharedPreferences sharedPreferences;
    String sharedpffile ="com.example.engrmajilani.q4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nightmode);


        txtcount1 = (TextView)findViewById(R.id.txtcount1);
        txtcount2 = (TextView)findViewById(R.id.txtcount2);
        txtmode = (TextView)findViewById(R.id.txtmode);
        btnadd1 = (Button)findViewById(R.id.btnadd1);
        btnadd2 = (Button)findViewById(R.id.btnadd2);
        btnsub1 = (Button)findViewById(R.id.btnsub1);
        btnsub2 = (Button)findViewById(R.id.btnsub2);

        btnadd1.setOnClickListener(this);
        btnadd2.setOnClickListener(this);
        btnsub1.setOnClickListener(this);
        btnsub2.setOnClickListener(this);
        txtmode.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(sharedpffile,MODE_PRIVATE);
        count = sharedPreferences.getInt(countkey1,0);
        txtcount1.setText(String.valueOf(count));
        count2 = sharedPreferences.getInt(countkey2,0);
        txtcount2.setText(String.valueOf(count2));

    }

    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putInt(countkey1, count);
        preferencesEditor.putInt(countkey2, count2);
        preferencesEditor.apply();
    }

    @Override
    public void onClick(View v) {
        if(v==btnadd1){
            txtcount1.setText(String.valueOf(count++));
        }
        if(v==btnadd2){
            txtcount2.setText(String.valueOf(count2++));
        }
        if(v==btnsub1){
            txtcount1.setText(String.valueOf(count--));
        }
        if(v==btnsub2){
            txtcount2.setText(String.valueOf(count2--));
        }
        if(v==txtmode){
            Intent i  = new Intent(nightmode.this,MainActivity.class);
            startActivity(i);
        }
    }
}
