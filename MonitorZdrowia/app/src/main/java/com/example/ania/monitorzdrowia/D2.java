package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class D2 extends AppCompatActivity {
    EditText number;
    Button next;
    Intent intent;
    int x;
    public static final String EXTRA_MESSAGE = "com.example.ania.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d2);


        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(D1.EXTRA_MESSAGE);
 x=Integer.parseInt(message);

        next=(Button)findViewById(R.id.button5);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                intent=new Intent(v.getContext(),DEnd.class);

                number=(EditText)findViewById(R.id.editText9);
                int n =Integer.parseInt(number.getText().toString());
//int n=26;
                if(n==3){
x+=1;
                    String message =Integer.toString(x);
                    intent.putExtra(EXTRA_MESSAGE, message);
                }
                else{
                    String message =Integer.toString(x);
                    intent.putExtra(EXTRA_MESSAGE, message);
                }
                startActivity(intent);
            }
        });






    }
}
