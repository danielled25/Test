package com.example.danie.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        TextView t = findViewById(R.id.display);
        Intent i = getIntent();
        String msg = i.getStringExtra("dataInTextField");
        t.setText(msg);

    }
}
