package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView link = (TextView) findViewById(R.id.hiperlink3);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.textView9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity7.class);
                MainActivity3.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageView7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity8.class);
                MainActivity3.this.startActivity(myIntent);
            }
        });
    }

}