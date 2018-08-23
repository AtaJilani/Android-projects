package com.example.engrmajilani.q3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    Button btntoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        btntoast = (Button)findViewById(R.id.btntoast);

           btntoast.setOnClickListener(new View.OnClickListener() {
               @Override

               public void onClick(View v) {
                   if(checkBox1.isChecked()==false&&checkBox2.isChecked()==false&&checkBox3.isChecked()==false&&checkBox4.isChecked()==false&&checkBox5.isChecked()==false){
                       Toast.makeText(getBaseContext(),"Nothing is Selected",Toast.LENGTH_SHORT).show();
                   }
                   else
                   {
                       String str = null;

                       if(checkBox1.isChecked())
                       {
                           str=checkBox1.getText().toString();
                       }
                       if(checkBox2.isChecked())
                       {
                           if(str==null){
                               str=checkBox2.getText().toString();
                           }
                           else {
                               str = str.concat(checkBox2.getText().toString());
                           }
                       }
                       if(checkBox3.isChecked())
                       {
                           if(str==null){
                               str=checkBox3.getText().toString();
                           }
                           else {
                               str = str.concat(checkBox3.getText().toString());
                           }

                       }
                       if(checkBox4.isChecked())
                       {
                           if(str==null){
                               str=checkBox4.getText().toString();
                           }
                           else {
                               str = str.concat(checkBox4.getText().toString());
                           }
                       }
                       if(checkBox5.isChecked())
                       {
                           if(str==null){
                               str=checkBox5.getText().toString();
                           }
                           else {
                               str = str.concat(checkBox5.getText().toString());
                           }
                       }


                       Toast.makeText(getBaseContext(), str,Toast.LENGTH_SHORT).show();

                   }
               }
           });



    }
}
