package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MindfullCardsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfull_cards);

        String category = getIntent().getStringExtra("category");
        TextView tvCategory = findViewById(R.id.mindful_cards_category);
        tvCategory.setText(category);
    }
}