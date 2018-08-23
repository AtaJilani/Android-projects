package com.example.engrmajilani.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btnresult;
    TextView txtresult;
    EditText editnum1,editnum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnresult = (Button)findViewById(R.id.btnresult);
        editnum1 = (EditText) findViewById(R.id.editnum1);
        editnum2 = (EditText) findViewById(R.id.editnum2);
        txtresult = (TextView)findViewById(R.id.txtresult);

        final String num1 = editnum1.getText().toString();
        final String num2 = editnum2.getText().toString();


        btnresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Num1 = Integer.parseInt(editnum1.getText().toString());
                int Num2 = Integer.parseInt(editnum2.getText().toString());
                int sum = Num1+Num2;
                txtresult.setText("Ans: " + String.valueOf(sum));
//                try{
//                    int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
//                    txtresult.setText(Integer.parseInt(editnum1.getText().toString()) + Integer.parseInt(editnum2.getText().toString()));
//                    Toast.makeText(MainActivity.this, sum, Toast.LENGTH_SHORT).show();
//
//                }catch (NumberFormatException e){
//                    Toast.makeText(MainActivity.this, "BC", Toast.LENGTH_SHORT).show();
//
//                }

            }
        });
    }
}
