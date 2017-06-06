package com.example.ania.monitorzdrowia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DEnd extends AppCompatActivity {
    EditText number;
    Button next;
    Intent intent;
    int x;
    public static final String EXTRA_MESSAGE = "com.example.ania.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dend);
        TextView textView;
        textView=(TextView) findViewById(R.id.textView19) ;

        Intent intent1 = getIntent();
        String message = intent1.getStringExtra(D2.EXTRA_MESSAGE);
        x=Integer.parseInt(message);
textView.setText(message);
        next=(Button)findViewById(R.id.button6);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }

    });
}
}