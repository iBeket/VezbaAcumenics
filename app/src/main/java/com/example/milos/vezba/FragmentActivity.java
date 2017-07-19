package com.example.milos.vezba;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentActivity extends AppCompatActivity {

    private Button photo;
    private Button video;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        photo = (Button) findViewById(R.id.button_fragment_photo);
        video = (Button) findViewById(R.id.button_fragment_video);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setAdapter(pageAdapter);

        //permissions for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            photo.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
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
}