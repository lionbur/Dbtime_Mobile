package com.example.Dbtime_Mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.time.OffsetTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity11 extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        MediaPlayer ring = MediaPlayer.create(MainActivity11.this, R.raw.mot_sound);
        ring.setLooping(true);
        ring.start();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        long time = System.currentTimeMillis();
        String timeMS = String.valueOf(time);
        Date date = new Date();
        String dateSTR = date.toString();
        Button saveBT = findViewById(R.id.button9);

        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBar2);
        TextView tv = (TextView) findViewById(R.id.textView32);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && !seekBar.isInTouchMode()) seek(progress);
            }

            private void seek(int progress) {
                tv.setText(String.valueOf(100 - sb1.getProgress()));

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }
        });

        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                saveBT.setVisibility(View.INVISIBLE);
                Map<String, Object> motivationLevel = new HashMap<>();
                motivationLevel.put("motivationLevel", String.valueOf(100 - sb1.getProgress()));
                motivationLevel.put("dateTime", dateSTR);
                motivationLevel.put("timeMS", timeMS);

                db.collection("users").document(email)
                        .collection("motivationLevel").document(dateSTR)
                        .set(motivationLevel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                ring.stop();
                                Intent myIntent = new Intent(MainActivity11.this, MainActivity.class);
                                MainActivity11.this.startActivity(myIntent);
                                Log.d("Rubi", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Rubi", "Error writing document", e);
                            }
                        });
            }
        });

        this.initBackground();
    }

    void initBackground() {
        final int[] backgrounds = {
                R.raw.mot_bg_1,
                R.raw.mot_bg_2,
                R.raw.mot_bg_3,
                R.raw.mot_bg_4,
                R.raw.mot_bg_5,
                R.raw.mot_bg_6,
                R.raw.mot_bg_7,
        };
        Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        VideoView bg = findViewById(R.id.videoBackground);
        bg.setVideoPath("android.resource://" + getPackageName() + "/" + backgrounds[day]);
        bg.start();
    }
}