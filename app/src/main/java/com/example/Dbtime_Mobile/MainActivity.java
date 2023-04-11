package com.example.Dbtime_Mobile;

import static com.google.android.material.snackbar.Snackbar.make;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

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

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer ring = MediaPlayer.create(MainActivity.this, R.raw.sound);
        ring.setLooping(true);
        ring.start();

        createNotificationChannel();

        Boolean isOpen = isNotificationChannelEnabled(MainActivity.this, "12");
        Log.d("Rubi", "Chanel Enabled = " + isOpen.toString());

        WorkManager.getInstance(this).cancelAllWorkByTag("cleanup");
        WorkManager.getInstance(this).cancelAllWork();
        PeriodicWorkRequest PSN = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            PSN = new PeriodicWorkRequest.Builder(ScheduleNotifications.class, 1, TimeUnit.HOURS)
                    .addTag("cleanup").build();
        }
        WorkManager.getInstance(this).enqueue(PSN);

        initEventHandlers();
    }

    void initEventHandlers() {
        findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.startActivity(
                        new Intent(MainActivity.this, WebViewActivity.class)
                            .putExtra("url", "https://www.thinglink.com/card/1472621679711617026")
                );
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
                MainActivity.this.startActivity(
                        new Intent(MainActivity.this, WebViewActivity.class)
                                .putExtra("url", "https://poki.co.il/%D7%97%D7%A9%D7%99%D7%91%D7%94")
                );
            }
        });

        findViewById(R.id.btn_life_skills).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SkillsIntroActivity.class);
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

        findViewById(R.id.imageView8).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, MainActivity11.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Snackbar sb = make(view, "בדיקה למעגל בחירה", LENGTH_SHORT).setAnchorView(R.id.imageView2);
                //sb.show();
                 Intent myIntent = new Intent(MainActivity.this, roundSelect.class);
                //myIntent.putExtra("key", value); //Optional parameters
                 MainActivity.this.startActivity(myIntent);
            }
        });
    }

    void initBackground() {
        Integer hr = 12;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetTime offset = OffsetTime.now();
            hr = offset.getHour();
        }

        VideoView bg = findViewById(R.id.main_bg);
        Integer video = (hr > 6 && hr < 19)
                ? R.raw.main_bg_day
                : R.raw.main_bg_night;
        bg.setVideoPath("android.resource://" + getPackageName() + "/" + video);
        bg.start();

        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
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
        initBackground();
    }
}