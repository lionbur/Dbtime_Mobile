package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MindfullIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfull_intro);

        ViewGroup categories = findViewById(R.id.mindful_categories);
        for (int i = 0; i < categories.getChildCount(); i++) {
            View child = categories.getChildAt(i);

            if (child instanceof Button) {
                child.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MindfullIntroActivity.this.startActivity(
                                new Intent(MindfullIntroActivity.this, MindfullCardsActivity.class)
                                        .putExtra("category", ((Button)child).getText())
                        );
                    }
                });
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        animate(R.id.mindful_categories, R.anim.fade_in_1);
    }

    void animate(int viewId, int animationId) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animationId);
        View view = findViewById(viewId);
        view.startAnimation(animation);
    }
}