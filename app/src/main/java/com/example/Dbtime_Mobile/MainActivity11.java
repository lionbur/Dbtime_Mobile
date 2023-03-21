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

        MediaPlayer ring= MediaPlayer.create(MainActivity11.this,R.raw.mot_sound);
        ring.setLooping(true);
        ring.start();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        long time= System.currentTimeMillis();
        String timeMS = String.valueOf(time);
        Date date = new Date();
        String dateSTR = date.toString();
        Button saveBT = findViewById(R.id.button9);

        View lo = findViewById(R.id.bg);
        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBar2);
        TextView tv = (TextView) findViewById(R.id.textView32);

        switch (day) {
            case Calendar.SUNDAY:
                lo.setBackgroundResource(R.raw.mot_pic_1);
                break;
            case Calendar.MONDAY:
                lo.setBackgroundResource(R.raw.mot_pic_2);
                break;
            case Calendar.TUESDAY:
                lo.setBackgroundResource(R.raw.mot_pic_3);
                break;
            case Calendar.WEDNESDAY:
                lo.setBackgroundResource(R.raw.mot_pic_4);
                break;
            case Calendar.THURSDAY:
                lo.setBackgroundResource(R.raw.mot_pic_5);
                break;
            case Calendar.FRIDAY:
                lo.setBackgroundResource(R.raw.mot_pic_6);
                break;
            case Calendar.SATURDAY:
                lo.setBackgroundResource(R.raw.mot_pic_7);
                break;
        }


        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && !seekBar.isInTouchMode()) seek(progress);
            }

            private void seek(int progress) {
                tv.setText(String.valueOf(sb1.getProgress()));

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
                motivationLevel.put("motivationLevel",String.valueOf(sb1.getProgress()));
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


    }
}