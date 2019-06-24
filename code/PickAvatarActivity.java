package coogans2.dcu.ie.wordnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PickAvatarActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_avatar);
        // Access database
        myDb = new DatabaseHelper(this);
        // Retrieve details from previous activity
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");
        final String PATIENT_NAME = getIntent().getStringExtra("PATIENT_NAME");
        final int SEL_INDEX = getIntent().getIntExtra("SEL_INDEX",0);
        // Link layout with code
        Button av_man_beardBTN = (Button) findViewById(R.id.av_man_beardBTN);
        Button av_man_blackBTN = (Button) findViewById(R.id.av_man_blackBTN);
        Button av_man_builderBTN = (Button) findViewById(R.id.av_man_builderBTN);
        Button av_woman_redBTN = (Button) findViewById(R.id.av_woman_redBTN);
        Button av_woman_glassesBTN = (Button) findViewById(R.id.av_woman_glassBTN);
        Button av_woman_flowerBTN = (Button) findViewById(R.id.av_woman_flowerBTN);


        // ADD AVATAR CHOICE TO DATABASE
        av_man_beardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"man_beard");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });
        av_man_blackBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"man_black");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });
        av_man_builderBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"man_builder");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });
        av_woman_redBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"woman_red");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });
        av_woman_glassesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"woman_glasses");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });
        av_woman_flowerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.updateAvatar(PATIENT_NAME,CARER_NAME,"woman_flower");
                returnToSender(CARER_NAME,SEL_INDEX);
            }
        });

    }
    // GO BACK TO CARER PAGE
    public void returnToSender(String CARER_NAME,int SEL_INDEX){
        Intent intent = new Intent(getApplicationContext(),CarerActivity.class);
        intent.putExtra("SEL_INDEX",SEL_INDEX);
        intent.putExtra("CARER_NAME",CARER_NAME);
        startActivity(intent);
    }

}
