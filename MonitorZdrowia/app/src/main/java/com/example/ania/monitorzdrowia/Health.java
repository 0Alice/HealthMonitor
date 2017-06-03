package com.example.ania.monitorzdrowia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Health extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinner=(Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> feelings= new ArrayList<String>();
        feelings.add("Bardzo złe");
        feelings.add("Złe");
        feelings.add("Raczej złe");
        feelings.add("Srednie");
        feelings.add("Raczej dobre");
        feelings.add("Dobre");
        feelings.add("Bardzo dobre");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, feelings);
        spinner.setAdapter(dataAdapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
