package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {

    public Integer i = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        TextView link = (TextView) findViewById(R.id.hiperlink6);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        final ArrayList<String> list = new ArrayList<String>();
        list.add("אם אתם רוצים להיות מאושרים, הציבו לעצמכם מטרה, שחררו לחופשי את האנרגיה שלכם, והיו מלאי השראה ותקווה");
        list.add("אם אתם מתחילים לחשוב שהבעיה נמצאת 'שם', עצרו מיד. הבעיה האמיתית היא עצם המחשבה הזו");
        list.add("אנחנו יכולים ליצור שלום בתוכנו אם אנחנו מרפים מהרצון לשנות את העבר ולשלוט בעתיד");
        list.add("כל מה שמוחו של אדם יכול לקבל ולהאמין בו, הוא גם יכול להשיג");
        list.add("אל תחתרו להצלחה, שאפו להיות בעלי ערך");

        TextInputEditText inpt = (TextInputEditText) findViewById(R.id.inpt);
        Button entr = (Button) findViewById(R.id.button5);
        TextView tv = (TextView)findViewById(R.id.textView15);



        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i<list.size()-1) i++;
                else i = 0;
                TextView tv = (TextView)findViewById(R.id.textView15);
                String txt = list.get(i);
                tv.setText(txt);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(i>0) i--;
                else i = list.size()-1;
                String txt = list.get(i);
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
                list.add(t);
                i=list.size()-1;
                tv.setText(t);
                inpt.setVisibility((View.INVISIBLE));
                entr.setVisibility((View.INVISIBLE));
            }
        });
    }
}