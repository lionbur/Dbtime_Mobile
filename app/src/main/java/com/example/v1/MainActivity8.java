package com.example.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        TextView link = (TextView) findViewById(R.id.hiperlink8);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}