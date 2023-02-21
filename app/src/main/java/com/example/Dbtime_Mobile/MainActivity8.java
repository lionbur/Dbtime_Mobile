package com.example.Dbtime_Mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity8 extends AppCompatActivity {

    public ArrayList quotas,targets;
    ListView simpleList;
    String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand","India", "China", "australia", "Portugle", "America", "NewZealand"};
    int flags[] = {R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo,R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo, R.raw.bmo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);


        Bundle extras = getIntent().getExtras();
        targets = extras.getStringArrayList("targets");
        quotas = extras.getStringArrayList("quotas");

       // Log.d("RRubi","#1: "+from);
       // Log.d("RRubi","#2: "+targets);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), quotas, targets);
        simpleList.setAdapter(customAdapter);

        TextView link = (TextView) findViewById(R.id.hiperlink8);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
    public void getTargetsFromBackEnd(){
        Bundle extras = getIntent().getExtras();
        from = extras.getStringArrayList("from");
        target = extras.getStringArrayList("targets");
        Log.d("Rubi","#1: "+from);
        Log.d("Rubi","#2: "+target);
    }

     */
}
