/*
 * Copyright © 2018 Fangting Chen. CMPUT301. University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of
 * the Code of Student Behaviour at University of Alberta. You can find a copy of
 * the license in this project. Otherwise please contact fc1@ualberta.ca
 *
 */

package com.example.fc1_subbook;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Represnets the view and edit activity
 */
public class ViewAndEditctivity extends AppCompatActivity {

    private Subscription sub;
    private Subscription updatedSub;

    /**
     * Called when the activity is first created
     *
     * @param savedInstanceState state of the instance
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //get object sub from MainActicity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        Subscription sub = (Subscription) bundle.get("sub");

        final EditText ETname = findViewById(R.id.editName);
        final EditText ETdate = findViewById(R.id.editDate);
        final EditText ETbill = findViewById(R.id.editBill);
        final EditText ETcomment = findViewById(R.id.editComment);

        //Set texts of the EditTexts to corresponding attributes
        assert sub != null;
        ETname.setText(sub.getName(), TextView.BufferType.EDITABLE);
        ETdate.setText(sub.getDate(), TextView.BufferType.EDITABLE);
        ETbill.setText(sub.getMonthlyCharge().toString(), TextView.BufferType.EDITABLE);
        ETcomment.setText(sub.getComment(), TextView.BufferType.EDITABLE);

        Button doneButton = (Button)findViewById(R.id.done);

        doneButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when done button is clicked
             *
             * @param view view of the done button
             */
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(ViewAndEditctivity.this,MainActivity.class);


                String name = ETname.getText().toString();
                String date = ETdate.getText().toString();
                String bill = ETbill.getText().toString();
                String comment = ETcomment.getText().toString();
                Float Fbill = Float.parseFloat(bill);

                Subscription updatedSub = new Subscription(name, date, Fbill, comment);
                startActivity(mainIntent);
            }
        });



    }


}
