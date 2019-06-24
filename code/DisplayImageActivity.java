package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayImageActivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        final String TITLE = getIntent().getStringExtra("TITLE");
        // RETRIVE PATIENT SETTINGS
        final String LANGUAGE = getIntent().getStringExtra("LANGUAGE");
        final String CAPTIONS = getIntent().getStringExtra("CAPTIONS");
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");
        final int IMAGE_ADR = getIntent().getIntExtra("IMAGE_ADR",0);

        // INITIALIZE BUTTON FROM LAYOUT
        Button homeBTN = (Button)findViewById(R.id.homeBTN);
        Button logoutBTN = (Button)findViewById(R.id.logoutBTN);
        ImageView displayIV = (ImageView) findViewById(R.id.displayIV);
        Button returnBTN = (Button)findViewById(R.id.returnBTN);

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

        // SET WORD DISPLAY
        TextView wordTV = (TextView)findViewById(R.id.wordTV);
        wordTV.setText(TITLE);
        // Display image
        displayIV.setImageResource(IMAGE_ADR);

        // RETURN BUTTON FUNC
        returnBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gohome = new Intent(getApplicationContext(),FrontActivity.class);
                gohome.putExtra("CARER_NAME",CARER_NAME);
                gohome.putExtra("TITLE",TITLE);
                gohome.putExtra("LANGUAGE",LANGUAGE);
                gohome.putExtra("CAPTIONS",CAPTIONS);
                startActivity(gohome);
                overridePendingTransition(R.anim.abc_fade_in,R.anim.abc_fade_out);
            }
        });
    }
}
