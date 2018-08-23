package com.example.engrmajilani.castvote;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;




class CustomAdpter extends ArrayAdapter<String> {


    String[] titles;
    String[] description;
    Integer[] images;
    Activity context;


    public CustomAdpter(@NonNull Activity context, String[] titles, String[] description, Integer[] images) {
        super(context, R.layout.custom_row,titles);

        this.context = context;
        this.titles = titles;
        this.description = description;
        this.images = images;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View customview;

        customview = convertView;
        ViewHolder viewHolder = null;

        if(customview == null){
            LayoutInflater inflater = context.getLayoutInflater();
            customview=inflater.inflate(R.layout.custom_row,null,true);
            viewHolder = new ViewHolder(customview);
                    customview.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) customview.getTag();
        }

        viewHolder.img.setImageResource(images[position]);
        viewHolder.txtname.setText(titles[position]);
        viewHolder.txtdesc.setText(description[position]);


        return customview;//super.getView(position, convertView, parent);
    }

    class ViewHolder{

        TextView txtname;
        TextView txtdesc;
        ImageView img;

        public ViewHolder(View v) {
            super();

            txtname = (TextView)v.findViewById(R.id.txtname);
            txtdesc = (TextView)v.findViewById(R.id.txtdesc);
            img = (ImageView)v.findViewById(R.id.imgLogo);
        }
    }
}
