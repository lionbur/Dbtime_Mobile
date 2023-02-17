package com.example.Dbtime_Mobile;

import static java.lang.Long.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity7 extends AppCompatActivity {

    public Long dtNmbr= Long.valueOf(new Date().getTime());
    //public Long dtNmbr, dtNmbrInDB;
    public Integer i = 0;
    public Integer second = 1000;
    public Integer minute = 60*second;
    public Integer hour = 60*minute;
    public Integer day = 24*hour;
    public ArrayList target,from;
    public String txt;
    public long dif, nowMilli;
    public DocumentReference userTargets;

    private FirebaseAuth mAuth;
    long time= System.currentTimeMillis();
    String timeMS = String.valueOf(time);
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        TextView link = (TextView) findViewById(R.id.hiperlink7);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        final Calendar c = Calendar.getInstance();

        TextInputEditText inpt = (TextInputEditText) findViewById(R.id.inpt71);
        Button entr = (Button) findViewById(R.id.button5);
        TextView tv = (TextView)findViewById(R.id.textView15);
        TextView tv18 = (TextView)findViewById(R.id.tv18);
        TextView tv24 = (TextView)findViewById(R.id.textView24);
        TextView tv26 = (TextView)findViewById(R.id.textView26);
        TextView tv28 = (TextView)findViewById(R.id.textView28);
        TextView tv30 = (TextView)findViewById(R.id.textView30);
        ProgressBar pb3 = (ProgressBar) findViewById(R.id.pb3);
        ProgressBar pb4 = (ProgressBar) findViewById(R.id.pb4);
        ProgressBar pb5 = (ProgressBar) findViewById(R.id.pb5);
        ProgressBar pb6 = (ProgressBar) findViewById(R.id.pb6);

        userTargets = db.collection("users").document(email)
                .collection("targets").document("list");
        userTargets.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        from = new ArrayList(document.getData().keySet());
                        target = new ArrayList(document.getData().values());
                        txt = target.get(0).toString();
                        tv.setText(txt);
                        dtNmbr = valueOf((String) from.get(0));

                    } else {
                        Log.d("Rubi", "No such document");
                    }
                } else {
                    Log.d("Rubi", "get failed with ", task.getException());
                }
            }
        });

        pb3.setMax(365);
        pb4.setMax(24);
        pb5.setMax(60);
        pb6.setMax(60);

        FloatingActionButton fltBt = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public long time = 0;

            public void run() {
                nowMilli = new Date().getTime();
                dif = nowMilli-dtNmbr;
                Integer difdays = Math.toIntExact(dif / day);
                Integer difhors = Math.toIntExact((dif- (long) difdays *day )/ hour);
                Integer difminuts = Math.toIntExact((dif- (long) difdays *day- (long) difhors *hour)/ minute);
                Integer difseconds = Math.toIntExact((dif- (long) difdays *day- (long) difhors *hour- (long) difminuts *minute)/ second);

                tv24.setText(String.valueOf(difdays));
                tv26.setText(String.valueOf(difhors));
                tv28.setText(String.valueOf(difminuts));
                tv30.setText(String.valueOf(difseconds));
                pb3.setProgress(difdays);
                pb4.setProgress(difhors);
                pb5.setProgress(difminuts);
                pb6.setProgress(difseconds);
                tv18.setText(getDate(dtNmbr,c));
                time += 1000;
                h.postDelayed(this, 1000);
            }
        }, 1000); // 1 second delay (takes millis)

        findViewById(R.id.frwrd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i<target.size()-1) i++;
                else i = 0;
                txt = target.get(i).toString();
                tv.setText(txt);
                dtNmbr = valueOf((String) from.get(i));
                tv18.setText(getDate(dtNmbr,c));
            }
        });

        findViewById(R.id.bckwrd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i>0) i--;
                else i = target.size()-1;
                txt = target.get(i).toString();
                tv.setText(txt);
                dtNmbr = valueOf((String) from.get(i));
                tv18.setText(getDate(dtNmbr,c));
            }
        });


        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                inpt.setVisibility((View.VISIBLE));
                entr.setVisibility((View.VISIBLE));
                fltBt.setVisibility((View.INVISIBLE));

            }
        });

        // Add new target
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                fltBt.setVisibility((View.VISIBLE));
                String t =inpt.getText().toString();
                target.add(t);
                Date dd = new Date();
                Long ddms = dd.getTime();
                from.add(String.valueOf(ddms));
                i=target.size()-1;
                tv.setText(t);
                dtNmbr = valueOf((String) from.get(i));
                tv18.setText(getDate(dtNmbr,c));
                inpt.setVisibility((View.INVISIBLE));
                entr.setVisibility((View.INVISIBLE));
                addTarget(t);
            }
        });

        // Reset start date
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
               // Log.d("Rubi","#1: "+dtNmbr.toString());
                updateStartTarget(dtNmbr.toString(),txt);
                Date dd = new Date();
                Long ddms = dd.getTime();
                from.set(i, (String.valueOf(ddms)));
                dtNmbr = ddms;
                tv18.setText(getDate(dtNmbr,c));
            }
        });
    }

    public String getDate(Long dtNmbr, Calendar c){
        c.setTimeInMillis(dtNmbr);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return (day + "-" + (month + 1) + "-" + year + " ");
    }

    public void addTarget(String target){
        Map<String, Object> newTarget = new HashMap<>();
        newTarget.put(timeMS, target);

        db.collection("users").document(email)
        .collection("targets").document("list")
        .update(newTarget)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Rubi", "DocumentSnapshot successfully written!");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Rubi", "Error writing document", e);
            }
        } );
    }

    public void updateStartTarget(String newStartMS,String trgt){
        Map<String, Object> newTarget = new HashMap<>();
        nowMilli = new Date().getTime();
        dif = nowMilli-dtNmbr;

        Map<String,Object> toDelete = new HashMap<>();
        toDelete.put(dtNmbr.toString(), FieldValue.delete());
        newTarget.put(String.valueOf(nowMilli), trgt);

        // Add the new/update target
        db.collection("users").document(email)
        .collection("targets").document("list")
        .update(newTarget)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Rubi", "DocumentSnapshot successfully written!");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Rubi", "Error writing document", e);
            }
        } );

        // Delete the old target
        db.collection("users").document(email)
        .collection("targets").document("list")
        .update(toDelete)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Rubi", "DocumentSnapshot successfully written!");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Rubi", "Error writing document", e);
            }
        } );
    }
}