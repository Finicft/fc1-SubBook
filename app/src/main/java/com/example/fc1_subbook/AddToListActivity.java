package com.example.fc1_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class AddToListActivity extends AppCompatActivity {
    private Subscription newSub ;
    private ArrayList<Subscription> List;
    private ArrayAdapter<Subscription> ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);


        List = new ArrayList<Subscription>();

        Button OkButton = (Button) findViewById(R.id.ok);

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
                //String to Int conversion from https://www.mkyong.com/java/java-convert-string-to-int/
                //Integer bill = Integer.parseInt(Sbill);
                String comment = ETcomment.getText().toString();

                //create new subsciption
                Subscription newSub = new Subscription(name, date, bill, comment);

                //List.add(newSub);

                //passing object to another activity from http://hmkcode.com/android-passing-java-object-another-activity/
                intent.putExtra("newSub", (Serializable) newSub);


                startActivity(intent);
            }

        });



    }
}
