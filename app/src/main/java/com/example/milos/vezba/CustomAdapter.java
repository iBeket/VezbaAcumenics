package com.example.milos.vezba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by Milos on 07-Jul-17.
 */

public class CustomAdapter extends ArrayAdapter <ListModel> {

    public CustomAdapter(Context context, int resource, ArrayList<ListModel> obj) {
        super(context,resource,obj);

    }

    /**
     * This method returns the row related view,
     * first, checks if the actual view (convertView) is set
     *
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Initialize the helper class
        ViewHolder viewHolder = null;

        /*
          If convertView is not set, inflate the row layout and get its views' references
          then set the helper class as a tag for the convertView
        */
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_view_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.textList);
            viewHolder.im = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }
        /*
          If convertView already exists, just get the tag and set it in the viewHolder attribute
        */
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Populate the row's layout
        ListModel obj = getItem(position);
        viewHolder.tv.setText(obj.getName());
        Picasso.with(getContext())
                .load(obj.getImage())
                .into(viewHolder.im);



        return convertView;
    }

    public static class ViewHolder {
        /* This is an helper class used to save
        *  each component of the listView row layout */
        public TextView tv;
        public ImageView im;
    }
}
