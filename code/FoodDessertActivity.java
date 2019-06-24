package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodDessertActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_dessert);

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

        Button food_dessert_cakeBTN = (Button)findViewById(R.id.food_dessert_cakeBTN);
        Button food_dessert_chocolateBTN = (Button)findViewById(R.id.food_dessert_chocolateBTN);
        Button food_dessert_tartBTN = (Button)findViewById(R.id.food_dessert_tartBTN);
        Button food_dessert_icecreamBTN = (Button)findViewById(R.id.food_dessert_icecreamBTN);
        Button food_dessert_cupcakeBTN = (Button)findViewById(R.id.food_dessert_cupcakeBTN);
        Button food_dessert_donutBTN = (Button)findViewById(R.id.food_dessert_donutBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            food_dessert_cakeBTN.setText("");
            food_dessert_chocolateBTN.setText("");
            food_dessert_tartBTN.setText("");
            food_dessert_icecreamBTN.setText("");
            food_dessert_cupcakeBTN.setText("");
            food_dessert_donutBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_dessert_cakeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_cake,R.drawable.food_dessert_cake,R.raw.food_dessert_cake,R.raw.food_dessert_cake_es);
            }
        });
        food_dessert_chocolateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_chocolate,R.drawable.food_dessert_chocolate,R.raw.food_dessert_chocolate,R.raw.food_dessert_chocolate_es);
            }
        });
        food_dessert_tartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_appletart,R.drawable.food_dessert_appletart,R.raw.food_dessert_tart,R.raw.food_dessert_tart_es);
            }
        });
        food_dessert_icecreamBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_icecream,R.drawable.food_dessert_icecream,R.raw.food_dessert_icecream,R.raw.food_dessert_icecream_es);
            }
        });
        food_dessert_cupcakeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_cupcake,R.drawable.food_dessert_cupcake,R.raw.food_dessert_cupcake,R.raw.food_dessert_cake_es);
            }
        });
        food_dessert_donutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_dessert_donut,R.drawable.food_dessert_donut,R.raw.food_dessert_donut,R.raw.food_dessert_donut_es);
            }
        });


    }

    // DISPLAY IMAGE ON ITS OWN
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
