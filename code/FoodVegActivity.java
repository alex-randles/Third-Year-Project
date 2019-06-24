package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodVegActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_veg);

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
        Button food_veg_carrotBTN = (Button)findViewById(R.id.food_veg_carrotBTN);
        Button food_veg_potatoBTN= (Button)findViewById(R.id.food_veg_potatoBTN);
        Button food_veg_tomatoBTN = (Button)findViewById(R.id.food_veg_tomatoBTN);
        Button food_veg_peasBTN = (Button)findViewById(R.id.food_veg_peasBTN);
        Button food_veg_onionBTN = (Button)findViewById(R.id.food_veg_onionBTN);
        Button food_veg_lettuceBTN = (Button)findViewById(R.id.food_veg_lettuceBTN);

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
            food_veg_carrotBTN.setText("");
            food_veg_potatoBTN.setText("");
            food_veg_tomatoBTN.setText("");
            food_veg_peasBTN.setText("");
            food_veg_lettuceBTN.setText("");
            food_veg_onionBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_veg_carrotBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_carrot,R.drawable.food_veg_carrot,R.raw.food_veg_carrot,R.raw.food_veg_carrot_es);
            }
        });
        food_veg_potatoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_potato,R.drawable.food_veg_potato,R.raw.food_veg_potato,R.raw.food_veg_potato_es);
            }
        });
        food_veg_tomatoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_tomato,R.drawable.food_veg_tomato,R.raw.food_veg_tomato,R.raw.food_veg_tomato_es);
            }
        });
        food_veg_peasBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_peas,R.drawable.food_veg_peas,R.raw.food_veg_peas,R.raw.food_veg_peas_es);
            }
        });
        food_veg_lettuceBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_lettuce,R.drawable.food_veg_lettuce,R.raw.food_veg_lettuce,R.raw.food_veg_lettuce_es);
            }
        });
        food_veg_onionBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_veg_onion,R.drawable.food_veg_onion,R.raw.food_veg_onion,R.raw.food_veg_onion_es);
            }
        });


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
