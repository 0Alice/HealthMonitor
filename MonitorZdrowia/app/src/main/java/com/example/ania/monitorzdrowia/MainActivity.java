package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ProgressBar mProgressBar;
    TextView temper;
    TextView wind;
    TextView hyd;
    TextView pm1;
    TextView pm2;
    TextView pre;
    TextView adv;

    public final static String API_KEY ="0126d0b44551423b1d4c683a63c3be5c2d079953";
    public final static String  CITY ="Poznań 1 ul. Polanka";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        temper=(TextView)findViewById(R.id.textView13);
        wind=(TextView)findViewById(R.id.textView3);
        pre=(TextView)findViewById(R.id.textView5);
        hyd=(TextView)findViewById(R.id.textView7);
        pm1=(TextView)findViewById(R.id.textView9);
        pm2=(TextView)findViewById(R.id.textView11);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        adv=(TextView) findViewById(R.id.textView17);

       if (isOnline()){
            DownloadData task = new DownloadData();
            task.execute();
        }else{
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this.getApplicationContext(), "Aby pobierać dane, musisz się połączyć z siecią!", Toast.LENGTH_SHORT).show();
        }
        }


    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
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



            case R.id.pref_header_plot_pm:
                Intent intent2=new Intent(this,Pollution.class);
                startActivity(intent2);
                return true;
            case R.id.pref_header_plot_w:
                Intent intent1=new Intent(this,WeightPlot.class);
                startActivity(intent1);
                return true;
           case R.id.pref_header_plot:
                Intent intentHealth=new Intent(this,TempPlot.class);
                startActivity(intentHealth);
                return true;
            case R.id.title_activity_pesonal_data:
                Intent intentSettings=new Intent(this,PesonalData.class);
                startActivity(intentSettings);
                return true;
            case R.id.pref_header_bmi:
                Intent intentSettings1=new Intent(this,Bmi.class);
                startActivity(intentSettings1);
                return true;
            case R.id.header_over:
                Intent intent3=new Intent(this,ExercisesOverweight.class);
                startActivity(intent3);
                return true;

            case R.id.exercises_pain:
                Intent intent4=new Intent(this,ExercisesPain.class);
                startActivity(intent4);
                return true;
            case R.id.dalt:
                Intent intent5=new Intent(this,d0.class);
                startActivity(intent5);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void show(String t,String w,String p,String h,String pm10,String pm25) {
        temper.setText(t);
        wind.setText(w);
        pre.setText(p);
        hyd.setText(h);
        pm1.setText(pm10);
        pm2.setText(pm25);
        File file=new File(this.getFilesDir(),"temperature.txt");
        DataToHistory d=new DataToHistory(file,t);
        File file2=new File(this.getFilesDir(),"pm10.txt");
        DataToHistory d2=new DataToHistory(file2,pm10);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String we=preferences.getString("text_weight","1");

        File file3=new File(this.getFilesDir(),"weight.txt");
        DataToHistory d3=new DataToHistory(file3,we);
    }



    public void check(Double t) {
        if (t > 20) {
            adv.setText("Dzisiaj jest ciepło.");
        } else if (t > 15) {
            adv.setText("Załóż kurtkę, jest chłodno.");
        } else if (t > 5) {
            adv.setText("Ubierz się ciepło.");
        } else if (t > 0) {
            adv.setText("Zimno, załóż czapkę i rękawiczki.");
        } else if (t > (-5)) {
            adv.setText("Mróz, może padać śnieg. Czapka i rekawiczki obowiązkowo!");
        } else {
            adv.setText("Bardzo zimno, najlepiej nie wychodź z domu.");
        }
    }







//Pobieranie danych

private class DownloadData extends AsyncTask<String , String , Long > {

    String t;
    String w;
    String p;
    String h;
    String pm10;
    String pm25;
    Boolean isPublic;
    Boolean isFriend;
    Boolean isFamily;

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(Long result) {
        if (result==0){
            show(t,w,p,h,pm10,pm25);
check(Double.parseDouble(t));
        }else{
            Toast.makeText(MainActivity.this.getApplicationContext(), "Coś poszło źle!", Toast.LENGTH_SHORT).show();
        }
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    protected Long doInBackground(String... params) {
        HttpURLConnection connection = null;
        try {
            URL dataUrl = new URL("http://api.waqi.info/feed/here/?token=" + API_KEY  );
            connection = (HttpURLConnection) dataUrl.openConnection();
            connection.connect();
            int status = connection.getResponseCode();
            Log.d("DATA", status + " " + connection.getResponseMessage());

            if (status ==200){
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String responseString;
                StringBuilder sb = new StringBuilder();
                while ((responseString = reader.readLine()) != null) {
                    sb = sb.append(responseString);
                }
                String jsonData = sb.toString();
                Log.d("DATA", jsonData);
                JSONObject jsonObject=new JSONObject(jsonData);
//JSONObject jsonObject=new JSONObject(jsonObject1.toString());

//System.out.print(jsonData);
                t=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("t").optString("v","blad");
                w=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("w").optString("v","blad");
                p=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("p").optString("v","blad");
                h=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("h").optString("v","blad");
                pm10=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm10").optString("v","blad");
                pm25=(String) jsonObject.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm25").optString("v","blad");
                //isPublic=(Boolean) jsonObject.optBoolean("ispublic", false);
                //isFriend=(Boolean) jsonObject.optBoolean("isfriend", false);
                //isFamily=(Boolean) jsonObject.optBoolean("isfamily", false);
                return (0l);
            }else{
                return (1l);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return (1l);
        }
        catch (IOException e) {
            e.printStackTrace();
            return (1l);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return (1l);
        } catch (JSONException e) {
            e.printStackTrace();
            return (1l);
        } finally {
            connection.disconnect();
        }
    }
}
}