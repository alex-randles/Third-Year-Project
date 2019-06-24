package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodFastfoodActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_fastfood);


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

        Button food_fastfood_chipsBTN = (Button)findViewById(R.id.food_fastfood_chipsBTN);
        Button food_fastfood_pizzaBTN = (Button)findViewById(R.id.food_fastfood_pizzaBTN);
        Button food_fastfood_burgerBTN = (Button)findViewById(R.id.food_fastfood_burgerBTN);
        Button food_fastfood_curryBTN = (Button)findViewById(R.id.food_fastfood_curryBTN);
        Button food_fastfood_friedchickenBTN = (Button)findViewById(R.id.food_fastfood_friedchickenBTN);
        Button food_fastfood_chineseBTN = (Button)findViewById(R.id.food_fastfood_chineseBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            food_fastfood_chipsBTN.setText("");
            food_fastfood_pizzaBTN.setText("");
            food_fastfood_burgerBTN.setText("");
            food_fastfood_curryBTN.setText("");
            food_fastfood_friedchickenBTN.setText("");
            food_fastfood_chineseBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_fastfood_chipsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_chips,R.drawable.food_fastfood_chips,R.raw.food_fastfood_chips,R.raw.food_fastfood_chips_es);
            }
        });
        food_fastfood_pizzaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_pizza,R.drawable.food_fastfood_pizza,R.raw.food_fastfood_pizza,R.raw.food_fastfood_pizza_es);
            }
        });
        food_fastfood_burgerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_burger,R.drawable.food_fastfood_burger,R.raw.food_fastfood_burger,R.raw.food_fastfood_burger_es);
            }
        });
        food_fastfood_curryBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_curry,R.drawable.food_fastfood_curry,R.raw.food_fastfood_curry,R.raw.food_fastfood_curry_es);
            }
        });
        food_fastfood_friedchickenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_friedchicken,R.drawable.food_fastfood_friedchicken,R.raw.food_fastfood_friedchicken,R.raw.food_fastfood_friedchicken_es);
            }
        });
        food_fastfood_chineseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_fastfood_chinese,R.drawable.food_fastfood_chinese,R.raw.food_fastfood_chinese,R.raw.food_fastfood_chinese_es);
            }
        });
    } // onCreate

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
