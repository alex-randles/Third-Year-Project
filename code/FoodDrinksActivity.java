package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodDrinksActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_drinks);

        // RETRIEVE PAGE TITLE
        final String TITLE = getIntent().getStringExtra("TITLE");
        // RETRIVE PATIENT SETTINGS
        final String LANGUAGE = getIntent().getStringExtra("LANGUAGE");
        final String CAPTIONS = getIntent().getStringExtra("CAPTIONS");
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");

        // SET PAGE TITLE
        TextView titleTV = (TextView)findViewById(R.id.titleTV);
        titleTV.setText(TITLE);

        // INITIALIZE BUTTON FROM LAYOUT
        Button homeBTN = (Button)findViewById(R.id.homeBTN);
        Button logoutBTN = (Button)findViewById(R.id.logoutBTN);

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

        Button food_drinks_waterBTN = (Button)findViewById(R.id.food_drinks_waterBTN);
        Button food_drinks_juiceBTN = (Button)findViewById(R.id.food_drinks_juiceBTN);
        Button food_drinks_teaBTN = (Button)findViewById(R.id.food_drinks_teaBTN);
        Button food_drinks_sodaBTN = (Button)findViewById(R.id.food_drinks_sodaBTN);
        Button food_drinks_beerBTN = (Button)findViewById(R.id.food_drinks_beerBTN);
        Button food_drinks_wineBTN = (Button)findViewById(R.id.food_drinks_wineBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            food_drinks_waterBTN.setText("");
            food_drinks_juiceBTN.setText("");
            food_drinks_teaBTN.setText("");
            food_drinks_sodaBTN.setText("");
            food_drinks_beerBTN.setText("");
            food_drinks_wineBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_drinks_waterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_water,R.drawable.food_drinks_water,R.raw.food_drinks_water,R.raw.food_drinks_water_es);
            }
        });
        food_drinks_juiceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_juice,R.drawable.food_drinks_juice,R.raw.food_drinks_juice,R.raw.food_drinks_juice_es);
            }
        });
        food_drinks_teaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_tea,R.drawable.food_drinks_tea,R.raw.food_drinks_tea,R.raw.food_drinks_tea_es);
            }
        });
        food_drinks_sodaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_soda,R.drawable.food_drinks_soda,R.raw.food_drinks_soda,R.raw.food_drinks_soda_es);
            }
        });
        food_drinks_beerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_beer,R.drawable.food_drinks_beer,R.raw.food_drinks_beer,R.raw.food_drinks_beer_es);
            }
        });
        food_drinks_wineBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks_wine,R.drawable.food_drinks_wine,R.raw.food_drinks_wine,R.raw.food_drinks_wine_es);
            }
        });

    } // onCreate

    public void imgActivity(String LANGUAGE,String CARER_NAME, String CAPTIONS, int TITLE_ADR, int pic, int audio, int audio_es){
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
