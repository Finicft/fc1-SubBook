package com.example.fc1_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditActivity extends AppCompatActivity {

    private static final String FILENAME = "subs.sav";
    private ArrayList<Subscription> subList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        final Subscription sub = (Subscription) bundle.get("sub");
        //final Subscription sub = subList.get(index);

        Button doneButton = (Button)findViewById(R.id.done);

        loadFromFile();

        final EditText ETname = findViewById(R.id.editName);
        final EditText ETdate = findViewById(R.id.editDate);
        final EditText ETbill = findViewById(R.id.editBill);
        final EditText ETcomment = findViewById(R.id.editComment);

        assert sub != null;
        ETname.setText(sub.getName(), TextView.BufferType.EDITABLE);
        ETdate.setText(sub.getDate(), TextView.BufferType.EDITABLE);
        ETbill.setText(sub.getMonthlyCharge(), TextView.BufferType.EDITABLE);
        ETcomment.setText(sub.getComment(), TextView.BufferType.EDITABLE);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(EditActivity.this,MainActivity.class);

                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();

                //assert sub != null;
                sub.update(name, date, bill,comment);
                //subList.set(index,sub);

                //saveInFile();

                //mainIntent.putExtra("updatedSub", sub);

                startActivity(mainIntent);
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
