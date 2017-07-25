package com.example.milos.vezba;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Milos on 05-Jul-17.
 */

public class SecondActivity extends AppCompatActivity {
    final Context context = this;
    Button button;
    Button button1;
    Button button2;
    ImageView kruzic, tofej;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        final Intent intent = getIntent();
        if (null != intent) {

            Toast.makeText(SecondActivity.this,
                    intent.getStringExtra("stringPass"), Toast.LENGTH_LONG).show();
            int passInt = intent.getIntExtra("intPass", 0);
            System.out.println(passInt);
            Boolean clickButton = intent.getBooleanExtra("boolButton", false);
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
        button2 = (Button) findViewById(R.id.deny);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent deny = new Intent(SecondActivity.this, FragmentActivity.class);
                startActivity(deny);
            }
        });

        kruzic = (ImageView) findViewById(R.id.kruizc);
        kruzic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kruzic = new Intent(SecondActivity.this, SqlActivity.class);
                startActivity(kruzic);
            }
        });

        tofej = (ImageView) findViewById(R.id.picture);
        tofej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trf = new Intent(SecondActivity.this, SharedPreferenceActivity.class);
                startActivity(trf);
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
}
