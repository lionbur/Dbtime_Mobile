package com.example.v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.Random;

public class MainActivity23 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);

        TextView link = (TextView) findViewById(R.id.hiperlink23);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        int rnd = new Random().nextInt(11);;

        TextView tv = findViewById(R.id.textView51);
        if(rnd>5) tv.setText(R.string.txt231);
        else tv.setText(R.string.txt232);
    }
}