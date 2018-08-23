package com.example.engrmajilani.q2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtcount;
    Button btncount,btntoast;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtcount = (TextView)findViewById(R.id.txtcount);
        btncount = (Button)findViewById(R.id.btncount);
        btntoast = (Button)findViewById(R.id.btntoast);

        btntoast.setOnClickListener(this);
        btncount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btncount){
            txtcount.setText(String.valueOf(count++));
        }
        if(v==btntoast){
            Intent i = new Intent(MainActivity.this,toastactivity.class);
            i.putExtra("count",txtcount.getText());
            startActivity(i);
            Toast.makeText(this, "Toasted "+txtcount.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
