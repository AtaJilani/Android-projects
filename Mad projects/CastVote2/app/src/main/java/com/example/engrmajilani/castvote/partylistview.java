package com.example.engrmajilani.castvote;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.session.PlaybackState;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class partylistview extends AppCompatActivity {
    ListView listlogo;
    String temp;
    String userID, name;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String NIC;
    String[] CNIC;


    private void Auth(FirebaseUser user) {
        userID = user.getUid();
        NIC = user.getEmail();
        CNIC = NIC.split("@");
        NIC = CNIC[0];
        Toast.makeText(this, "Vote Casted", Toast.LENGTH_SHORT).show();
        createData(userID, NIC, temp);
    }

    private void createData(String userID, String cnic, String name) {
        UserData user = new UserData(userID, cnic, name);
        databaseReference.child("Casted Votes").child(userID).setValue(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partylistview);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        String[] titles = {"MQM PAKISTAN","PTI","PMLN","PPP","APML","JUIF","JAMAT"};
//        String[] description = {"Cast To MQM PAKISTAN","Cast To PTI","Cast To PMLN","Cast To PPP","Cast To APML","Cast To JUIF","Cast To JAMAT"};
        Integer[] img = {R.drawable.mqm, R.drawable.p_ti, R.drawable.pmln, R.drawable.ppp, R.drawable.jamat, R.drawable.meme1, R.drawable.que};
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] description = getResources().getStringArray(R.array.description);


        CustomAdpter customAdpter = new CustomAdpter(this, titles, description, img);
        listlogo = (ListView) findViewById(R.id.listlogo);
        listlogo.setAdapter(customAdpter);

        listlogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                String item;
                item = parent.getItemAtPosition(position).toString();
                temp = item;
                AlertDialog.Builder alert = new AlertDialog.Builder(partylistview.this);
                alert.setTitle("Cast Vote");
                alert.setMessage("Are You Sure To Cast Vote To " + temp);
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        Toast.makeText(partylistview.this, "Selected", Toast.LENGTH_SHORT).show();

                        Toast.makeText(partylistview.this, temp, Toast.LENGTH_SHORT).show();

                        Auth(firebaseAuth.getCurrentUser());

                        //databaseReference.child("Votes").setValue(temp);
                        shift();
                    }
                });
                alert.show();
            }
        });
    }

    public void shift() {
        Intent i = new Intent(partylistview.this, CastSuccess.class);
        i.putExtra("party", temp);
        startActivity(i);
        finish();

    }

}
