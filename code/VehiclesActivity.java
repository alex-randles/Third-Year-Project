package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VehiclesActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles);
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
        Button vehicles_carBTN = (Button)findViewById(R.id.vehicles_carBTN);
        Button vehicles_shipBTN = (Button)findViewById(R.id.vehicles_shipBTN);
        Button vehicles_taxiBTN = (Button)findViewById(R.id.vehicles_taxiBTN);
        Button vehicles_bikeBTN = (Button)findViewById(R.id.vehicles_bikeBTN);
        Button vehicles_motorbikeBTN = (Button)findViewById(R.id.vehicles_motorbikeBTN);
        Button vehicles_planeBTN = (Button)findViewById(R.id.vehicles_planeBTN);

        // TURN TEXT OFF IF SELECTED
        if (CAPTIONS.equals("OFF")){
            vehicles_carBTN.setText("");
            vehicles_shipBTN.setText("");
            vehicles_taxiBTN.setText("");
            vehicles_bikeBTN.setText("");
            vehicles_motorbikeBTN.setText("");
            vehicles_planeBTN.setText("");
        }

        // BUTTON ACTION
        vehicles_carBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_car,R.drawable.vehicles_car,R.raw.vehicles_car,R.raw.vehicles_car_es);
            }
        });
        vehicles_shipBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_ship,R.drawable.vehicles_ship,R.raw.vehicles_ship,R.raw.vehicles_ship_es);
            }
        });
        vehicles_taxiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_taxi,R.drawable.vehicles_taxi,R.raw.vehicles_taxi,R.raw.vehicles_taxi_es);
            }
        });
        vehicles_bikeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_bike,R.drawable.vehicles_bike,R.raw.vehicles_bike,R.raw.vehicles_bike_es);
            }
        });
        vehicles_motorbikeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_motorbike,R.drawable.vehicles_motorbike,R.raw.vehicles_motorbike,R.raw.vehicles_motorbike_es);
            }
        });
        vehicles_planeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgActivity(LANGUAGE,CARER_NAME,CAPTIONS,R.string.vehicles_plane,R.drawable.vehicles_plane,R.raw.vehicles_plane,R.raw.vehicles_plane_es);
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
