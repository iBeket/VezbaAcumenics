
package com.example.milos.vezba;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
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
    //  private String letter = "";
    private ArrayList<JasonModel> obj;
    private Context context;
    private int resource;
    private LayoutInflater inflater;
    private ScaleAnimation scale;


    public CustomAdapter2(Context context, int resource, ArrayList obj) {
        super(context, resource, obj);
        this.context = context;
        this.resource = resource;
        this.obj = obj;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return obj.size();
    }

    @Nullable
    @Override
    public JasonModel getItem(int position) {
        return this.obj.get(position);
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
        if (convertView == null) {


            convertView = inflater.inflate(resource, parent, false);

            viewHolder = new ViewHolder2();

            viewHolder.letterTv = (TextView) convertView.findViewById(R.id.letterTv);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.titlejson);
            viewHolder.tv1 = (TextView) convertView.findViewById(R.id.descriptionjson);
            viewHolder.im = (ImageView) convertView.findViewById(R.id.imageViewjson);
            viewHolder.linear = (LinearLayout) convertView.findViewById(R.id.listid);

            convertView.setTag(viewHolder);
        }
        /*
          If convertView already exists, just get the tag and set it in the viewHolder attribute
        */
        else {
            viewHolder = (CustomAdapter2.ViewHolder2) convertView.getTag();
        }


        //Populate the row's layout
        final JasonModel obj = this.obj.get(position);
        if ((position > 0) && (!obj.title.substring(0, 1).equalsIgnoreCase(this.obj.get(position - 1).getTitle().substring(0, 1)))) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
            viewHolder.letterTv.setText(obj.title.substring(0, 1).toUpperCase());
            viewHolder.tv.setText(obj.title.substring(0, 1).toUpperCase() + obj.title.substring(1));
            viewHolder.tv1.setText(obj.description.substring(0, 1).toUpperCase() + obj.description.substring(1));
            //   letter = obj.title.substring(0, 1);
        } else if (position == 0) {
            viewHolder.letterTv.setVisibility(View.VISIBLE);
            viewHolder.letterTv.setText(obj.title.substring(0, 1).toUpperCase());
            viewHolder.tv.setText(obj.title.substring(0, 1).toUpperCase() + obj.title.substring(1));
            viewHolder.tv1.setText(obj.description.substring(0, 1).toUpperCase() + obj.description.substring(1));
        } else {
            viewHolder.letterTv.setVisibility(View.GONE);
            viewHolder.letterTv.setText("");
            viewHolder.tv.setText(obj.title.substring(0, 1).toUpperCase() + obj.title.substring(1));
            viewHolder.tv1.setText(obj.description.substring(0, 1).toUpperCase() + obj.description.substring(1));
        }

        Picasso.with(getContext())
                .load(obj.getImageJson().substring(0, 4) + "s" + obj.getImageJson().substring(4))
                .into(viewHolder.im);

        //sending information`s to BlogActivity

        viewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BlogActivity.class);
                intent.putExtra("title", obj.title.substring(0, 1).toUpperCase() + obj.title.substring(1));
                intent.putExtra("description", obj.description.substring(0, 1).toUpperCase() + obj.description.substring(1));
                intent.putExtra("image", obj.getImageJson().substring(0, 4) + "s" + obj.getImageJson().substring(4));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        });

        //starting animation
        animateView();
        convertView.startAnimation(scale);


        return convertView;
    }

    public class ViewHolder2 {
        /* This is an helper class used to save
        *  each component of the listView row layout */
        public TextView tv, letterTv;
        public TextView tv1;
        public ImageView im;
        public LinearLayout linear;

    }

    private void animateView() {
        scale = new ScaleAnimation((float) 1.0, (float) 1.0, (float) 0.0, (float) 1.0);
        scale.setFillAfter(true);
        scale.setDuration(300);
    }
}
