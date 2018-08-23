package com.example.engrmajilani.castvote;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
    TextView txtregester,txtlogin,txtclose;
    EditText editname,editpassword;
    String name,password;
    private FirebaseAuth firebaseAuth;
    String cnic1, password1, NIC;
//    String uname = "votecast";
//    String pass = "cast123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //TypeCasting of edittext
        editname = (EditText)findViewById(R.id.editname);
        editpassword = (EditText)findViewById(R.id.editpassword); 

        //TypeCasting of textview
        txtregester = (TextView)findViewById(R.id.txtregester);
        txtlogin = (TextView)findViewById(R.id.txtlogin);
        txtclose = (TextView)findViewById(R.id.txtclose);

        //ClickListeners
        txtclose.setOnClickListener(this);
        txtregester.setOnClickListener(this);
        txtlogin.setOnClickListener(this);
    }

    public void initialize(){
        cnic1=editname.getText().toString().trim();
        password1=editpassword.getText().toString().trim();
    }

    public void onloginsuccess() {
//        Intent i = new Intent(login.this,partylistview.class);
//        startActivity(i);

        cnic1 = editname.getText().toString();
        password1 = editpassword.getText().toString();

        NIC = cnic1 + "@gmail.com";

        //Toast.makeText(this, NIC + password1, Toast.LENGTH_SHORT).show();


        firebaseAuth.signInWithEmailAndPassword(NIC, password1)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            // there was an error
                            //hideProgressDialog();
                            Intent intent = new Intent(login.this, loginsuccess.class);
                            intent.putExtra("UserName",editname.getText().toString());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(login.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();

                            //hideProgressDialog();
                        }
                    }
                });
    }


    public boolean validate(){

        boolean valid = true;
        if(cnic1.isEmpty()){
            editname.setError("Enter Valid User Name");
            valid = false;
        }

        if(password1.isEmpty()){
            editpassword.setError("Enter Valid Password");
            valid = false;
        }
        else{
            onloginsuccess();
        }
        return valid;
    }


    @Override
    public void onClick(View v) {
        if(v==txtlogin){
            initialize();
            if (!validate()) {
                Toast.makeText(login.this, "Sign up has failed", Toast.LENGTH_SHORT).show();
            } else {
                onloginsuccess();
            }
        }
        if(v==txtregester){
            Intent i = new Intent(login.this,regester.class);
            startActivity(i);
            finish();

        }
        if(v==txtclose){
            finish();
        }

    }
}
