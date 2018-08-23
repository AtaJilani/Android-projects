package com.fa15be.evoteapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fa15be.evoteapp.Model.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class RegistrationActivity extends AppCompatActivity {

    private EditText ed_name, ed_cnic, ed_password, ed_repassword, ed_mobileno, ed_code;
    private String name, cnic, pass, repass, mobileno, code;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    DatabaseReference mDatabase;



    private static final String TAG = "PhoneAuth";

    private Button verifyButton;
    private Button sendButton;
    private Button resendButton;

    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;

    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        ed_name = (EditText)findViewById(R.id.txtName);
        ed_cnic = (EditText)findViewById(R.id.txtCnic);
        ed_password = (EditText)findViewById(R.id.txtPassword);
        ed_repassword = (EditText)findViewById(R.id.txtRepass);
        signup = (Button)findViewById(R.id.RegNxtbtn);

        ed_mobileno = (EditText) findViewById(R.id.txtMobileNo);
        ed_code = (EditText) findViewById(R.id.txtCode);
        verifyButton = (Button) findViewById(R.id.btnVerify);
        sendButton = (Button) findViewById(R.id.btnSend);
        resendButton = (Button) findViewById(R.id.btnResend);

        verifyButton.setEnabled(false);
        resendButton.setEnabled(false);

        fbAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
            }
        });
    }

//START HIA IDHER MOBILE AUTH

    public void sendCode(View view) {

        String phoneNumber = ed_mobileno.getText().toString();

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                verificationCallbacks);
    }


    private void setUpVerificatonCallbacks() {

        verificationCallbacks =
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(
                            PhoneAuthCredential credential) {

                        resendButton.setEnabled(false);
                        verifyButton.setEnabled(false);
                        ed_code.setText("");
                        signInWithPhoneAuthCredential(credential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            // Invalid request
                            Log.d(TAG, "Invalid credential: "
                                    + e.getLocalizedMessage());
                        } else if (e instanceof FirebaseTooManyRequestsException) {
                            // SMS quota exceeded
                            Log.d(TAG, "SMS Quota exceeded.");
                        }
                    }

                    @Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken token) {

                        phoneVerificationId = verificationId;
                        resendToken = token;

                        verifyButton.setEnabled(true);
                        sendButton.setEnabled(false);
                        resendButton.setEnabled(true);
                    }
                };
    }

    public void verifyCode(View view) {

        String code = ed_code.getText().toString();

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fbAuth.signInWithCredential(credential)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            signoutButton.setEnabled(true);
                            ed_code.setText("");
//                            statusText.setText("Signed In");
                            resendButton.setEnabled(false);
                            verifyButton.setEnabled(false);
                            FirebaseUser user = task.getResult().getUser();

                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(RegistrationActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                                ed_code.setText("");
                            }
                        }
                    }
                });
    }

    public void resendCode(View view) {

        String phoneNumber = ed_code.getText().toString();

        setUpVerificatonCallbacks();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,
                verificationCallbacks,
                resendToken);
    }


    //// END HAI IDHER MOBILE AUTH

    public void registration(){

        intialize();
        if(!validate()){
            Toast.makeText(RegistrationActivity.this,"Sign up has failed",Toast.LENGTH_SHORT).show();
        }
        else {

            onSignUpSucess();
        }
    }

    public void onSignUpSucess(){

//        Intent intent = new Intent (RegistrationActivity.this, MobileNoVerificationActivity.class);
//        startActivity(intent);

        String name = ed_name.getText().toString().trim();
        String cnic = ed_cnic.getText().toString().trim();
        String password = ed_password.getText().toString().trim();
        String repass = ed_repassword.getText().toString().trim();
        String mobileno = ed_mobileno.getText().toString().trim();

        String NIC = cnic + "@gmail.com";

        firebaseAuth.createUserWithEmailAndPassword(NIC, password)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            //hideProgressDialog();
                        } else {
                            Auth(task.getResult().getUser());
                            //hideProgressDialog();
                        }
                    }
                });

    }

    private void Auth(FirebaseUser user) {
        String userID = user.getUid();

        createData(userID, name, cnic, pass, mobileno);

        Intent i = new Intent(RegistrationActivity.this, SplashScreenAfterVerifyActivity.class);
        startActivity(i);
        finish();
    }

    private void createData(String userID, String name, String cnic, String pass, String mobileno) {
        UserData user = new UserData(userID, name, cnic, pass, mobileno);

        mDatabase.child("Users").child(userID).setValue(user);

    }

    public boolean validate(){

        boolean valid = true;

        if(name.isEmpty()||name.length()>20){

            ed_name.setError("Please Enter Your Name in between 20 words");
            valid = false;
        }

        if(cnic.isEmpty()||cnic.length()<13){

            ed_cnic.setError("Please Enter Correct CNIC");
            valid = false;
        }

        if(pass.isEmpty()||pass.length()<6){

            ed_password.setError("Please Enter Your Passord Approx 6 letters");
            valid = false;
        }

        if(repass.equals(pass)){

            valid = true;
        }
        else {

            ed_repassword.setError("Password not matched");
            valid = false;
        }
        if(mobileno.isEmpty()||mobileno.length()<11)
        {
            ed_mobileno.setError("Please Enter Correct Mobile No");
            valid = false;
        }

        return valid;
    }


    public void intialize(){

            name = ed_name.getText().toString().trim();
        cnic = ed_cnic.getText().toString().trim();
        pass = ed_password.getText().toString().trim();
        repass = ed_repassword.getText().toString().trim();
        mobileno = ed_mobileno.getText().toString().trim();
        code = ed_code.getText().toString().trim();
    }
}
