/*
 * Copyright © 2018 Fangting Chen. CMPUT301. University of Alberta - All Rights Reserved.You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta. You can find a copy of the license in this project. Otherwise please contact fc1@ualberta.ca
 *
 */

package com.example.fc1_subbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Represent the Main Activity
 *
 * @author fc1
 * @see Subscription
 * @see AddToListActivity
 * @see ViewAndEditctivity
 * @see SubAdapter
 */
public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "subs.sav";
    private ListView lv;
    private ArrayList<Subscription> subList;
    private SubAdapter adapter;
    private float totalCharge;


    /**
     * Called when the activity is first created
     *
     * @param savedInstanceState state of an instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.Add);
        Button clearButton = (Button) findViewById(R.id.clear) ;
        lv = (ListView) findViewById(R.id.subList);
        subList = new ArrayList<Subscription>();
        adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, subList);
        lv.setAdapter(adapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when add button is clicked
             *
             * @param view view of the button
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToListActivity.class);
                startActivity(intent);

                adapter.notifyDataSetChanged();



            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when clear button is clicked
             *
             * @param view view of the button
             */
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                subList.clear();
                adapter.clear();
                calCharge();
                saveInFile();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Called when the item is clicked
             * @param adapterView adapter view of the click
             * @param view view within adapter view
             * @param i index of veiw in adapter
             * @param l row id of the item being clicked
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //View and Edit intent
                Intent VEintent = new Intent(MainActivity.this, ViewAndEditctivity.class);
                VEintent.putExtra("sub", subList.get(i));

                startActivity(VEintent);

            }

        });

        //On long click from https://developer.android.com/reference/android/widget/AdapterView.OnItemLongClickListener.html
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            /**
             * Called when the item is long clicked
             *
             * @param adapterView adapter view of the click
             * @param view view within adapter view
             * @param i index of veiw in adapter
             * @param l row id of the item being clicked
             * @return boolean indicating the long click
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                subList.remove(i);
                adapter.notifyDataSetChanged();
                calCharge();
                saveInFile();
                return false;
            }
        });



    }


    /**
     * Called when the activity starts
     */
    @Override
    protected  void onStart() {
        super.onStart();

        loadFromFile();

        calCharge();
        adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, subList);
        lv.setAdapter(adapter);

    }


    /**
     * Loads from the file
     */
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subList = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            subList = new ArrayList<Subscription>();
        }
    }

    /**
     * Save the contents to the file
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            //throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO Auto-generated catch block
            //throw new RuntimeException();
        }
    }

    /**
     * Calculates the total charge of subscriptions in the list
     */
    @SuppressLint("DefaultLocale")
    public void calCharge(){
        totalCharge = 0;

        for (int i = 0; i < subList.size(); i++ ){
            Float temp;
            temp = subList.get(i).getMonthlyCharge();
            totalCharge += (temp);
        }

        TextView charge = (TextView)findViewById(R.id.totalCharge);
        String Scharge;
        Scharge = String.format("%.2f", totalCharge);
        charge.setText(String.format("Total charge: $%s", Scharge));
    }



}
