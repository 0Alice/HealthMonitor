package com.example.ania.monitorzdrowia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyData extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    File file;
    private static String personalData="personal_data.txt";
    Button saveButton;
    EditText age;
    EditText height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        age=(EditText)findViewById(R.id.editText2);
        height=(EditText)findViewById(R.id.editText3);
        Spinner spinner=(Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);
        List<String> female_male= new ArrayList<String>();
        female_male.add("Kobieta");
        female_male.add("Mężczyzna");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, female_male);
        spinner.setAdapter(dataAdapter);
        file=new File(this.getFilesDir(),personalData);
        saveButton=(Button)findViewById(R.id.button3);
        saveButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                try{
                    file.delete();
                    FileWriter fileWriter=new FileWriter(file,true);
                    BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
                    String data=age.getText().toString();
                    bufferedWriter.write(data);
                    data=height.getText().toString();
                    bufferedWriter.write(data);
                    bufferedWriter.close();
                    fileWriter.close();
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }

            }
        });
        StringBuffer stringBuffer= new StringBuffer();
        try{
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader=new BufferedReader(fileReader);
            int readData;
            while((readData=bufferedReader.read())!=-1){
                char data = (char) readData;
                stringBuffer.append(data);
            }
            bufferedReader.close();
            fileReader.close();
            age.setText("");
            age.setText(stringBuffer.toString());
        }catch (FileNotFoundException e1){
            e1.printStackTrace();
        } catch (IOException e1){
            e1.printStackTrace();
        }

     /*   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
