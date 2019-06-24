package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodBreakfastActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_breakfast);

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

        Button food_breakfast_toastBTN = (Button)findViewById(R.id.food_breakfast_toastBTN);
        Button food_breakfast_eggsBTN = (Button)findViewById(R.id.food_breakfast_eggsBTN);
        Button food_breakfast_crossaintBTN = (Button)findViewById(R.id.food_breakfast_crossaintBTN);
        Button food_breakfast_beansBTN = (Button)findViewById(R.id.food_breakfast_beansBTN);
        Button food_breakfast_baconBTN = (Button)findViewById(R.id.food_breakfast_baconBTN);
        Button food_breakfast_cerealBTN = (Button)findViewById(R.id.food_breakfast_cerealBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            food_breakfast_toastBTN.setText("");
            food_breakfast_eggsBTN.setText("");
            food_breakfast_crossaintBTN.setText("");
            food_breakfast_beansBTN.setText("");
            food_breakfast_baconBTN.setText("");
            food_breakfast_cerealBTN.setText("");
        }

        // ANNOUNCE ASSOCIATE WORD ON CLICK
        food_breakfast_toastBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_toast,R.drawable.food_breakfast_toast,R.raw.food_breakfast_toast,R.raw.food_breakfast_toast_es);
            }
        });
        food_breakfast_eggsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_eggs,R.drawable.food_breakfast_eggs,R.raw.food_breakfast_eggs,R.raw.food_breakfast_eggs_es);
            }
        });
        food_breakfast_crossaintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_crossaint,R.drawable.food_breakfast_crossaint,R.raw.food_breakfast_crossaint,R.raw.food_breakfast_crossaint_es);
            }
        });
        food_breakfast_beansBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_beans,R.drawable.food_breakfast_beans,R.raw.food_breakfast_beans,R.raw.food_breakfast_beans_es);
            }
        });
        food_breakfast_baconBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_bacon,R.drawable.food_breakfast_bacon,R.raw.food_breakfast_bacon,R.raw.food_breakfast_bacon_es);
            }
        });
        food_breakfast_cerealBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.food_breakfast_cereal,R.drawable.food_breakfast_cereal,R.raw.food_breakfast_cereal,R.raw.food_breakfast_cereal_es);
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
