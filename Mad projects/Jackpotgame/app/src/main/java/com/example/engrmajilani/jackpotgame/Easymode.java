package com.example.engrmajilani.jackpotgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Easymode extends AppCompatActivity {

    TextView txtpredict,txtcurrentscore,txthighscore,txtstatus;
    EditText editpredict;
    Button btnpredict,btnreset;
    String currentcountkey = "currentcount",highcountkey = "hightcount";
    int currentcount=0,highcount=0;

    SharedPreferences preferences;
    String sharedprefFile = "com.example.engrmajilani.jackpotgame";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easymode);

        txtpredict = (TextView)findViewById(R.id.txtpredict);
        txtcurrentscore = (TextView)findViewById(R.id.txtcurrentscore);
        txthighscore = (TextView)findViewById(R.id.txthighscore);
        txtstatus = (TextView)findViewById(R.id.txtstatus);
        editpredict = (EditText)findViewById(R.id.editget);
        btnpredict = (Button)findViewById(R.id.btnpredict);
        btnreset = (Button)findViewById(R.id.btnreset);
        preferences = getSharedPreferences(sharedprefFile,MODE_PRIVATE);


        Random random = new Random();
       final int r=1+random.nextInt(10);
       final String s = String.valueOf(r);

       // final Editable get;
        String get=editpredict.getText().toString();
         int n = 0;//= Integer.parseInt(get);
        try{
             n = Integer.parseInt(get);
        }catch (NumberFormatException e){

        }
        final int finalN = n;
        btnpredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i =1;
                for(;i<3;i++) {
                    if (s == editpredict.getText().toString()) {
                        txtstatus.setText("You Win!");
                        if(txtstatus.getText()=="You Win!"){
                            currentcount++;
                        }
                    } else {
                        txtstatus.setText("You Lost!");

                    }

                    editpredict.setText("");
                }
                txtpredict.setText(String.valueOf(r));
               // btnpredict.setOnClickListener(null);
            }
        });

        txtcurrentscore.setText(String.valueOf(currentcount));
        if(currentcount>=highcount){
            txthighscore.setText(String.valueOf(currentcount));
        }


        currentcount = preferences.getInt(currentcountkey,0);
        highcount = preferences.getInt(highcountkey,0);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentcount = 0;
                highcount = 0;
                txtcurrentscore.setText(String.valueOf(currentcount));
                txthighscore.setText(String.valueOf(highcount));

                SharedPreferences.Editor prefeditor = preferences.edit();
                prefeditor.clear();
                prefeditor.apply();
            }
        });
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor prefeditor = preferences.edit();
        prefeditor.putInt(currentcountkey,currentcount);
        prefeditor.putInt(highcountkey,highcount);
        prefeditor.apply();

    }


}
