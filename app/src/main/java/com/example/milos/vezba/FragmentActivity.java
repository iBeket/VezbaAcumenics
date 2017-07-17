package com.example.milos.vezba;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1992;
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private Fragment fragment;
    private Button photo;
    private Button video;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        init();

        //permissions for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            photo.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragment = new FragmentClass1();
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            });
            video.setVisibility(View.GONE);
        } else {
            fragment = new FragmentClass2();
            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                    }
                }
            });
            photo.setVisibility(View.GONE);
        }
    }

    //permissions for camera
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                photo.setEnabled(true);
                video.setEnabled(true);
            }
        }
    }
    public void init(){
        photo = (Button) findViewById(R.id.button_fragment_photo);
        video = (Button) findViewById(R.id.button_fragment_video);
    }
}
