package com.example.ania.monitorzdrowia;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geral_000 on 2017-06-05.
 */

public class DataToHistory  {
    public List<Float> data;
    StringBuffer stringBuffer;
    String writedData;
    LineData lineData;

    public DataToHistory(File file,String caption,LineData lineDatas,int color) {
        data = new ArrayList<>();
        read(file);

        List<Entry> entries = new ArrayList<Entry>();
        int i=0;
        for(float f:data){
            entries.add(new Entry(i, f));
            i++;
        }
        LineDataSet dataSet = new LineDataSet(entries, caption);
        //dataSet.setColor(color);
        lineData = lineDatas;
        lineData.addDataSet(dataSet);
    }



    public DataToHistory(File file, String dataToWrite) {
        writedData=dataToWrite;
        data = new ArrayList<>();
        stringBuffer=new StringBuffer();
        //file.delete();
        write(file);
    }


    private void read(File file){
        try {
            FileReader fileReader=new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String text ;
            while((text = bufferedReader.readLine()) != null){
                data.add(Float.valueOf(text));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void write(File file){
        try {

            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(writedData+"\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
