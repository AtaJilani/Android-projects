package com.example.engrmajilani.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtcount;
    Button btncount;
    EditText edittxt;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcount = (TextView) findViewById(R.id.txtcount);
        btncount = (Button) findViewById(R.id.btncount);
        edittxt = (EditText) findViewById(R.id.edittxt);

        btncount.setOnClickListener(this);
        btncount.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txtcount.setText(String.valueOf(count++));
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        txtcount.setText(String.valueOf(count++));
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString("txt", edittxt.getText().toString());
        savedInstanceState.putString("txt1", txtcount.getText().toString());
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String s = savedInstanceState.getString("txt");
        String s1 = savedInstanceState.getString("txt1");
        Log.d("enregistred value", s);
        edittxt.setText(s);
        txtcount.setText(String.valueOf(s1));

    }


}