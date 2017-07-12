package com.example.milos.vezba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * Created by Milos on 07-Jul-17.
 */

public class ListViewActivity extends AppCompatActivity {

    Context context;
    private CustomAdapter adapter;
    private ListView list;
    private ArrayList<ListModel> acumenics = new ArrayList<>();
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);
        list = (ListView) findViewById(R.id.information);
        context = this;


        for (i = 0; i < 20; i++) {
            ListModel listModel = new ListModel();
            if (i % 2 == 0) {
                listModel.setName("Milos");
                listModel.setImage("https://static.pexels.com/photos/36753/flower-purple-lical-blosso.jpg");
            } else {
                listModel.setName("Sasa");
                listModel.setImage("https://static.pexels.com/photos/36764/marguerite-daisy-beautiful-beauty.jpg");
            }
            acumenics.add(listModel);
        }

        adapter = new CustomAdapter(getApplicationContext(), R.layout.list_view_item, acumenics);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position % 2 == 0) {
                    Toast.makeText(getApplicationContext(), "par", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ListViewActivity.this, JasonActivity.class);
                    startActivity(intent1);
                }
            }
        });

    }
}
