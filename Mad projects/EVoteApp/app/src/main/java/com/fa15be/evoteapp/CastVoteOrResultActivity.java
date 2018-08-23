package com.fa15be.evoteapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class CastVoteOrResultActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_vote_or_result);

        String username;
        Intent intent = getIntent();
        username = intent.getStringExtra("UserName");
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        String[] userlogout =  {"User Name: " + username,"Logout"};


//        userlogout = getResources().getStringArray(R.array.userlogout);
//        Arrays.copyOf(userlogout, userlogout.length + 1);
//        userlogout[userlogout.length - 1] = username;

        final Button BtnCast = (Button)findViewById(R.id.CastBtn);
        final Button BtnResult = (Button)findViewById(R.id.ResultBtn);

        final Spinner SpnLo = (Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String >(this , android.R.layout.simple_spinner_item,userlogout);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnLo.setAdapter(arrayAdapter);

        BtnCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CastVoteOrResultActivity.this,ListViewOfPartiesForVoteActivity.class);
                startActivity(intent);
            }
        });

        BtnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CastVoteOrResultActivity.this,ListViewOfPartiesForResultActivity.class);
                startActivity(intent);
            }
        });

        SpnLo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Display Selected option
                if(parent.getItemAtPosition(position).toString().equals("Logout")) {
                    Intent i = new Intent(CastVoteOrResultActivity.this,LoginActivity.class);
                    startActivity(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
