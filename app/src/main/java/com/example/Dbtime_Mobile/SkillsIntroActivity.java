package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class SkillsIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        (new Handler()).postDelayed(this::delay, 3000);
    }

    private void delay() {
        Intent i = new Intent(SkillsIntroActivity.this, MainActivity5.class);
        startActivity(i);
    }
}