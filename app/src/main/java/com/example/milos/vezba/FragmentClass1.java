package com.example.milos.vezba;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentClass1 extends Fragment {
    private Button photo;
    private ImageView imageFr;
    private static final int CAMERA_REQUEST = 1992;
    private Bitmap bmp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.actyvity_fragment_photo, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageFr = (ImageView) view.findViewById(R.id.picture_fra);
        photo = (Button) view.findViewById(R.id.button_fragment_photo);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {

            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            imageFr.setImageBitmap(bmp);
        }
    }

}
