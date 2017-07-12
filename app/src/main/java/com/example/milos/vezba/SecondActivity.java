package com.example.milos.vezba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by Milos on 05-Jul-17.
 */

public class SecondActivity extends AppCompatActivity {
    final Context context = this;
    Button button;
    Button button1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        if (null != intent) {

                Toast.makeText(SecondActivity.this,
                        intent.getStringExtra("stringPass"), Toast.LENGTH_LONG).show();
            int passInt = intent.getIntExtra("intPass", 0);
            System.out.println(passInt);
            Boolean clickButton = intent.getBooleanExtra("boolButton",false);
            System.out.println(clickButton);
        }

        button = (Button) findViewById(R.id.spam);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               onBackPressed();

            }
        });

        button1 = (Button) findViewById(R.id.accept);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1 = new Intent(SecondActivity.this, ListViewActivity.class);
                startActivity(intent1);

            }
        });



    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle("      Choose your next step");
        alertDialogBuilder
                .setMessage("                   Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SecondActivity.this.finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(SecondActivity.this,
                "OnStart", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(SecondActivity.this,
                "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(SecondActivity.this,
                "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(SecondActivity.this,
                "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(SecondActivity.this,
                "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(SecondActivity.this,
                "onRestart", Toast.LENGTH_LONG).show();
    }*/
}
