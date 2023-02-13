package com.example.Dbtime_Mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.OffsetTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AlertDetails extends AppCompatActivity {

    TextView textView, sndTxt,userEmail;
    Button bt;
    ImageView img1, img2, img3, img4, img5;
    Integer face = 3;
    private FirebaseAuth mAuth;
    Date date = new Date();
    String dateSTR = date.toString();
    OffsetTime offset = null;
    String offserSTR = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            offset = OffsetTime.now();
            offserSTR = offset.toString();
        }

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();

        textView = (TextView) findViewById(R.id.text_input);
        bt = (Button) findViewById(R.id.button3);
        img1 = (ImageView) findViewById(R.id.imageView1);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);
        img5 = (ImageView) findViewById(R.id.imageView5);
        sndTxt = (TextView) findViewById(R.id.textView2);
        userEmail = (TextView) findViewById(R.id.textView32);

        userEmail.setText(email);

        findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                textView.setVisibility((View.VISIBLE));
                bt.setVisibility((View.VISIBLE));
                face = 1;
                img1.setImageResource(R.raw.mg_1bg);
                img2.setImageResource(R.raw.mg_2);
                img3.setImageResource(R.raw.mg_3);
                img4.setImageResource(R.raw.mg_4);
                img5.setImageResource(R.raw.mg_5);
            }
        });
        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                textView.setVisibility((View.VISIBLE));
                bt.setVisibility((View.VISIBLE));
                face = 2;
                img1.setImageResource(R.raw.mg_1);
                img2.setImageResource(R.raw.mg_2bg);
                img3.setImageResource(R.raw.mg_3);
                img4.setImageResource(R.raw.mg_4);
                img5.setImageResource(R.raw.mg_5);
            }
        });
        findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                textView.setVisibility((View.VISIBLE));
                bt.setVisibility((View.VISIBLE));
                face = 3;
                img1.setImageResource(R.raw.mg_1);
                img2.setImageResource(R.raw.mg_2);
                img3.setImageResource(R.raw.mg_3bg);
                img4.setImageResource(R.raw.mg_4);
                img5.setImageResource(R.raw.mg_5);
            }
        });
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                textView.setVisibility((View.VISIBLE));
                bt.setVisibility((View.VISIBLE));
                face = 4;
                img1.setImageResource(R.raw.mg_1);
                img2.setImageResource(R.raw.mg_2);
                img3.setImageResource(R.raw.mg_3);
                img4.setImageResource(R.raw.mg_4bg);
                img5.setImageResource(R.raw.mg_5);
            }
        });
        findViewById(R.id.imageView5).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(View view) {
                textView.setVisibility((View.VISIBLE));
                bt.setVisibility((View.VISIBLE));
                face = 5;
                img1.setImageResource(R.raw.mg_1);
                img2.setImageResource(R.raw.mg_2);
                img3.setImageResource(R.raw.mg_3);
                img4.setImageResource(R.raw.mg_4);
                img5.setImageResource(R.raw.mg_5bg);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sndTxt.setVisibility((View.VISIBLE));
                Map<String, Object> notificationFB = new HashMap<>();
                notificationFB.put("choice", face.toString());
                notificationFB.put("whatDoIDo", textView.getText().toString());
                notificationFB.put("dateTime", dateSTR);
                notificationFB.put("timeMS", offserSTR);

                db.collection("users").document(email)
                        .collection("notificationFB").document(dateSTR)
                        .set(notificationFB)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                userEmail.setText("success");
                                Log.d("Rubi", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                userEmail.setText("error");
                                Log.d("Rubi", "Error writing document", e);
                            }
                        });

                (new Handler()).postDelayed(this::yourMethod, 15000);
                //Intent i = new Intent(AlertDetails.this, MainActivity.class);
                //startActivity(i);
            }

            private void yourMethod() {
                finish();
                System.exit(0);
            }
        });
    }
}