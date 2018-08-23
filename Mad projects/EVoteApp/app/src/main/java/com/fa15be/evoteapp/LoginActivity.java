package com.fa15be.evoteapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
//
//    private Button buttonRegister;
//    private EditText editTextEmail;
//    private EditText editTextPassword;
//    private TextView textSignin;
//    private ProgressDialog progressDialog;
//    private FirebaseAuth firebaseAuth;

    private EditText cnic;
    private EditText password;
    private FirebaseAuth firebaseAuth;
    String cnic1, password1, NIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();


        password = (EditText) findViewById(R.id.ed_Pass);
        Button btnLogin = (Button)findViewById(R.id.btnSignup);
        cnic = (EditText) findViewById(R.id.ed_Mail);


        TextView txtReg = (TextView) findViewById(R.id.RegistrationBtn);
        txtReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private boolean validate(){

        boolean valid = true;

        if(cnic1.isEmpty())
        {
            cnic.setError("Please Enter Your Cnic");
            valid = false;
        }
        if(password1.isEmpty())
        {
            password.setError("Please Enter Your Password");
            valid = false;
        }
        return valid;

    }

    public void intialize(){
        cnic1 = cnic.getText().toString().trim();
        password1 = password.getText().toString().trim();
    }


        private void login() {

            intialize();
            if (!validate()) {
                Toast.makeText(LoginActivity.this, "Sign up has failed", Toast.LENGTH_SHORT).show();
            } else {
                onSignInSucess();
            }
        }

            private void onSignInSucess() {
                cnic1 = cnic.getText().toString();
                password1 = password.getText().toString();

                NIC = cnic1 + "@gmail.com";


                firebaseAuth.signInWithEmailAndPassword(NIC, password1)
                                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                // If sign in fails, display a message to the user. If sign in succeeds
                                                // the auth state listener will be notified and logic to handle the
                                                // signed in user can be handled in the listener.
                                                if (task.isSuccessful()) {
                                                    // there was an error
                                                    //hideProgressDialog();
                                                    Intent intent = new Intent(LoginActivity.this, CastVoteOrResultActivity.class);
                                                    intent.putExtra("UserName",cnic.getText().toString());
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Authentication failed, check your email and password or sign up", Toast.LENGTH_LONG).show();

                                                    //hideProgressDialog();
                                                }
                            }
                        });
            }
    }
