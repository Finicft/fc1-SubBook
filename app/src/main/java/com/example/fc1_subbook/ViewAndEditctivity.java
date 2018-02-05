package com.example.fc1_subbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.lang.UScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ViewAndEditctivity extends AppCompatActivity {

    private Subscription sub;
    private Subscription updatedSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Subscription sub = (Subscription) bundle.get("sub");

        final EditText ETname = findViewById(R.id.editName);
        final EditText ETdate = findViewById(R.id.editDate);
        final EditText ETbill = findViewById(R.id.editBill);
        final EditText ETcomment = findViewById(R.id.editComment);

        assert sub != null;
        ETname.setText(sub.getName(), TextView.BufferType.EDITABLE);
        ETdate.setText(sub.getDate(), TextView.BufferType.EDITABLE);
        ETbill.setText(sub.getMonthlyCharge(), TextView.BufferType.EDITABLE);
        ETcomment.setText(sub.getComment(), TextView.BufferType.EDITABLE);

        Button doneButton = (Button)findViewById(R.id.done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ViewAndEditctivity.this,MainActivity.class);

                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();

                Subscription updatedSub = new Subscription(name, date, bill, comment);


                //saveInFile();
                //mainIntent.putExtra("updatedSub", updatedSub);

                startActivity(mainIntent);
            }
        });

        /*Button editButton = (Button)findViewById(R.id.edit);

        editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ViewAndEditctivity.this,MainActivity.class);

                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();

                //assert sub != null;
                //sub.update(name, date, bill,comment);
                //subList.set(index,sub);

                Subscription updatedSub = new Subscription(name, date, bill, comment);
                //saveInFile();
                mainIntent.putExtra("updatedsub", updatedSub);

                startActivity(mainIntent);

            }
        });

        /*Button backButton = (Button)findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ViewAndEditctivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });*/


    }


}
