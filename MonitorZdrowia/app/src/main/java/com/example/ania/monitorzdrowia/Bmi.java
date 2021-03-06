package com.example.ania.monitorzdrowia;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class Bmi extends AppCompatActivity {
    TextView bmiText;
    TextView advice;
Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       c= getApplicationContext();
         bmiText=(TextView)findViewById(R.id.textView14);
advice=(TextView) findViewById(R.id.textView16);
       /* setContentView(R.layout.activity_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
     /*   TextView bmiText=(TextView)findViewById(R.id.textView14);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String w=preferences.getString("text_weight","1");
        double weight=Integer.parseInt(w);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String h=preferences1.getString("example_text2","1");
        double height=Integer.parseInt(h);

        Double bmi=weight/(height*height);
        if(bmi>0){
            bmiText.setText(bmi.toString());
        }
        else{
            bmiText.setText("brak danych");
        }*/

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
       String w=preferences.getString("text_weight","1");
        double weight=Double.parseDouble(w);
        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(c);
        String h=preferences1.getString("example_text2","1");
        double height=Double.parseDouble(h);
        Double bmi=weight/(height*height);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String string = nf.format( bmi );
        if(bmi>0){
            bmiText.setText(string.toString());
        }
        else{
            bmiText.setText("brak danych");
        }

        if(bmi<20){
            advice.setText("Masz niedowagę. \nSkontaktuj się z lekarzem.");
        }
        else if(bmi<25){
            advice.setText("Brawo! Twoja waga jest w normie. \nCwicz regularnie i trzymaj prawidłową dietę.");
        }
        else if(bmi<30){
            advice.setText("Masz nadwagę. \nNie załamuj się. Skontaktuj się z dietetykiem, odstaw słodycze. \nCwicz regularnie i trzymaj prawidłową dietę. ");
        }

        else if(bmi>40){
            advice.setText("Jesteś bardzo otyły! \nZmień dietę, zacznij ćwiczyć! \nTwoje życie jest zagrożone!");
        }
        else if(bmi>31){
            advice.setText("Jesteś otyły! \nSkontaktuj się z lekarzem! Zmień dietę, zacznij ćwiczyć!");
        }



    }

}
