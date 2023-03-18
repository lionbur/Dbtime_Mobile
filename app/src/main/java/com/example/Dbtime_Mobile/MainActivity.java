package com.example.Dbtime_Mobile;

import static com.google.android.material.snackbar.Snackbar.LENGTH_SHORT;
import static com.google.android.material.snackbar.Snackbar.make;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.time.OffsetTime;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("12", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public boolean isNotificationChannelEnabled(Context context, @Nullable String channelId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(!TextUtils.isEmpty(channelId)) {
                NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel channel = manager.getNotificationChannel(channelId);
                return channel.getImportance() != NotificationManager.IMPORTANCE_NONE;
            }
            return false;
        } else {
            return NotificationManagerCompat.from(context).areNotificationsEnabled();
        }
    }

    View lo;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Integer hr = 12;

        TextView link = (TextView) findViewById(R.id.hiperlink);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        MediaPlayer ring= MediaPlayer.create(MainActivity.this,R.raw.sound);
        ring.setLooping(true);
        ring.start();

        OffsetTime offset = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            offset = OffsetTime.now();
            hr = offset.getHour();
        }

        if(hr>6 && hr<19){
            lo = findViewById(R.id.mainlot);
            lo.setBackgroundResource(R.raw.day);
        }

        createNotificationChannel();

        Boolean isOpen = isNotificationChannelEnabled(MainActivity.this,"12");
        Log.d("Rubi","Chanel Enabled = "+isOpen.toString());

        WorkManager.getInstance(this).cancelAllWorkByTag("cleanup");
        WorkManager.getInstance(this).cancelAllWork();
        PeriodicWorkRequest PSN = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            PSN = new PeriodicWorkRequest.Builder(ScheduleNotifications.class, 1, TimeUnit.HOURS)
                    .addTag("cleanup").build();
        }
        WorkManager.getInstance(this).enqueue(PSN);

        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, MainActivity9.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity6.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageButton11).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Uri uri = Uri.parse("https://poki.co.il/%D7%97%D7%A9%D7%99%D7%91%D7%94");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        findViewById(R.id.imageButton9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity4.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageView9).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity3.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar sb = make(view, "מכאן נעבור למדידת דופק ומצב רוח", LENGTH_SHORT).setAnchorView(R.id.imageView2);
                sb.show();
               // Intent myIntent = new Intent(MainActivity.this, MainActivity3.class);
                //myIntent.putExtra("key", value); //Optional parameters
               // MainActivity.this.startActivity(myIntent);
            }
        });


        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar sb = make(view, "בדיקה למעגל בחירה", LENGTH_SHORT).setAnchorView(R.id.imageView2);
                sb.show();
                 Intent myIntent = new Intent(MainActivity.this, roundSelect.class);
                //myIntent.putExtra("key", value); //Optional parameters
                 MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.reload();
        } else {
            // No user is signed in
        }


    }
}