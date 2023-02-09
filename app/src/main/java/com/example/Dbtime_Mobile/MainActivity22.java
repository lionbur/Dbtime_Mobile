package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import java.util.Random;

public class MainActivity22 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);

        TextView link = (TextView) findViewById(R.id.hiperlink22);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        int rnd = new Random().nextInt(11);;

        TextView tv = findViewById(R.id.textView41);
        if(rnd>5) tv.setText(R.string.txt221);
        else tv.setText(R.string.txt222);
    }
}