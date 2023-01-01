package com.example.v1;

import static java.lang.Long.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity7 extends AppCompatActivity {

    public Integer i = 0;
    public Long dtNmbr= Long.valueOf(0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        TextView link = (TextView) findViewById(R.id.hiperlink7);
        link.setMovementMethod(LinkMovementMethod.getInstance());

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
        FloatingActionButton fltBt = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        Integer dayMS = 24 * 60 * 60 * 1000;
        Integer hourMS = 60 * 60 * 1000;
        Integer minutsMS = 60 * 1000;
        Integer secondMS = 1000;


        final Handler h = new Handler();
        h.postDelayed(new Runnable() {
            private long time = 0;
            public void run() {
                Date now = new Date();
                long nowMilli = now.getTime();
                long dif = nowMilli-dtNmbr;
                Integer difdays = Math.toIntExact(dif / dayMS);
                Integer difhors = Math.toIntExact((dif-difdays*dayMS )/ hourMS);
                Integer difminuts = Math.toIntExact((dif- (long) difdays *dayMS-difhors*hourMS)/ minutsMS);
                Integer difseconds = Math.toIntExact((dif-difdays*dayMS-difhors*hourMS-difminuts*minutsMS)/ secondMS);

                tv24.setText(String.valueOf(difdays));
                tv26.setText(String.valueOf(difhors));
                tv28.setText(String.valueOf(difminuts));
                tv30.setText(String.valueOf(difseconds));
                pb3.setProgress(difdays);
                pb4.setProgress(difhors);
                pb5.setProgress(difminuts);
                pb6.setProgress(difseconds);
                time += 1000;
                h.postDelayed(this, 1000);
            }
        }, 1000); // 1 second delay (takes millis)

        final ArrayList<String> target = new ArrayList<String>();
        target.add("להפסיק לעשן");
        target.add("להתחיל לעשות ספורט");
        target.add("להפסיק לגלוש באינטרנט");

        final ArrayList<String> from = new ArrayList<String>();
        from.add("1671372438130");
        from.add("1670767638130");
        from.add("1670076438130");

        tv.setText(target.get(0));
        dtNmbr = valueOf(from.get(0));
        final Calendar c = Calendar.getInstance();
        tv18.setText(getDate(dtNmbr,c));
        pb3.setMax(365);
        pb4.setMax(24);
        pb5.setMax(60);
        pb6.setMax(60);

        findViewById(R.id.frwrd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i<target.size()-1) i++;
                else i = 0;
                String txt = target.get(i);
                tv.setText(txt);
                dtNmbr = valueOf(from.get(i));
                tv18.setText(getDate(dtNmbr,c));
            }
        });

        findViewById(R.id.bckwrd).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i>0) i--;
                else i = target.size()-1;
                String txt = target.get(i);
                tv.setText(txt);
                dtNmbr = valueOf(from.get(i));
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
                dtNmbr = valueOf(from.get(i));
                tv18.setText(getDate(dtNmbr,c));
                inpt.setVisibility((View.INVISIBLE));
                entr.setVisibility((View.INVISIBLE));
            }
        });

        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Date dd = new Date();
                Long ddms = dd.getTime();
                from.set(i, (String.valueOf(ddms)));
                dtNmbr = ddms;
                tv18.setText(getDate(dtNmbr,c));
            }
        });
    }

    public String getDate(Long dtNmbr, Calendar c){
        String toReturn = "";
        c.setTimeInMillis(dtNmbr);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return (day + "-" + (month + 1) + "-" + year + " ");
    }
}