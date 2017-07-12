package com.example.milos.vezba;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


/**
 * Created by Milos on 11-Jul-17.
 */

public class BlogActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private TextView title, description;
    private ImageView image;
    private Button zoom;
    private Bitmap bmp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        init();


        //receive information from CustomAdapter2
        final Intent receivedIntent = getIntent();
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmp!=null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, getImageUri(getApplicationContext(), bmp));
                    startActivity(intent);
                }else {
                    Uri uri =  Uri.parse(receivedIntent.getStringExtra("image"));
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
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
            bmp = (Bitmap) extras.get("data");
            image.setImageBitmap(bmp);
        }

    }
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public void init() {
        title = (TextView) findViewById(R.id.titleBlog);
        description = (TextView) findViewById(R.id.descriptionBlog);
        image = (ImageView) findViewById(R.id.imageViewBlog);
        zoom = (Button) findViewById(R.id.zoombutton);
    }

}
