package com.example.engrmajilani.listview;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView stdlist;
    String[] students = {"Ali","Zuhaib","Hamza","Umair","Samad","Ali","Zuhaib","Hamza","Umair","Samad","Ali","Zuhaib","Hamza","Umair","Samad"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stdlist = (ListView) findViewById(R.id.stdlist);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,students);
        stdlist.setAdapter(arrayAdapter);
        stdlist.setOnItemClickListener(this);

//        stdlist = (ListView) findViewById(R.id.stdlist);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,students);
//        stdlist.setAdapter(arrayAdapter);
//        stdlist.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

        TextView tv = (TextView)view;
       // Toast.makeText(this, "You Click On "+tv.getText()+(++i), Toast.LENGTH_SHORT).show();
        alertDialog.setMessage(tv.getText());
        alertDialog.show();
    }
}
