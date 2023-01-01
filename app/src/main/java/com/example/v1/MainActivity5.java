package com.example.v1;

import static android.graphics.Color.BLUE;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;
import static com.google.android.material.snackbar.Snackbar.make;

import static java.security.AccessController.getContext;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity5 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        TextView link = (TextView) findViewById(R.id.hiperlink5);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        Group gp_1 = (Group) findViewById(R.id.op1);
        Group gp_2 = (Group) findViewById(R.id.op2);
        Group gp_3 = (Group) findViewById(R.id.op3);
        TextView bt1 = (TextView) findViewById(R.id.textView12);
        TextView bt2 = (TextView) findViewById(R.id.textView14);
        TextView bt3 = (TextView) findViewById(R.id.textView13);
        gp_2.setVisibility(View.INVISIBLE);
        gp_3.setVisibility(View.INVISIBLE);

        Button bt1_1 = (Button) findViewById(R.id.button1_1);
        Button bt1_2 = (Button) findViewById(R.id.button1_2);
        Button bt1_3 = (Button) findViewById(R.id.button1_3);
        Button bt1_4 = (Button) findViewById(R.id.button1_4);
        Button bt2_1 = (Button) findViewById(R.id.button2_1);
        Button bt2_2 = (Button) findViewById(R.id.button2_2);
        Button bt2_3 = (Button) findViewById(R.id.button2_3);
        Button bt2_4 = (Button) findViewById(R.id.button2_4);
        Button bt2_5 = (Button) findViewById(R.id.button2_5);
        Button bt2_6 = (Button) findViewById(R.id.button2_6);
        Button bt3_1 = (Button) findViewById(R.id.button3_1);
        Button bt3_2 = (Button) findViewById(R.id.button3_2);
        Button bt3_3 = (Button) findViewById(R.id.button3_3);


        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    bt1.setTextAppearance( R.style.white_bg);
                    bt2.setTextAppearance( R.style.grey_bg);
                    bt3.setTextAppearance( R.style.grey_bg);
                }
                bt1.setBackgroundColor(Color.parseColor("#F9FCFF"));
                bt2.setBackgroundColor(Color.parseColor("#A5ACB2"));
                bt3.setBackgroundColor(Color.parseColor("#A5ACB2"));
                gp_1.setVisibility(View.VISIBLE);
                gp_2.setVisibility((View.INVISIBLE));
                gp_3.setVisibility((View.INVISIBLE));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    bt2.setTextAppearance( R.style.white_bg);
                    bt1.setTextAppearance( R.style.grey_bg);
                    bt3.setTextAppearance( R.style.grey_bg);
                }
                bt2.setBackgroundColor(Color.parseColor("#F9FCFF"));
                bt1.setBackgroundColor(Color.parseColor("#A5ACB2"));
                bt3.setBackgroundColor(Color.parseColor("#A5ACB2"));
                gp_1.setVisibility(View.INVISIBLE);
                gp_2.setVisibility((View.VISIBLE));
                gp_3.setVisibility((View.INVISIBLE));

                bt2_1.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Snackbar sb = make(view, "כל הכבוד שנכנסת, הגיע הזמן לרכוש כלי שיעזור לך ביומיום", 5000);
                        sb.setTextColor(BLUE);

                        View v = sb.getView();
                        TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                        final Typeface typeface = ResourcesCompat.getFont(v.getContext(), R.font.ganclm_boldwebfont);
                        textView.setTypeface(typeface);

                        sb.getView().setBackgroundColor(Color.rgb(119,202,218));
                        sb.show();
                        (new Handler()).postDelayed(this::delay21, 5000);
                    }
                    private void delay21() {
                        Intent i = new Intent(MainActivity5.this, MainActivity21.class);
                        startActivity(i);
                    }
                });

                bt2_2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Snackbar sb = make(view, "כל הכבוד שנכנסת, הגיע הזמן לרכוש כלי שיעזור לך ביומיום", 5000);
                        sb.setTextColor(BLUE);

                        View v = sb.getView();
                        TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                        final Typeface typeface = ResourcesCompat.getFont(v.getContext(), R.font.ganclm_boldwebfont);
                        textView.setTypeface(typeface);

                        sb.getView().setBackgroundColor(Color.rgb(119,202,218));
                        sb.show();
                        (new Handler()).postDelayed(this::delay22, 5000);
                    }
                    private void delay22() {
                        Intent i = new Intent(MainActivity5.this, MainActivity22.class);
                        startActivity(i);
                    }
                });

                bt2_3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Snackbar sb = make(view, "כל הכבוד שנכנסת, הגיע הזמן לרכוש כלי שיעזור לך ביומיום", 5000);
                        sb.setTextColor(BLUE);

                        View v = sb.getView();
                        TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                        final Typeface typeface = ResourcesCompat.getFont(v.getContext(), R.font.ganclm_boldwebfont);
                        textView.setTypeface(typeface);

                        sb.getView().setBackgroundColor(Color.rgb(119,202,218));
                        sb.show();
                        (new Handler()).postDelayed(this::delay23, 5000);
                    }
                    private void delay23() {
                        Intent i = new Intent(MainActivity5.this, MainActivity23.class);
                        startActivity(i);
                    }
                });

                bt2_4.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Snackbar sb = make(view, "כל הכבוד שנכנסת, הגיע הזמן לרכוש כלי שיעזור לך ביומיום", 5000);
                        sb.setTextColor(BLUE);

                        View v = sb.getView();
                        TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                        final Typeface typeface = ResourcesCompat.getFont(v.getContext(), R.font.ganclm_boldwebfont);
                        textView.setTypeface(typeface);

                        sb.getView().setBackgroundColor(Color.rgb(119,202,218));
                        sb.show();
                        (new Handler()).postDelayed(this::delay24, 5000);
                    }
                    private void delay24() {
                        Intent i = new Intent(MainActivity5.this, MainActivity24.class);
                        startActivity(i);
                    }
                });

                bt2_5.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Snackbar sb = make(view, "כל הכבוד שנכנסת, הגיע הזמן לרכוש כלי שיעזור לך ביומיום", 5000);
                        sb.setTextColor(BLUE);

                        View v = sb.getView();
                        TextView textView = v.findViewById(com.google.android.material.R.id.snackbar_text);
                        final Typeface typeface = ResourcesCompat.getFont(v.getContext(), R.font.ganclm_boldwebfont);
                        textView.setTypeface(typeface);

                        sb.getView().setBackgroundColor(Color.rgb(119,202,218));
                        sb.show();
                        (new Handler()).postDelayed(this::delay25, 5000);
                    }
                    private void delay25() {
                        Intent i = new Intent(MainActivity5.this, MainActivity25.class);
                        startActivity(i);
                    }
                });

                bt2_6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(MainActivity5.this, ScrollingActivity.class);
                        //myIntent.putExtra("key", value); //Optional parameters
                        MainActivity5.this.startActivity(myIntent);
                    }
                });
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    bt3.setTextAppearance( R.style.white_bg);
                    bt2.setTextAppearance( R.style.grey_bg);
                    bt1.setTextAppearance( R.style.grey_bg);
                }
                bt3.setBackgroundColor(Color.parseColor("#F9FCFF"));
                bt1.setBackgroundColor(Color.parseColor("#A5ACB2"));
                bt2.setBackgroundColor(Color.parseColor("#A5ACB2"));
                gp_1.setVisibility(View.INVISIBLE);
                gp_2.setVisibility((View.INVISIBLE));
                gp_3.setVisibility((View.VISIBLE));
            }
        });
    }
}