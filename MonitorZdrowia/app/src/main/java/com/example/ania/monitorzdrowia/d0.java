package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class d0 extends AppCompatActivity {
Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        start=(Button)findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent=new Intent(v.getContext(),D1.class);
                startActivity(intent);
                }
            });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
