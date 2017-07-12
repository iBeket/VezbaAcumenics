package com.example.milos.vezba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



/**
 * Created by Milos on 11-Jul-17.
 */

public class BlogActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private TextView title, description;
    private ImageView image;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        init();

        //receive information from CustomAdapter2
        Intent receivedIntent = getIntent();
        title.setText(receivedIntent.getStringExtra("title"));
        description.setText(receivedIntent.getStringExtra("description"));
        Picasso.with(getApplicationContext())
                .load(receivedIntent.getStringExtra("image"))
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            //taking picture
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });

           //permissions for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            image.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

    }

    //permissions for camera
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                image.setEnabled(true);
            }
        }
    }
    //putting picture on image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {

            Bundle extras = data.getExtras();
            Bitmap bmp = (Bitmap) extras.get("data");
            image.setImageBitmap(bmp);
        }

    }

    public void init() {
        title = (TextView) findViewById(R.id.titleBlog);
        description = (TextView) findViewById(R.id.descriptionBlog);
        image = (ImageView) findViewById(R.id.imageViewBlog);
    }

}
