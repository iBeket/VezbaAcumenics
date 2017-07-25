package com.example.milos.vezba;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Milos on 25-Jul-17.
 */

public class SharedPreferenceActivity extends AppCompatActivity {
    EditText name, phoneNum;
    Button save;

    private String strName;
    private String strPhoneNum;
    private SharedPreferences sharedpreferences;

    public static final String PREFS_NAME = "MyPrefsFile";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        init();

        sharedpreferences = getSharedPreferences(PREFS_NAME, 0);

        String getName = sharedpreferences.getString(Name, null);
        String getPhoneNum = sharedpreferences.getString(Phone, null);

        name.setText(getName);
        phoneNum.setText(getPhoneNum);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strName = name.getText().toString();
                strPhoneNum = phoneNum.getText().toString();
                Toast.makeText(SharedPreferenceActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, strName);
        editor.putString(Phone, strPhoneNum);
        editor.commit();
    }

    public void init() {
        name = (EditText) findViewById(R.id.editText_name);
        phoneNum = (EditText) findViewById(R.id.editText_phoneNum);
        save = (Button) findViewById(R.id.button_save);
    }
}
