package coogans2.dcu.ie.wordnu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class CarerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carer);


        // Retrieve carer name from previous activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int SEL_INDEX;
        String PATIENT_NAME = "blank";
        final String CARER_NAME = intent.getStringExtra("CARER_NAME");

        // SET TITLE AS CARER NAME
        final TextView cnameTV = (TextView) findViewById(R.id.cnameTV);
        cnameTV.setText(CARER_NAME);

        // Intialize database
        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // RETRIEVE LAYOUT
        final TextView pickavatarTV = (TextView) findViewById(R.id.pickavatarTV);
        final Button selectBTN = (Button) findViewById(R.id.selectBTN);
        final Button delBTN = (Button) findViewById(R.id.delBTN);
        final TextView addpatTV = (TextView) findViewById(R.id.addpatTV);
        final Spinner patientSelectSPIN = (Spinner) findViewById(R.id.spinner);
        final ImageView logoIV = (ImageView) findViewById(R.id.logoIV);
        final TextView settingsTV = (TextView) findViewById(R.id.settingsTV);

        // POPULATE SPINNER
        List<String> labels = db.getLabels(CARER_NAME);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patientSelectSPIN.setAdapter(adapter);


        if (extras.containsKey("SEL_INDEX")){
            SEL_INDEX = intent.getIntExtra("SEL_INDEX", 0);
            patientSelectSPIN.setSelection(SEL_INDEX);
            PATIENT_NAME = patientSelectSPIN.getSelectedItem().toString();
        }else{
            SEL_INDEX = -1;
        }


        // Configure avatar
        String avatar = "wordup";
        if(!PATIENT_NAME.equals("blank")) {
            avatar = db.getAvatar(PATIENT_NAME, CARER_NAME);
        }

        if (avatar.equals("man_beard")) {
            logoIV.setImageResource(R.drawable.av_man_beard);
        } else if (avatar.equals("man_black")) {
            logoIV.setImageResource(R.drawable.av_man_young);
        } else if (avatar.equals("man_builder")) {
            logoIV.setImageResource(R.drawable.av_man_builder);
        } else if (avatar.equals("woman_red")) {
            logoIV.setImageResource(R.drawable.av_woman_red);
        } else if (avatar.equals("woman_glasses")) {
            logoIV.setImageResource(R.drawable.av_woman_glasses);
        } else if (avatar.equals("woman_flower")) {
            logoIV.setImageResource(R.drawable.av_woman_flower);
        } else {
            logoIV.setImageResource(R.drawable.wup);
        }
        // CHOOSE AVATAR
        pickavatarTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PickAvatarActivity.class);
                intent.putExtra("CARER_NAME",CARER_NAME);
                intent.putExtra("SEL_INDEX",patientSelectSPIN.getSelectedItemPosition());
                intent.putExtra("PATIENT_NAME", patientSelectSPIN.getSelectedItem().toString());
                startActivity(intent);
            }
        });

        // Delete patient from database
        delBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.deletePat(patientSelectSPIN.getSelectedItem().toString()) > 0){
                    Toast.makeText(CarerActivity.this,"Patient removed",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CarerActivity.this, CarerActivity.class);
                    intent.putExtra("CARER_NAME", CARER_NAME);
                    CarerActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(CarerActivity.this,"Failed to delete",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Select patient
        selectBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> settings = db.getSettings(patientSelectSPIN.getSelectedItem().toString());
                String LANGUAGE = settings.get(0);
                String CAPTIONS = settings.get(1);
                System.out.println(LANGUAGE);
                System.out.println(CAPTIONS);
                if(LANGUAGE.equals("SPANISH") && !Locale.getDefault().toString().equals("es")){
                    changeLocale(CarerActivity.this,"es");
                }else if(LANGUAGE.equals("ENGLISH") && Locale.getDefault().toString().equals("es")){
                    changeLocale(CarerActivity.this,"en_UK");
                }

                Intent intent = new Intent(CarerActivity.this, FrontActivity.class);
                intent.putExtra("LANGUAGE", LANGUAGE);
                intent.putExtra("CAPTIONS", CAPTIONS);
                intent.putExtra("CARER_NAME",CARER_NAME);
                CarerActivity.this.startActivity(intent);
            }
        });
        // edit patient settings
        settingsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarerActivity.this, UpdatePatientActivity.class);
                intent.putExtra("CARER_NAME",CARER_NAME);
                intent.putExtra("SEL_INDEX",patientSelectSPIN.getSelectedItemPosition());
                intent.putExtra("PATIENT_NAME",patientSelectSPIN.getSelectedItem().toString());
                CarerActivity.this.startActivity(intent);
            }
        });
        // add new patient
        addpatTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CarerActivity.this, AddPatientActivity.class);
                intent.putExtra("CARER_NAME", CARER_NAME);
                CarerActivity.this.startActivity(intent);
            }
        });
        // select from existing patients
        patientSelectSPIN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                settingsTV.setVisibility(View.VISIBLE);
                selectBTN.setVisibility(View.VISIBLE);
                delBTN.setVisibility(View.VISIBLE);
                pickavatarTV.setVisibility(View.VISIBLE);
                // SET NEW AVATAR
                String avatar = db.getAvatar(patientSelectSPIN.getSelectedItem().toString(),CARER_NAME);
                if (avatar.equals("man_beard")) {
                    logoIV.setImageResource(R.drawable.av_man_beard);
                }else if (avatar.equals("man_black")){
                    logoIV.setImageResource(R.drawable.av_man_young);
                }else if(avatar.equals("man_builder")){
                    logoIV.setImageResource(R.drawable.av_man_builder);
                }else if(avatar.equals("woman_red")){
                    logoIV.setImageResource(R.drawable.av_woman_red);
                }else if(avatar.equals("woman_glasses")){
                    logoIV.setImageResource(R.drawable.av_woman_glasses);
                }else if(avatar.equals("woman_flower")){
                    logoIV.setImageResource(R.drawable.av_woman_flower);
                }else{
                    logoIV.setImageResource(R.drawable.wup);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                settingsTV.setVisibility(View.INVISIBLE);
                selectBTN.setVisibility(View.INVISIBLE);
                delBTN.setVisibility(View.INVISIBLE);
                pickavatarTV.setVisibility(View.VISIBLE);
                logoIV.setImageResource(R.drawable.wup);
            }
        });

    }
    // change language
    public void changeLocale(Context context, String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

}
