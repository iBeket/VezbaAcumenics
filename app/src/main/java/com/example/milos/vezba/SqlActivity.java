package com.example.milos.vezba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milos on 25-Jul-17.
 */

public class SqlActivity extends AppCompatActivity {
    private SglHelper sglHelper;
    private GridView gridView;
    public static ArrayList<String> ArrayofName = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        sglHelper = new SglHelper(this);
        sglHelper.addContact(new PhoneBook("Mentor", "063202"));
        sglHelper.addContact(new PhoneBook("Srki", "0365251"));
        sglHelper.addContact(new PhoneBook("Milos", "95252"));
        sglHelper.addContact(new PhoneBook("Sasa", "93524"));

        List<PhoneBook> contacts = sglHelper.getAllContacts();

        for (PhoneBook cn : contacts) {
            String log = " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

        sglHelper.getAllContacts();

        gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, ArrayofName);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
