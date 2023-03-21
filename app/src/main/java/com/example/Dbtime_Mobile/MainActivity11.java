package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.time.OffsetTime;
import java.util.Calendar;

public class MainActivity11 extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        View lo = findViewById(R.id.bg);
        SeekBar sb1 = (SeekBar) findViewById(R.id.seekBar2);
        TextView tv = (TextView) findViewById(R.id.textView32);

        switch (day) {
            case Calendar.SUNDAY:
               // lo.setBackgroundResource(R.raw.day);
                break;
            case Calendar.MONDAY:
                // Current day is Monday
                break;
            case Calendar.TUESDAY:
                // etc.
                break;
            case Calendar.WEDNESDAY:
                // etc.
                break;
            case Calendar.THURSDAY:
                // etc.
                break;
            case Calendar.FRIDAY:
                // etc.
                break;
            case Calendar.SATURDAY:
                // etc.
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


    }
}