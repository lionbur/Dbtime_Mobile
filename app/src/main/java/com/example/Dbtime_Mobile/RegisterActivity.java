package com.example.Dbtime_Mobile;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText   userFirstNameEdt, userLastNameEdt,passwordEdt,confirmPwdEdt,email,phone,id;
    private TextView loginTV;
    private Button registerBtn;
    private ProgressBar loadingPB;
    private DatabaseReference mDatabase;
    public String TAG = "Rubi";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseAuth mAuth;
    String userEmail;
    Map<String, Object> user = new HashMap<>();
    public ArrayList newTargetArr;
    long time= System.currentTimeMillis();
    String timeMS = String.valueOf(time);
    Date date = new Date();
    String dateSTR = date.toString();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userFirstNameEdt = findViewById(R.id.FirstName);
        userLastNameEdt = findViewById(R.id.LastName);
        passwordEdt = findViewById(R.id.Password);
        confirmPwdEdt = findViewById(R.id.ConfirmPassword);
        email = findViewById(R.id.Email);
        phone = findViewById(R.id.Phone);
        id = findViewById(R.id.UserId);
        registerBtn = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        loadingPB = findViewById(R.id.progressBar);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFirstName = userFirstNameEdt.getText().toString();
                String userLastName = userLastNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String confirmPassword = confirmPwdEdt.getText().toString();
                userEmail = email.getText().toString();
                String userPhone = phone.getText().toString();
                String userId = id.getText().toString();
                Boolean full = true;
                if( TextUtils.isEmpty(userFirstNameEdt.getText())){
                    Log.d(TAG,"#4");
                    userFirstNameEdt.setError( "יש להכניס שם פרטי" );
                    full = false;}
                if( TextUtils.isEmpty(userLastNameEdt.getText())){
                    userLastNameEdt.setError( "יש להכניס שם משפחה" );
                    full = false;}
                if( TextUtils.isEmpty(passwordEdt.getText())){
                    passwordEdt.setError( "יש להכניס סיסמה" );
                    full = false;}
                if( TextUtils.isEmpty(email.getText())){
                    email.setError( "יש להכניס כתובת אימייל" );
                    full = false;}
                if( TextUtils.isEmpty(confirmPwdEdt.getText())){
                    confirmPwdEdt.setError( "יש לאמת סיסמה" );
                    full = false;}
                else{
                    if(!password.equals(confirmPassword)){
                        confirmPwdEdt.setError( "סיסמה לא זהה" );
                        full = false;}
                }
                if( TextUtils.isEmpty(phone.getText())){
                    phone.setError( "יש להכניס טלפון" );
                    full = false;}
                if( TextUtils.isEmpty(id.getText())){
                    id.setError( "יש להכניס תעודת זהות" );
                    full = false;}
                if(full ==true) {
                    writeNewUser(userFirstName,userLastName,password,
                            userEmail,userPhone,userId);
                 }
            }
        });
    }

    public void writeNewUser(String userFirstName,String userLastName,String password,
                             String userEmail,String userPhone,String userId) {
        loadingPB.setVisibility(View.VISIBLE);

        user.put("first", userFirstName);
        user.put("last", userLastName);
        user.put("pw", password);
        user.put("phone", userPhone);
        user.put("mail", userEmail);

        //=========
        mAuth.createUserWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "נרשמת בהצלחה", Toast.LENGTH_LONG).show();
                    addUserToDB();
                    finish();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "לא הצלחנו לרשום אותך, בדוק שוב את הנתונים שהכנסת", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @IgnoreExtraProperties
    public static class User {

        public String userFirstName;
        public String userLastName;
        public String password;
        public String userEmail;
        public String userPhone;
        public String userId;

        public User() {
            // Default constructor required for calls to DataSnapshot.getValue(com.example.login.HomeActivity.User.class)
        }

        public User(String userFirstName,String userLastName,String password,
                    String userEmail,String userPhone,String userId) {
            this.userFirstName = userFirstName;
            this.userLastName = userLastName;
            this.password = password;
            this.userEmail = userEmail;
            this.userPhone = userPhone;
            this.userId = userId;
        }

    }

    public void addUserToDB() {
        db.collection("users").document(userEmail)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        addFirstTarget();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void addFirstTarget() {
        Map<String, Object> newTarget = new HashMap<>();
        String timeMS = String.valueOf(System.currentTimeMillis());
        newTargetArr = new ArrayList();
        newTargetArr.add("התחלתי להשתמש במערכת");
        newTargetArr.add(604800000);
        newTargetArr.add("שבוע");
        newTarget.put(timeMS, newTargetArr);
        db.collection("users").document(userEmail)
                .collection("targets").document("list")
                .set(newTarget)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        addNotificationFBToDB();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void addNotificationFBToDB() {
        Map<String, Object> notificationFB = new HashMap<>();
        notificationFB.put("choice", "9");
        notificationFB.put("whatDoIDo", "initialize");
        notificationFB.put("dateTime", dateSTR);
        notificationFB.put("timeMS", timeMS);
        db.collection("users").document(userEmail)
                .collection("notificationFB").document("Initialize")
                .set(notificationFB)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        addSentenceToDB();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void addSentenceToDB() {
        Map<String, Object> sentence = new HashMap<>();
        db.collection("users").document(userEmail)
                .collection("sentences").document("list")
                .set(sentence)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        addReportToDB();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void addReportToDB() {
        Map<String, Object> report = new HashMap<>();
        db.collection("users").document(userEmail)
                .collection("reports").document("list")
                .set(report)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        addBloodPressureToDB();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

    public void addBloodPressureToDB() {
        Map<String, Object> measure = new HashMap<>();
        db.collection("users").document(userEmail)
                .collection("measures").document("list")
                .set(measure)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }

}