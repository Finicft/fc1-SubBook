package com.example.fc1_subbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "subs.sav";
    private ListView lv;
    private ArrayList<Subscription> subList;
    private SubAdapter adapter;



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
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToListActivity.class);
                startActivity(intent);

                adapter.notifyDataSetChanged();



            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                subList.clear();
                adapter.clear();
                saveInFile();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //View and Edit intent
                Intent VEintent = new Intent(MainActivity.this, ViewAndEditctivity.class);
                VEintent.putExtra("sub", subList.get(i));

                startActivity(VEintent);

                /*Intent editIntent = getIntent();
                Bundle bundle = editIntent.getExtras();
                assert bundle != null;
                Subscription sub = (Subscription) bundle.getSerializable("updatedSub");
                subList.set(i, sub);
                adapter.notifyDataSetChanged();
                saveInFile();*/

            }

        });

        //On long click from https://developer.android.com/reference/android/widget/AdapterView.OnItemLongClickListener.html
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                subList.remove(i);
                adapter.notifyDataSetChanged();
                saveInFile();
                return false;
            }
        });



    }



    @Override
    protected  void onStart() {
        super.onStart();

        loadFromFile();
        adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, subList);
        lv.setAdapter(adapter);

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
