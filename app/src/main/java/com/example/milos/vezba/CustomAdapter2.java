
package com.example.milos.vezba;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.R.attr.animation;

/**
 * Created by Milos on 10-Jul-17.
 */

public class CustomAdapter2 extends ArrayAdapter<JasonModel> {
    private String letter = "";
    private ArrayList<JasonModel> obj = new ArrayList<>();
    private Context context;
    private int resource;
    private LayoutInflater inflater;


    public CustomAdapter2(Context context, int resource, ArrayList<JasonModel> obj) {
        super(context, resource, obj);
        this.context = context;
        this.resource = resource;
        this.obj = obj;

    }

    @Nullable
    @Override
    public JasonModel getItem(int position) {
        return this.obj.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    /**
     * This method returns the row related view,
     * first, checks if the actual view (convertView) is set
     */


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Initialize the helper class
        ViewHolder2 viewHolder = null;
        //creating animation for listview
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_left);
        /*
          If convertView is not set, inflate the row layout and get its views' references
          then set the helper class as a tag for the convertView
        */
           if (convertView == null ) {


        if (!obj.get(position).getTitle().substring(0, 1).equalsIgnoreCase(letter)) {

                inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                viewHolder = new ViewHolder2();
                convertView = inflater.inflate(R.layout.letter_layout, null);
                viewHolder.letterTv = (TextView) convertView.findViewById(R.id.letterTextView);
                letter = obj.get(position).getTitle().substring(0, 1).toUpperCase();


        } else {

                viewHolder = new ViewHolder2();
                convertView = inflater.inflate(R.layout.jason_list_item, null);
                inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.titlejson);
                viewHolder.tv1 = (TextView) convertView.findViewById(R.id.descriptionjson);
                viewHolder.im = (ImageView) convertView.findViewById(R.id.imageViewjson);
                viewHolder.linear = (LinearLayout) convertView.findViewById(R.id.listid);


       }

          convertView.setTag(viewHolder);
         }
        /*
          If convertView already exists, just get the tag and set it in the viewHolder attribute
        */
        else {
           viewHolder = (CustomAdapter2.ViewHolder2) convertView.getTag();
        }

        //Populate the row's layout
        final JasonModel obj = getItem(position);
        if (viewHolder.letterTv != null ) {
            viewHolder.letterTv.setText(letter);
        } else {
            viewHolder.tv.setText(obj.getTitle().substring(0, 1).toUpperCase() + obj.getTitle().substring(1));
            viewHolder.tv1.setText(obj.getDescription().substring(0, 1).toUpperCase() + obj.getDescription().substring(1));
            Picasso.with(getContext())
                    .load(obj.getImageJson().substring(0, 4) + "s" + obj.getImageJson().substring(4))
                    .into(viewHolder.im);


            //sending information`s to BlogActivity
            viewHolder.linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), BlogActivity.class);
                    intent.putExtra("title", obj.getTitle().substring(0, 1).toUpperCase() + obj.getTitle().substring(1));
                    intent.putExtra("description", obj.getDescription().substring(0, 1).toUpperCase() + obj.getDescription().substring(1));
                    intent.putExtra("image", obj.getImageJson().substring(0, 4) + "s" + obj.getImageJson().substring(4));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(intent);

                }
            });
            //starting animation
            // convertView.startAnimation(animation);
             letter = obj.getTitle().substring(0, 1).toUpperCase();
        }

        return convertView;
    }

    public class ViewHolder2 {
        /* This is an helper class used to save
        *  each component of the listView row layout */
        public TextView tv;
        public TextView tv1;
        public ImageView im;
        public LinearLayout linear;
        public TextView letterTv;
    }
}