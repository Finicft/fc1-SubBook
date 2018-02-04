package com.example.fc1_subbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2018-02-04.
 */

public class SubAdapter extends ArrayAdapter<Subscription> {
    Context context;
    int resource;
    ArrayList<Subscription> subs = null;

    public SubAdapter(@NonNull Context context, int resource, ArrayList<Subscription> subs) {
        super(context, resource, subs);

        this.context = context;
        this.resource = resource;
        this.subs = subs;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Subscription sub = subs.get(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        }

        TextView subNameTV = (TextView)convertView.findViewById(R.id.subName);
        TextView chargeTV = (TextView)convertView.findViewById(R.id.subCharge);

        subNameTV.setText(sub.getName());
        chargeTV.setText(sub.getMonthlyCharge());

        return convertView;

    }
}
