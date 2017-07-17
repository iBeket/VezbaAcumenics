package com.example.milos.vezba;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button button;
    Context context;
    private String passString = "uspeh";
    private int num = 5;
    private boolean buttonClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonClicked = true;

                Intent myIntent = new Intent(context, SecondActivity.class);
                myIntent.putExtra("stringPass", passString);
                myIntent.putExtra("intPass", num);
                myIntent.putExtra("boolButton", buttonClicked);

                startActivity(myIntent);

            }
        });
    }
}
