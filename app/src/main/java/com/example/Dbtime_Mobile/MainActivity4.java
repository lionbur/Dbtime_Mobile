package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        TextView link = (TextView) findViewById(R.id.hiperlink4);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        (new Handler()).postDelayed(this::delay, 3000);
    }

    private void delay() {
        Intent i = new Intent(MainActivity4.this, MainActivity5.class);
        startActivity(i);
    }
}