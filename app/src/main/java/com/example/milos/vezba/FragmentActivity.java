package com.example.milos.vezba;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;


/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentActivity extends AppCompatActivity {

    private Button photo;
    private Button video;
    private Button change;
    private boolean isClicked =false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        photo = (Button) findViewById(R.id.button_fragment_photo);
        video = (Button) findViewById(R.id.button_fragment_video);
        change = (Button) findViewById(R.id.change);
        //permissions for camera
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            photo.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentClass1 frg1 = new FragmentClass1();
        fragmentTransaction.replace(R.id.fragment_container, frg1);
        fragmentTransaction.commit();
        isClicked=true;
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    FragmentClass1 frg1 = new FragmentClass1();
                    fragmentTransaction.replace(R.id.fragment_container, frg1);
                    fragmentTransaction.commit();
                    isClicked=true;
                } else {
                    FragmentManager fragmentManager1 = getFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();

                    FragmentClass2 frg2 = new FragmentClass2();
                    fragmentTransaction1.replace(R.id.fragment_container, frg2);
                    fragmentTransaction1.commit();
                    isClicked=false;
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
                photo.setEnabled(true);
                video.setEnabled(true);
            }
        }
    }
}