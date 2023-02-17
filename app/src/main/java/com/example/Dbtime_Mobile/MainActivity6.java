package com.example.Dbtime_Mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity6 extends AppCompatActivity {

    public Integer i = 0;
    public ArrayList globalSentences,list;
    private FirebaseAuth mAuth;
    long time= System.currentTimeMillis();
    String timeMS = String.valueOf(time);
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();

        DocumentReference globalDoc = db.collection("sentences").document("list");
        globalDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                globalSentences = new ArrayList();
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      globalSentences = new ArrayList(document.getData().values());

                        DocumentReference userDoc = db.collection("users").document(email)
                                .collection("sentences").document("list");
                        userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        list = new ArrayList(document.getData().values());
                                        list.addAll(globalSentences);
                                    } else {
                                        Log.d("Rubi", "No such document");
                                    }
                                } else {
                                    Log.d("Rubi", "get failed with ", task.getException());
                                }
                            }
                        });
                    } else {
                        Log.d("Rubi", "No such document");
                    }
                } else {
                    Log.d("Rubi", "get failed with ", task.getException());
                }
            }
        });

        TextView link = (TextView) findViewById(R.id.hiperlink6);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        TextInputEditText inpt = (TextInputEditText) findViewById(R.id.inpt);
        Button entr = (Button) findViewById(R.id.button5);
        TextView tv = (TextView)findViewById(R.id.textView15);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i<list.size()-1) i++;
                else i = 0;
                TextView tv = (TextView)findViewById(R.id.textView15);
                String txt = list.get(i).toString();
                tv.setText(txt);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i>0) i--;
                else i = list.size()-1;
                String txt = list.get(i).toString();
                tv.setText(txt);
            }
        });


        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                inpt.setVisibility((View.VISIBLE));
                entr.setVisibility((View.VISIBLE));
            }
        });

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String t =inpt.getText().toString();
                addSentence(t);
                list.add(t);
                i=list.size()-1;
                tv.setText(t);
                inpt.setVisibility((View.INVISIBLE));
                entr.setVisibility((View.INVISIBLE));
            }
        });
    }
    public void addSentence(String sentence){
        Map<String, Object> newSentence = new HashMap<>();
        newSentence.put(timeMS, sentence);

        db.collection("users").document(email)
        .collection("sentences").document("list")
        .update(timeMS, sentence)
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