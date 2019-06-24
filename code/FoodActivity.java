package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

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

        // HOME BUTTON
        homeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(),CarerActivity.class);
                home.putExtra("CARER_NAME",CARER_NAME);
                startActivity(home);
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

        // INTIALIZE WORD BUTTONS
        Button food_fruitBTN = (Button)findViewById(R.id.food_fruitBTN);
        Button food_vegBTN = (Button)findViewById(R.id.food_vegBTN);
        Button food_drinksBTN = (Button)findViewById(R.id.food_drinksBTN);
        Button food_dessertBTN = (Button)findViewById(R.id.food_dessertBTN);
        Button food_breakfastBTN = (Button)findViewById(R.id.food_breakfastBTN);
        Button food_fastfoodBTN = (Button)findViewById(R.id.food_fastfoodBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            food_vegBTN.setText("");
            food_drinksBTN.setText("");
            food_dessertBTN.setText("");
            food_breakfastBTN.setText("");
            food_fruitBTN.setText("");
            food_fastfoodBTN.setText("");
        }

        // BUTTON ACTION
        food_vegBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodVegActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_veg,R.raw.food_veg,R.raw.food_veg_es);
            }
        });
        food_fruitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodFruitActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_fruit,R.raw.food_fruit,R.raw.food_fruit_es);
            }
        });
        food_dessertBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodDessertActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_dessert,R.raw.food_dessert,R.raw.food_dessert_es);
            }
        });
        food_drinksBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodDrinksActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_drinks,R.raw.food_drinks,R.raw.food_drinks_es);
            }
        });
        food_breakfastBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodBreakfastActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_breakfast,R.raw.food_breakfast,R.raw.food_breakfast_es);
            }
        });
        food_fastfoodBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newActivity(FoodFastfoodActivity.class,CARER_NAME,LANGUAGE,CAPTIONS,R.string.food_fastfood,R.raw.food_fastfood,R.raw.food_fastfood_es);
            }
        });

    }

    public void newActivity(Class dest,String CARER_NAME, String LANGUAGE, String CAPTIONS, int title_int,int audio, int audio_es){
        // Convert title from  int address to String
        String title = context.getString(title_int);

        // ANNOUNCE WORD IN SELECTED LANGUAGE
        MediaPlayer mp;
        if(LANGUAGE.equals("SPANISH")){
            mp = MediaPlayer.create(context,audio_es);
        }else {
            mp = MediaPlayer.create(context, audio);
        }
        try {
            if (mp.isPlaying()) {
                mp.reset();
                mp.prepare();
                mp.stop();
                mp.release();
                mp=null;
                if(LANGUAGE.equals("SPANISH")){
                    mp = MediaPlayer.create(context,R.raw.food_veg);
                }else {
                    mp = MediaPlayer.create(context, R.raw.food_veg);
                }
            } mp.start();
        } catch(Exception e) { e.printStackTrace(); }

        // SWITCH TO NEW ACTIVITY
        Intent intent = new Intent(getApplicationContext(), dest);
        intent.putExtra("CARER_NAME",CARER_NAME);
        intent.putExtra("TITLE",title);
        intent.putExtra("LANGUAGE",LANGUAGE);
        intent.putExtra("CAPTIONS",CAPTIONS);
        startActivity(intent);
    }

}

