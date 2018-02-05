package com.example.fc1_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewAndEditctivity extends AppCompatActivity {

    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Subscription sub = (Subscription) bundle.get("Sub");

        TextView nameView = findViewById(R.id.textName);
        nameView.setText("Name:\n" + sub.getName());
        TextView dateView = findViewById(R.id.textDate);
        dateView.setText("Date:\n" + sub.getDate());
        TextView billView = findViewById(R.id.textBill);
        billView.setText("Monthly Charge:\n" + sub.getMonthlyCharge());
        TextView commentView = findViewById(R.id.textComment);
        commentView.setText("Comment:\n" + sub.getComment());

        //EditText



        Button editButton = (Button)findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAndEditctivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
