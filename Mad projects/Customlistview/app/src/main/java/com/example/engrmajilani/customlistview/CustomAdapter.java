package com.example.engrmajilani.customlistview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Engr M A Jilani on 11/16/2017.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private String[] titles;
    private String[] description;
    private Integer[] imga;
    private Activity context;

    public CustomAdapter(@NonNull Activity context, @LayoutRes String[] titels,String[] description,Integer[] img) {
        super(context,R.layout.custom_row ,titels);



        this.context=context;
        this.titles = titels;
        this.description = description;
        this.imga = img;
    }



    public View getView(int position ,View convertView, ViewGroup parant){


        //LayoutInflater inflater= LayoutInflater.from(getContext());
        View customView ;//= inflater.inflate(R.layout.custom_row,parant,false);
        customView=convertView;
        ViewHolder viewHolder = null;

        if(customView==null){

            LayoutInflater inflater = context.getLayoutInflater();
            customView=inflater.inflate(R.layout.custom_row,null,true);
            viewHolder = new ViewHolder(customView);
            customView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.img.setImageResource(imga[position]);
        viewHolder.txtname.setText(titles[position]);
        viewHolder.txtdesc.setText(description[position]);

//        String studentnumber = getItem(position);
//        TextView txtstd= (TextView)customView.findViewById(R.id.txtstd);
//        ImageView imgstd = (ImageView)customView.findViewById(R.id.imgstd);
//
//        txtstd.setText(studentnumber);
//        imgstd.setImageResource(R.drawable.meme1);
//        imgstd.setImageResource(R.drawable.mqm);

        return customView;
    }

    class ViewHolder{

        TextView txtname;
        TextView txtdesc;
        ImageView img;

        public ViewHolder(View v) {
            super();

            txtname = (TextView)v.findViewById(R.id.txtname);
            txtdesc = (TextView)v.findViewById(R.id.txtdesc);
            img = (ImageView)v.findViewById(R.id.img);
        }
    }
}
