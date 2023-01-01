package com.example.v1;

import static com.google.android.material.snackbar.Snackbar.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.common.util.concurrent.ListenableFuture;

import java.sql.Time;
import java.time.OffsetTime;
import java.util.Date;
import java.util.List;
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

                /*
                Snackbar sb = make(view, "מכאן נעבור למדיטציה", LENGTH_SHORT).setAnchorView(R.id.imageView2);
                sb.show();

                 */
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
                Uri uri = Uri.parse("https://poki.com");
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

        findViewById(R.id.imageView14).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar sb = make(view, "מכן נעבור לטמגוצ'י", LENGTH_SHORT);
                sb.show();
            }
        });

        /*
        findViewById(R.id.imageButton11).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Snackbar sb = make(view, "מכאן נעבור למשחקים", LENGTH_SHORT).setAnchorView(R.id.imageButton11);
                sb.show();
            }
        });

         */
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
    }
}