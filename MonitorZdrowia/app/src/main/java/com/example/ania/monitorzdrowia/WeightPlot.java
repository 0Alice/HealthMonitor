package com.example.ania.monitorzdrowia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

import java.io.File;

public class WeightPlot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_plot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        File file=new File(this.getFilesDir(),"weight.txt");
        LineData lineData=new LineData();
        DataToHistory d=new DataToHistory(file,"waga",lineData,97);
        LineChart chart = (LineChart) findViewById(R.id.chartwei);
        chart.setData(d.lineData);
        chart.invalidate();
    }

}
