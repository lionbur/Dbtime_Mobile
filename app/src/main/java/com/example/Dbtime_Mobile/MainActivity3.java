package com.example.Dbtime_Mobile;

import static java.lang.Long.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public DocumentReference userTargets;
    public ArrayList targets,from,targetArr,targetsQuotaAsString,targetsList;
    public FirebaseAuth mAuth;

    public String email;
    public Long currentTargetStartMS,quota;
    public Long nowMS;
    public Long dif;
    public Long applesCount;
    public TextView aplsCount, motivationAvg;
    public Double avrg = 0.0;
    public Integer indx = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mAuth = FirebaseAuth.getInstance();
        nowMS = new Date().getTime();
        applesCount = Long.valueOf(0);
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();

        TextView link = (TextView) findViewById(R.id.hiperlink3);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        aplsCount = (TextView) findViewById(R.id.textView18);
        motivationAvg = (TextView) findViewById(R.id.textView138);
        calculateApples();
        calculateMotivation();

        findViewById(R.id.textView9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity7.class);
                MainActivity3.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageView7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity8.class);
                myIntent.putExtra("targets",targetsList);
                myIntent.putExtra("quotas",targetsQuotaAsString);
                MainActivity3.this.startActivity(myIntent);
            }
        });
    }

    public void calculateApples(){
        userTargets = db.collection("users").document(email)
                .collection("targets").document("list");
        userTargets.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()&&!document.getData().isEmpty()) {
                        from = new ArrayList(document.getData().keySet());
                        targets = new ArrayList(document.getData().values());
                        targetsQuotaAsString = new ArrayList();
                        targetsList = new ArrayList();
                       for(int i=0; i<from.size();i++) {
                           currentTargetStartMS = valueOf((String) from.get(i));
                           dif = nowMS-currentTargetStartMS;
                           targetArr = (ArrayList) targets.get(i);
                           quota = (Long) targetArr.get(1);
                           targetsQuotaAsString.add(targetArr.get(2));
                           targetsList.add(targetArr.get(0));
                           applesCount=applesCount+dif/quota;
                       }
                        aplsCount.setText(Long.toString(applesCount));
                    }
                } else {
                    Log.d("Rubi", "get failed with ", task.getException());
                }
            }
        });
    }

    public void calculateMotivation(){
        String last7days = String.valueOf((nowMS-7*24*60*60*1000));
        CollectionReference userMotivation = db.collection("users").document(email)
                .collection("motivationLevel");
       userMotivation.whereGreaterThan("timeMS", last7days)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("RRubi", "#2");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               String t = (String) document.getData().get("motivationLevel");
                                avrg = avrg+valueOf(t);
                                indx++;
                                Log.d("RRubi", document.getId() + " avrg " + avrg+ " indx: "+indx);
                            }
                            motivationAvg.setText(String.valueOf(avrg/indx));
                        } else {
                            Log.d("RRubi", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

}