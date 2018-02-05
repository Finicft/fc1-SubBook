package com.example.fc1_subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ViewAndEditctivity extends AppCompatActivity {

    private Subscription sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_editctivity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Subscription sub = (Subscription) bundle.get("Sub");

        EditText nameView = findViewById(R.id.editName);
        nameView.setHint(sub.getName());
        EditText dateView = findViewById(R.id.editDate);
        dateView.setHint(sub.getDate());
        EditText billView = findViewById(R.id.editDate);
        billView.setHint(sub.getMonthlyCharge());
        EditText commentView = findViewById(R.id.editComment);
        commentView.setHint(sub.getComment());

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
