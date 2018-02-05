/*
 * Copyright © 2018 Fangting Chen. CMPUT301. University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of
 * the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact fc1@ualberta.ca
 *
 */

package com.example.fc1_subbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;

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
 * Represent the activity where a subscription is added to the list
 *
 * @author fc1
 * @see MainActivity
 */

public class AddToListActivity extends AppCompatActivity {

    private Subscription newSub ;
    private static final String FILENAME = "subs.sav";
    private ArrayList<Subscription> subList;


    /**
     * Called when the acvitity is first created
     *
     * @param savedInstanceState state of the instance
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);


        loadFromFile();

        Button OkButton = (Button) findViewById(R.id.ok);

        OkButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when ok button is clicked
             *
             * @param v view of the button
             */
            //https://stackoverflow.com/questions/11460896/button-to-go-back-to-mainactivity
            public void onClick(View v) {
                Intent mainIntent = new Intent(AddToListActivity.this, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //get info of the new subscription
                EditText ETname = findViewById(R.id.enterName);
                EditText ETdate = findViewById(R.id.enterDate);
                EditText ETbill = findViewById(R.id.enterBill);
                EditText ETcomment = findViewById(R.id.enterComment);

                //Convert info into string
                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();
                //Convert bill into float type
                Float Fbill = Float.parseFloat(bill);


                //create new subsciption
                Subscription newSub = new Subscription(name, date, Fbill, comment);

                subList.add(newSub);
                saveInFile();

                startActivity(mainIntent);
            }

        });



    }

    /**
     * Loads from the file
     *
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

}
