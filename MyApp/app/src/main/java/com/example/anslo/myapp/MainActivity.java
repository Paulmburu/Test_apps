package com.example.anslo.myapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView =new TextView(this);
        textView.setText("Paul Mburu->+254704002748\n<paulmburu53@gmail.com>");
        textView.setTextColor(Color.MAGENTA);
        textView.setTextSize(20);
        //textView.setMaxLines(1);
        setContentView(textView);
    }
}
