package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class D1 extends AppCompatActivity {
    EditText number;
    Button next;
    Intent intent;
    public static final String EXTRA_MESSAGE = "com.example.ania.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d1);
        next=(Button)findViewById(R.id.button4);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                intent=new Intent(v.getContext(),D2.class);

                number=(EditText)findViewById(R.id.editText8);
                int n =Integer.parseInt(number.getText().toString());
//int n=26;
                if(n==26){
                    String message ="1";
                    intent.putExtra(EXTRA_MESSAGE, message);
                }
                else{
                    String message ="0";
                    intent.putExtra(EXTRA_MESSAGE, message);
                }
                startActivity(intent);
            }
        });





    }
}
