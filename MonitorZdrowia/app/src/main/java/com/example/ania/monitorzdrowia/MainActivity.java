package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //TextView tx=(TextView)findViewById(R.id.textView13);
        //String j=null;
        //j="aaa";
        /*try {
            JSONParser js=new JSONParser("//api.waqi.info/search/?token=0126d0b44551423b1d4c683a63c3be5c2d079953+&keyword=Pozna 1");
            j=js.json.toString();

        } catch (IOException e) {
            j="blad1";
            //e.printStackTrace();
        } catch (JSONException e) {
            j="blad2";
            //e.printStackTrace();
        }*/
        //tx.setText(j);



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });




        TextView tx=(TextView)findViewById(R.id.textView13);
        String j=null;
        j="aaa";
        try {
            JSONParser js=new JSONParser("https://api.waqi.info/feed/here/?token=0126d0b44551423b1d4c683a63c3be5c2d079953");
            j=js.json.getString("status");

        } catch (IOException e) {
            j="blad1";
            //e.printStackTrace();
        } catch (JSONException e) {
            j="blad2";
            //e.printStackTrace();
        }
        tx.setText(j);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        switch (item.getItemId()) {

           /* case R.id.actual_health:
                Intent intentHealth=new Intent(this,Health.class);
                startActivity(intentHealth);
                return true;*/
            case R.id.title_activity_pesonal_data:
                Intent intentSettings=new Intent(this,PesonalData.class);
                startActivity(intentSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
