package com.example.v1;

import static java.lang.String.valueOf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.BaseMenuPresenter;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.v1.databinding.ActivityScrollingBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScrollingActivity extends AppCompatActivity  {

    private ActivityScrollingBinding binding;
    String[] op = {"0", "1", "2", "3", "4", "5"};
    String[] op24 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
            "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    public Integer sp1_1_id,sp1_2_id,sp1_3_id=0;
    Integer sp2_1_id,sp2_2_id,sp2_3_id;
    Integer sp3_1_id,sp3_2_id,sp3_3_id;
    Integer sp4_1_id,sp14_2_id,sp4_3_id;
    Integer sp5_1_id,sp5_2_id,sp5_3_id;
    Integer sp6_1_id,sp6_2_id,sp6_3_id;
    Integer sp7_1_id,sp7_2_id,sp7_3_id;
    String lvl1_1,lvl1_2,lvl1_3="3";
    String lvl2_1,lvl2_2,lvl2_3="3";
    String lvl3_1,lvl3_2,lvl3_3="3";
    String lvl4_1,lvl4_2,lvl4_3="3";
    String lvl5_1,lvl5_2,lvl5_3="3";
    String lvl6_1,lvl6_2,lvl6_3="3";
    String lvl7_1,lvl7_2,lvl7_3="3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Spinner sp1_1 = (Spinner) findViewById(R.id.spinner1_1);
        Spinner sp1_2 = (Spinner) findViewById(R.id.spinner1_2);
        Spinner sp1_3 = (Spinner) findViewById(R.id.spinner1_3);
        Spinner sp2_1 = (Spinner) findViewById(R.id.spinner2_1);
        Spinner sp2_2 = (Spinner) findViewById(R.id.spinner2_2);
        Spinner sp2_3 = (Spinner) findViewById(R.id.spinner2_3);
        Spinner sp3_1 = (Spinner) findViewById(R.id.spinner3_1);
        Spinner sp3_2 = (Spinner) findViewById(R.id.spinner3_2);
        Spinner sp3_3 = (Spinner) findViewById(R.id.spinner3_3);
        Spinner sp4_1 = (Spinner) findViewById(R.id.spinner4_1);
        Spinner sp4_2 = (Spinner) findViewById(R.id.spinner4_2);
        Spinner sp4_3 = (Spinner) findViewById(R.id.spinner4_3);
        Spinner sp5_1 = (Spinner) findViewById(R.id.spinner5_1);
        Spinner sp5_2 = (Spinner) findViewById(R.id.spinner5_2);
        Spinner sp5_3 = (Spinner) findViewById(R.id.spinner5_3);
        Spinner sp7_1 = (Spinner) findViewById(R.id.spinner7_1);
        Spinner sp7_2 = (Spinner) findViewById(R.id.spinner7_2);
        Spinner sp9_1 = (Spinner) findViewById(R.id.spinner9_1);
        Spinner sp9_2 = (Spinner) findViewById(R.id.spinner9_2);
        Spinner sp9_3 = (Spinner) findViewById(R.id.spinner9_3);
        Spinner sp10_1 = (Spinner) findViewById(R.id.spinner10_1);
        Spinner sp10_2 = (Spinner) findViewById(R.id.spinner10_2);
        Spinner sp10_3 = (Spinner) findViewById(R.id.spinner10_3);
        Spinner sp11_1 = (Spinner) findViewById(R.id.spinner11_1);
        Spinner sp11_2 = (Spinner) findViewById(R.id.spinner11_2);
        Spinner sp11_3 = (Spinner) findViewById(R.id.spinner11_3);
        Spinner sp12_1 = (Spinner) findViewById(R.id.spinner12_1);
        // Spinner sp7_3 = (Spinner) findViewById(R.id.spinner7_3);
        SeekBar sb6_1 = (SeekBar) findViewById(R.id.sb6_1);
        SeekBar sb6_2 = (SeekBar) findViewById(R.id.sb6_2);
        SeekBar sb6_3 = (SeekBar) findViewById(R.id.sb6_3);
        TextView prcnt7_1 = (TextView) findViewById(R.id.prcnt6_1);
        TextView prcnt7_2 = (TextView) findViewById(R.id.prcnt6_2);
        TextView prcnt7_3 = (TextView) findViewById(R.id.prcnt6_3);
        TextView snd2 = (TextView) findViewById(R.id.snd2);

        CheckBox cb7_1y = (CheckBox) findViewById(R.id.cb7_1y);
        CheckBox cb7_1n = (CheckBox) findViewById(R.id.cb7_1n);
        TextInputEditText rmrk7_1 = (TextInputEditText) findViewById(R.id.rmrk7_1);
        CheckBox cb7_2y = (CheckBox) findViewById(R.id.cb7_2y);
        CheckBox cb7_2n = (CheckBox) findViewById(R.id.cb7_2n);
        TextInputEditText rmrk7_2 = (TextInputEditText) findViewById(R.id.rmrk7_2);
        TextInputEditText rmrk7_3 = (TextInputEditText) findViewById(R.id.rmrk7_3);
        CheckBox cb7_7y = (CheckBox) findViewById(R.id.cb7_7y);
        CheckBox cb7_7n = (CheckBox) findViewById(R.id.cb7_7n);
        CheckBox cb7_8y = (CheckBox) findViewById(R.id.cb7_8y);
        CheckBox cb7_8n = (CheckBox) findViewById(R.id.cb7_8n);

        TextView setCurrentDate = (TextView) findViewById(R.id.theDate);
        Button pic = (Button) findViewById(R.id.cngDate);
        setCurrentDate.setText(getCurrentDate() + " ");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, R.layout.spinner_item, op);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> ad24 = new ArrayAdapter<String>(this, R.layout.spinner_item, op24);
        ad24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button bt6 = (Button) findViewById(R.id.button6);

        sp1_1.setAdapter(ad);
        sp1_2.setAdapter(ad);
        sp1_3.setAdapter(ad);
        sp2_1.setAdapter(ad);
        sp2_2.setAdapter(ad);
        sp2_3.setAdapter(ad);
        sp3_1.setAdapter(ad);
        sp3_2.setAdapter(ad);
        sp3_3.setAdapter(ad);
        sp4_1.setAdapter(ad);
        sp4_2.setAdapter(ad);
        sp4_3.setAdapter(ad);
        sp5_1.setAdapter(ad);
        sp5_2.setAdapter(ad);
        sp5_3.setAdapter(ad);
        sp7_1.setAdapter(ad);
        sp7_2.setAdapter(ad24);
        //sp7_3.setAdapter(ad);
        sp9_1.setAdapter(ad);
        sp9_2.setAdapter(ad);
        sp9_3.setAdapter(ad);
        sp10_1.setAdapter(ad);
        sp10_2.setAdapter(ad);
        sp10_3.setAdapter(ad);
        sp11_1.setAdapter(ad);
        sp11_2.setAdapter(ad);
        sp11_3.setAdapter(ad);
        sp12_1.setAdapter(ad);

        sb6_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && !seekBar.isInTouchMode()) seek(progress);
            }

            private void seek(int progress) {
                lvl7_1 = String.valueOf(sb6_1.getProgress());
                prcnt7_1.setText(lvl7_1 + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }
        });

        sb6_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && !seekBar.isInTouchMode()) seek(progress);
            }

            private void seek(int progress) {
                lvl7_2 = String.valueOf(sb6_2.getProgress());
                prcnt7_2.setText(lvl7_2 + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }
        });

        sb6_3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && !seekBar.isInTouchMode()) seek(progress);
            }

            private void seek(int progress) {
                lvl7_3 = String.valueOf(sb6_3.getProgress());
                prcnt7_3.setText(lvl7_3 + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                seek(seekBar.getProgress());
            }
        });

        Button entr = findViewById(R.id.button6);
        bt6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lvl1_1 = sp1_1.getSelectedItem().toString();
                lvl1_2 = sp1_2.getSelectedItem().toString();
                lvl1_3 = sp1_3.getSelectedItem().toString();

                System.out.println("bt6: " + lvl1_1 + " " + lvl1_2 + " " + lvl1_3);
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScrollingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                setCurrentDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + " ");
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        cb7_1n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_1y.setChecked(false);
            }
        });
        cb7_1y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_1n.setChecked(false);
            }
        });
        cb7_2n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_2y.setChecked(false);
            }
        });
        cb7_2y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_2n.setChecked(false);
            }
        });
        cb7_7n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_7y.setChecked(false);
            }
        });
        cb7_7y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_7n.setChecked(false);
            }
        });
        cb7_8n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_8y.setChecked(false);
            }
        });
        cb7_8y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cb7_8n.setChecked(false);
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                snd2.setVisibility((View.VISIBLE));
                (new Handler()).postDelayed(this::yourMethod, 2000);


            }

            private void yourMethod() {
                Intent i = new Intent(ScrollingActivity.this, MainActivity5.class);
                startActivity(i);
            }
        });

        //Intent i = new Intent(AlertDetails.this, MainActivity.class);
        //startActivity(i);
    }
    public String getCurrentDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
 }