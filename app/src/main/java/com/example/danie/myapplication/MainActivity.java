package com.example.danie.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //ID code
    static final int CODE = 1
    //Declare here for onSaveInstanceState
    

    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

      if(et != null && et.getText() != null) {
          CharSequence userText = et.getText();
          outState.putCharSequence("savedText", userText);
      }
    }
    protected void onRestoreInstanceState(Bundle savedState)
    {
        if(et != null) {
            CharSequence userText = savedState.getCharSequence("savedText");
            et.setText(userText);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //===Part 1: Encrypt and decrypt the sentence===
        //Button that deals with the click
        final Button encrypt = findViewById(R.id.button);
        //Text field for user information
        et = findViewById(R.id.editText);

        //What happens when encrypt button is clicked
        encrypt.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                //Get the text entered into the text field
                Editable e = et.getText();
                //Send it to a method
                startEncryptActivity(e);

            }
        });

        //===Part 2: Trigger on first activity sends something to another for display===
        //Button that launches the text activity - find it by ID from the xml
        final Button launchTextActivity = findViewById(R.id.launchActivity);
        //Once the button is clicked
        launchTextActivity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Send the contents of the text field to the method startTextActivity
                startTextActivity(et.getText().toString());
            }
        });


        /*===Part 3: Trigger on first activity launches third activity that
         sends back to the first activity for display===*/
        //Button that gets button id from xml and will launch the third activity on click
        final Button launchThirdActivity = findViewById(R.id.button2);
        //Deal with the click
        launchThirdActivity.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               //Launch the third activity
                startThirdActivity();
            }

        });

        /*===Part 4: Launch Alert===*/
        //Get the button by ID from xml that, once clicked, launches alert
        final Button launchAlert = findViewById(R.id.launchAlert);
        //Deal with the click
        launchAlert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Call method that will launch the alert
                

            }
        });

        /*===Part 5: Launch Notification to make call===*/
        //Get the button by ID from xml that, once clicked, launches notification
        final Button launchNotification = findViewById(R.id.launchNotification);
        

        final Button launchSongPicker = findViewById(R.id.pickSong);

    }

    private void startTextActivity(String text){
        //Create an intent to send to the TextActivity
        Intent in = new Intent(MainActivity.this, TextActivity.class);
        //Put the text field content into the intent
        in.putExtra("dataInTextField", text);
        //Send the intent to the activity and start.
        startActivity(in);
    }

    private void startEncryptActivity(Editable e){
        //Create an intent to the second activity
        Intent in = new Intent(MainActivity.this, SecondActivity.class);
        //Pass the text field contents into the intent
        in.putExtra("dataInEditText", e.toString());
        //Start the second activity and send the intent
        startActivity(in);
    }

    private void startThirdActivity() {
        //Create an intent to the third activity
        Intent i = new Intent(MainActivity.this, ThirdActivity.class);
        //Start the activity to get the result
        startActivityForResult(i, CODE);


        //Returning an image
        //Create the bitmap based on the byteArray
        if(getIntent().hasExtra("byteArray")){
            //Get the image view from xml by ID
            ImageView preview = findViewById(R.id.imageView);
            //Create the bitmap
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"),0
                    ,getIntent().getByteArrayExtra("byteArray").length);
            //Set the contents of the image view to the bitmap
            preview.setImageBitmap(b);

        }

    }

    @Override
    //Deals with the result from startActivityForResult
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Bitmap b = data.getParcelableExtra("bitmap");
                ImageView preview = findViewById(R.id.imageView);
                preview.setImageBitmap(b);


            }
        }
    }




    }
