package com.example.milos.vezba;




import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;
import android.widget.VideoView;



/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentClass2 extends Fragment {
    private Button video;
    private static final int REQUEST_VIDEO_CAPTURE = 1;
    private VideoView videoFr;
    public Context context;
    Uri uri;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_video, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getActivity().getApplicationContext();

        videoFr = (VideoView) view.findViewById(R.id.videoView);
        video = (Button) view.findViewById(R.id.button_fragment_video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                takeVideoIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            }
        });
        videoFr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                ////  Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                if (uri != null) {
                    videoFr.setVideoURI(uri);
                    videoFr.requestFocus();
                    videoFr.start();
                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        uri = Uri.parse(data.getData().toString());
    }
}

