package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity9 extends AppCompatActivity {

    public Integer size = 150;
    public String direction = "up";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        TextView link = (TextView) findViewById(R.id.hiperlink9);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        ImageView bl = (ImageView) findViewById(R.id.imageView11);
        ImageButton bt_start = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton bt_stop = (ImageButton) findViewById(R.id.imageButton3);
        MediaPlayer ring1= MediaPlayer.create(MainActivity9.this,R.raw.meditation_voice);

        final Handler h = new Handler();
        bt_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ring1.start();
                direction = "up";
                h.postDelayed(new Runnable() {
                    public void run() {
                        size += 1;
                        bl.getLayoutParams().height = size;
                        bl.getLayoutParams().width =size;
                        bl.requestLayout();
                        if (direction=="up"&&size<800) h.postDelayed(this, 200);
                    }
                }, 200); // 1 second delay (takes millis)
            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ring1.pause();
                direction = "down";
                h.postDelayed(new Runnable() {
                    public void run() {
                        size -= 1;
                        bl.getLayoutParams().height = size;
                        bl.getLayoutParams().width =size;
                        bl.requestLayout();
                        if(direction == "down"&&size>180) h.postDelayed(this, 200);
                    }
                }, 200); // 1 second delay (takes millis)

            }
        });
    }
}