package com.example.fc1_subbook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;

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
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class AddToListActivity extends AppCompatActivity {
    //private static final String FILENAME = "subs.sav";
    private Subscription newSub ;
    private ArrayList<Subscription> List;
    private static final String FILENAME = "subs.sav";
    private ArrayList<Subscription> subList;
    //private SubAdapter adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);


        loadFromFile();

        Button OkButton = (Button) findViewById(R.id.ok);
        //adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, List);

        OkButton.setOnClickListener(new View.OnClickListener() {

            //https://stackoverflow.com/questions/11460896/button-to-go-back-to-mainactivity
            public void onClick(View v) {
                Intent intent = new Intent(AddToListActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                //get info of the new subscription
                EditText ETname = findViewById(R.id.enterName);
                EditText ETdate = findViewById(R.id.enterDate);
                EditText ETbill = findViewById(R.id.enterBill);
                EditText ETcomment = findViewById(R.id.enterComment);


                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();

                //create new subsciption
                Subscription newSub = new Subscription(name, date, bill, comment);


                //passing object to another activity from http://hmkcode.com/android-passing-java-object-another-activity/
                //intent.putExtra("newSub", newSub);
                subList.add(newSub);
                saveInFile();


                startActivity(intent);
            }

        });



    }

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
        }catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException();
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
