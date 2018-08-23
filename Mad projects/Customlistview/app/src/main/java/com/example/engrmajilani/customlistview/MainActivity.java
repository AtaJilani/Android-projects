package com.example.engrmajilani.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView stdlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //String[] students = {"Hamza","Umair","Raza","Saad","Imran","Sarim","Salman","Hamza","Umair","Raza","Saad","Imran","Sarim","Salman"};
       // ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,students);
        String[] titles = getResources().getStringArray(R.array.titels);
        String[] description = getResources().getStringArray(R.array.description);
//        String[] titles = {"MQM PAKISTAN","PTI","PMLN","PPP","APML","JUIF","JAMAT"};
//        String[] description = {"Cast To MQM PAKISTAN","Cast To PTI","Cast To PMLN","Cast To PPP","Cast To APML","Cast To JUIF","Cast To JAMAT"};
        Integer[] img = {R.drawable.mqm,R.drawable.pti,R.drawable.pmln,R.drawable.ppp,R.drawable.jamat,R.drawable.meme1,R.drawable.kaf};

        CustomAdapter customAdapter = new CustomAdapter(this,titles,description,img);

        //ArrayAdapter arrayAdapter =new CustomAdapter(this,titles);
        stdlist = (ListView)findViewById(R.id.stdlist);
        stdlist.setAdapter(customAdapter);
    }
}
