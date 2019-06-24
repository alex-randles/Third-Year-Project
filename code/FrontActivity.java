package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FrontActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        // PATIENT SETTINGS
        final String LANGUAGE = getIntent().getStringExtra("LANGUAGE");
        final String CAPTIONS = getIntent().getStringExtra("CAPTIONS");
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");

        // Intialize buttons from layout;
        Button homeBTN = (Button)findViewById(R.id.homeBTN);
        Button logoutBTN = (Button)findViewById(R.id.logoutBTN);

        Button foodBTN = (Button)findViewById(R.id.foodBTN);
        Button locationsBTN = (Button)findViewById(R.id.locationsBTN);
        Button musicBTN = (Button)findViewById(R.id.musicBTN);
        Button animalsBTN = (Button)findViewById(R.id.animalsBTN);
        Button vehiclesBTN = (Button)findViewById(R.id.vehiclesBTN);
        Button householdBTN = (Button)findViewById(R.id.householdBTN);

        // HOME ACTIVITY
        homeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(getApplicationContext(), CarerActivity.class);
                homeIntent.putExtra("CARER_NAME",CARER_NAME);
                startActivity(homeIntent);
            }
        });
        // LOG OUT BUTTON
        logoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
            }
        });

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            foodBTN.setText("");
            locationsBTN.setText("");
            musicBTN.setText("");
            animalsBTN.setText("");
            vehiclesBTN.setText("");
            householdBTN.setText("");
        }

        // BUTTON ACTION
        foodBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ANNOUNCE WORD IN SELECTED LANGUAGE GO TO NEXT CATEGORY
                nextActivity(FoodActivity.class,R.string.food,CARER_NAME,LANGUAGE,CAPTIONS,R.raw.food,R.raw.food_es);
            }
        });
        animalsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANNOUNCE WORD
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.animals,R.drawable.animals,R.raw.animal,R.raw.animal_es);
            }
        });
        locationsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANNOUNCE WORD
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.locations,R.drawable.locations,R.raw.location,R.raw.locations_es);
            }
        });
        vehiclesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANNOUNCE WORD
                nextActivity(VehiclesActivity.class,R.string.vehicles,CARER_NAME,LANGUAGE,CAPTIONS,R.raw.vehicles,R.raw.vehicles_es);
            }
        });
        musicBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANNOUNCE WORD
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.music,R.drawable.music,R.raw.music,R.raw.music_es);
            }
        });
        householdBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ANNOUNCE WORD
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.household,R.drawable.household,R.raw.household,R.raw.household_es);
            }
        });
    }

    public void nextActivity(Class dest, int TITLE_INT, String CARER_NAME, String LANGUAGE, String CAPTIONS, int audio, int audio_es ){
        String TITLE = context.getString(TITLE_INT);
        MediaPlayer mp;
        if(LANGUAGE.equals("SPANISH")){
            mp = MediaPlayer.create(context,audio_es);
        }else {
            mp = MediaPlayer.create(context, audio);
        }
        mp.start();
        android.os.SystemClock.sleep(1000);
        mp.reset();
        mp.stop();
        mp.release();
        Intent intent = new Intent(getApplicationContext(), dest);
        intent.putExtra("CARER_NAME",CARER_NAME);
        intent.putExtra("TITLE",TITLE);
        intent.putExtra("LANGUAGE",LANGUAGE);
        intent.putExtra("CAPTIONS",CAPTIONS);
        startActivity(intent);
    }

    public void imgActivity(String LANGUAGE,String CARER_NAME, String CAPTIONS,int TITLE_ADR, int pic,int audio, int audio_es){
        String TITLE = getResources().getString(TITLE_ADR);
        // ANNOUNCE WORD IN SELECTED LANGUAGE
        MediaPlayer mp;
        if(LANGUAGE.equals("SPANISH")){
            mp = MediaPlayer.create(context,audio_es);
        }else {
            mp = MediaPlayer.create(context, audio);
        }
        mp.start();
        android.os.SystemClock.sleep(1000);
        mp.reset();
        mp.stop();
        mp.release();

        // DISPLAY PHOTO
        Intent intent = new Intent(getApplicationContext(),DisplayImageActivity.class);
        intent.putExtra("IMAGE_ADR",pic);
        intent.putExtra("CARER_NAME",CARER_NAME);
        intent.putExtra("TITLE",TITLE);
        intent.putExtra("LANGUAGE",LANGUAGE);
        intent.putExtra("CAPTIONS",CAPTIONS);
        startActivity(intent);
    }
}
