package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity8 extends AppCompatActivity {

    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand","India", "China", "australia", "Portugle", "America", "NewZealand"};
    int flags[] = {R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo,R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);
        simpleList.setAdapter(customAdapter);

        TextView link = (TextView) findViewById(R.id.hiperlink8);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
