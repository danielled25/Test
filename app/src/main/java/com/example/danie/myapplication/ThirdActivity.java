package com.example.danie.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class ThirdActivity extends AppCompatActivity {
    private Bitmap animalImage = null;
    private int rbID = 0;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
            if(rb1.isChecked()){
                rbID = rb1.getId();
            } else if (rb2.isChecked()){
                rbID = rb2.getId();
            } else if (rb3.isChecked()){
                rbID = rb3.getId();
            } else{
                rbID = rb3.getId();
            }

            outState.putInt("radioButtonID", rbID);

    }
    protected void onRestoreInstanceState(Bundle savedState)
    {

            rbID = savedState.getInt("radioButtonID");
            if(rbID == rb1.getId()){
                rb1.setChecked(true);
            }  else if(rbID == rb2.getId()){
                rb2.setChecked(true);
            } else if(rbID == rb3.getId()){
                rb3.setChecked(true);
            } else{
                rb3.getId();
            }



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final TextView text = findViewById(R.id.pickText);
        final TextView underText = findViewById(R.id.underText);

        final RadioGroup rg = findViewById(R.id.rbGroup);
        rb1 = findViewById(R.id.option1);
        rb2 = findViewById(R.id.option2);
        rb3 = findViewById(R.id.option3);

        final Intent returnIntent = new Intent();
        final Button go = findViewById(R.id.go);

        go.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (rb1.isChecked()) {
                    animalImage = BitmapFactory.decodeResource(getResources(), R.drawable.chicken);
                    text.setText(getString(R.string.pick_chicken));

                } else if(rb2.isChecked()){
                    animalImage = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
                    text.setText(getString(R.string.pick_dog));

                } else if(rb3.isChecked()){
                    animalImage = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
                    text.setText(getString(R.string.pick_cat));
               } else {
                    text.setText(getString(R.string.no_selection));
                    underText.setText("");

                }

                if(animalImage != null) {
                    underText.setText(getString(R.string.sent_to_main));
                    returnIntent.putExtra("bitmap", animalImage);
                    setResult(Activity.RESULT_OK, returnIntent);
                }
            }
        });


    }
}
