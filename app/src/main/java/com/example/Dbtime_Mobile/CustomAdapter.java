package com.example.Dbtime_Mobile;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList targetsList;
    ArrayList quotasList;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList targets, ArrayList quotas) {
        this.context = context;
        this.targetsList = targets;
        this.quotasList = quotas;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return targetsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView targets = (TextView) view.findViewById(R.id.textView);
        TextView quotas = (TextView) view.findViewById(R.id.textView1);
        targets.setText(targetsList.get(i).toString());
        quotas.setText(quotasList.get(i).toString());
        return view;
    }
}