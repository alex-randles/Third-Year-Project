package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodFruitActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_fruit);

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
        Button food_fruit_appleBTN = (Button)findViewById(R.id.food_fruit_appleBTN);
        Button food_fruit_bananaBTN = (Button)findViewById(R.id.food_fruit_bananaBTN);
        Button food_fruit_pearBTN = (Button)findViewById(R.id.food_fruit_pearBTN);
        Button food_fruit_grapesBTN = (Button)findViewById(R.id.food_fruit_grapesBTN);
        Button food_fruit_strawberryBTN = (Button)findViewById(R.id.food_fruit_strawberryBTN);
        Button food_fruit_pineappleBTN = (Button)findViewById(R.id.food_fruit_pineappleBTN);

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
            food_fruit_appleBTN.setText("");
            food_fruit_bananaBTN.setText("");
            food_fruit_pearBTN.setText("");
            food_fruit_grapesBTN.setText("");
            food_fruit_strawberryBTN.setText("");
            food_fruit_pineappleBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_fruit_appleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_apple,R.drawable.food_fruit_apple,R.raw.food_fruit_apple,R.raw.food_fruit_apple_es);
            }
        });
        food_fruit_bananaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_banana,R.drawable.food_fruit_banana,R.raw.food_fruit_banana,R.raw.food_fruit_banana_es);
            }
        });
        food_fruit_pearBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_pear,R.drawable.food_fruit_pear,R.raw.food_fruit_pear,R.raw.food_fruit_pear_es);
            }
        });
        food_fruit_grapesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_grapes,R.drawable.food_fruit_grapes,R.raw.food_fruit_grapes,R.raw.food_fruit_grapes_es);
            }
        });
        food_fruit_pineappleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_pineapple,R.drawable.food_fruit_pineapple,R.raw.food_fruit_pineapple,R.raw.food_fruit_pineapple_es);
            }
        });
        food_fruit_strawberryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fruit_strawberry,R.drawable.food_fruit_strawberry,R.raw.food_fruit_strawberry,R.raw.food_fruit_strawberry_es);
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
