package com.example.engrmajilani.castvote;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class regester extends AppCompatActivity implements View.OnClickListener{
    TextView txtback;
    EditText editname,editpassword,editrepassword,editphone,editpostcode,editcnic,editdob,editaddress;
    Button btnregester;
    String name,password,email,phone,postcode,cnic,dob,address,mobileno,repass;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        editname = (EditText)findViewById(R.id.editname);
        editpassword = (EditText)findViewById(R.id.editpassword);
        editrepassword = (EditText)findViewById(R.id.editemail);
        editphone = (EditText)findViewById(R.id.editphone);
        editcnic = (EditText)findViewById(R.id.editcnic);
        editaddress = (EditText)findViewById(R.id.editaddress);

        btnregester = (Button) findViewById(R.id.btnregester);
        btnregester.setOnClickListener(this);

        txtback = (TextView)findViewById(R.id.txtback);
        txtback.setOnClickListener(this);




    }

    public void regester(){
        initialize();
        if(!validate()){
            Toast.makeText(this, "Please Insert valid information", Toast.LENGTH_SHORT).show();
        }else {
            onSignupSuccess();
        }
    }

    public void onSignupSuccess(){
//        Intent i = new Intent(regester.this,Mobnumverif.class);
//        startActivity(i);



        String NIC = cnic + "@gmail.com";

        //Toast.makeText(this, NIC + password, Toast.LENGTH_SHORT).show();

        firebaseAuth.createUserWithEmailAndPassword(NIC, password)
                .addOnCompleteListener(regester.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(regester.this, "Authentication failed." + task.getException(),
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

        createData(userID, name, cnic, password, phone);

        Intent i = new Intent(regester.this, Mobnumverif.class);
        startActivity(i);
        finish();
    }

    private void createData(String userID, String name, String cnic, String pass, String mobileno) {
        UserData user = new UserData(userID, name, cnic, pass, mobileno);

        databaseReference.child("Users").child(userID).setValue(user);

    }

    public boolean validate(){

        boolean valid = true;
        if(name.isEmpty()||name.length()>32){
            editname.setError("Please Enter Valid Name");
            valid = false;
        }

        if(password.isEmpty() || password.length()>10){
            editpassword.setError("Please Enter Valid Password");
            valid = false;
        }
        if((repass.equals(password))){

            valid =true;
        }else
        {
            editrepassword.setError("Please Enter Valid Email");
            valid = false;
        }
//        if(postcode.isEmpty()){
//            editpostcode.setError("Please Enter Valid Postcode");
//            valid = false;
//        }
        if(cnic.isEmpty()){
            editcnic.setError("Please Enter Valid CNIC");
            valid = false;
        }
//        if(dob.isEmpty()){
//            editdob.setError("Please Enter Valid Date Of Birth");
//            valid = false;
//        }
        if(address.isEmpty()){
            editaddress.setError("Please Enter Valid Address");
            valid = false;
        }
        if(mobileno.isEmpty()){
            editphone.setError("Please Enter Valid Phone Number");
        }
        else {
            onSignupSuccess();
        }

        return valid;
    }

    public void initialize(){
        name = editname.getText().toString().trim();
        password = editpassword.getText().toString().trim();
        repass = editrepassword.getText().toString().trim();
        mobileno = editphone.getText().toString().trim();
//        //postcode = editpostcode.getText().toString().trim();
        cnic = editcnic.getText().toString().trim();
//        //dob = editname.getText().toString().trim();
        address = editaddress.getText().toString().trim();

//        String name = editname.getText().toString().trim();
//        String cnic = editcnic.getText().toString().trim();
//        String password = editpassword.getText().toString().trim();
//       String repass = editrepassword.getText().toString().trim();
//        String mobileno = editphone.getText().toString().trim();

    }

    @Override
    public void onClick(View v) {
        if(v==btnregester){
            regester();

        }
        if(v==txtback){
            finish();
        }
    }
}
