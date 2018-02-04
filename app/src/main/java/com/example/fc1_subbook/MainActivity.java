package com.example.fc1_subbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v7.app.AppCompatActivity;
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
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "subs.sav";
    private ListView OldList;
    private ArrayList<Subscription> subList;
    private SubAdapter adapter;
    private Subscription newSub;

    private ArrayList<String> names;
    private ArrayAdapter<String> nameAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = (Button) findViewById(R.id.Add);
        OldList = (ListView) findViewById(R.id.subList);
        subList = new ArrayList<Subscription>();
        //Subscription newSub2 = new Subscription("netflix", "1990-22-44", "$32","null");
        //  adapter = new
        adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, subList);
        //subList.add(newSub2);
        //Subscription newSub1 = new Subscription("Sub2", "1990-22-44", "$32","null");
        //subList.add(newSub1);
        OldList.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setResult(RESULT_OK);
                Intent intent = new Intent(view.getContext(), AddToListActivity.class);
                startActivity(intent);
                //Intent addIntent = getIntent();
                //Subscription newSub =   (Subscription) addIntent.getSerializableExtra("newSub");
                //subList.add(newSub);

                //adapter.notifyDataSetChanged();
                //saveInFile();
            }
        });


    }



    @Override
    protected  void onStart() {
        super.onStart();

        //loadFromFile();

        //adapter = new SubAdapter(getApplicationContext(), R.layout.row_view, subList);
        //OldList.setAdapter(adapter);


    }

    public void AddToList (View view){
        //Intent addIntent = getIntent();
        //Subscription newSub =   (Subscription) addIntent.getSerializableExtra("newSub");
        //subList.add(newSub);

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
            throw new RuntimeException();
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
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



}
