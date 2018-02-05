/*
 * Copyright © 2018 Fangting Chen. CMPUT301. University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of
 * the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact fc1@ualberta.ca
 *
 */

package com.example.fc1_subbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter to the subscription array list
 *
 * @author fc1
 * @see  MainActivity
 */
public class SubAdapter extends ArrayAdapter<Subscription> {
    private Context context;
    private int resource;
    private ArrayList<Subscription> subs = null;

    /**
     * Constructer of the adapter
     *
     * @param context context of tha app
     * @param resource address of the layout being adapted to
     * @param subs list of subscriptions
     */
    public SubAdapter(@NonNull Context context, int resource, ArrayList<Subscription> subs) {
        super(context, resource, subs);

        this.context = context;
        this.resource = resource;
        this.subs = subs;

    }

    /**
     * Gets the view of the item
     *
     * @param position position of the item in the adapter
     * @param convertView orginally view
     * @param parent parent of the view
     * @return
     */
    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        Subscription sub = subs.get(position);
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_view, parent, false);
        }

        TextView subNameTV = (TextView)convertView.findViewById(R.id.subName);
        TextView chargeTV = (TextView)convertView.findViewById(R.id.subCharge);

        subNameTV.setText(sub.getName());
        chargeTV.setText(sub.getMonthlyCharge().toString());

        return convertView;

    }
}
