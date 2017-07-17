package com.example.milos.vezba;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Milos on 17-Jul-17.
 */

public class FragmentClass1 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     return inflater.inflate(R.layout.actyvity_fragment_photo,container,false);
    }
}
