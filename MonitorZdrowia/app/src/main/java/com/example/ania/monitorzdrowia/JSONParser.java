package com.example.ania.monitorzdrowia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * Created by geral_000 on 2017-06-03.
 */

public class JSONParser {
    JSONObject json;



    public JSONParser(){
    }
    public JSONParser(String url) throws IOException, JSONException {
        json = readJsonFromUrl(url);
        //System.out.println(json.toString());
        //System.out.println(json.get("id"));
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
}
/*
    JSONParser j=null;
                    try {
                            j=new JSONParser("//api.waqi.info/search/?token=0126d0b44551423b1d4c683a63c3be5c2d079953+&keyword=Pozna 1");
                            } catch (IOException e) {
                            e.printStackTrace();
                            } catch (JSONException e) {
                            e.printStackTrace();
                            }*/
