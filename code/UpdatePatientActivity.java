package coogans2.dcu.ie.wordnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePatientActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    String CARER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        // Access database
        myDb = new DatabaseHelper(this);
        // Retrive details from previous activity
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");
        final String PATIENT_NAME = getIntent().getStringExtra("PATIENT_NAME");
        final int SEL_INDEX = getIntent().getIntExtra("SEL_INDEX",0);
        // Link layout with code
        final TextView carerNameTV = (TextView) findViewById(R.id.carerNameTV);
        final TextView patientNameTV = (TextView) findViewById(R.id.patientNameTV);
        final Spinner languageSPIN = (Spinner) findViewById(R.id.languageSPIN);
        final Spinner captionSPIN = (Spinner) findViewById(R.id.captionSPIN);
        final Button updatePatBTN = (Button) findViewById(R.id.updatePatBTN);
        //Set care and patient name
        carerNameTV.setText(CARER_NAME);
        patientNameTV.setText(PATIENT_NAME);
        // Populate language spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSPIN.setAdapter(adapter);
        // Populate captions spinner
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.captions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        captionSPIN.setAdapter(adapter2);
        // Set spinners to null
        languageSPIN.setSelection(-1);
        captionSPIN.setSelection(-1);


        // Update patient details, checks to see both fields are selected
        updatePatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String langSelection = languageSPIN.getSelectedItem().toString();
                String capSelection = captionSPIN.getSelectedItem().toString();
                if(langSelection.equals(null) || capSelection.equals(null)){
                    Toast.makeText(UpdatePatientActivity.this,"Please select language & captions",Toast.LENGTH_LONG).show();
                    return;
                }
                boolean isIn = myDb.updatePatient(PATIENT_NAME,langSelection,
                        capSelection,CARER_NAME);

                if(isIn){
                    Toast.makeText(UpdatePatientActivity.this,"Details Updated",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdatePatientActivity.this, CarerActivity.class);
                    intent.putExtra("SEL_INDEX",SEL_INDEX);
                    intent.putExtra("CARER_NAME", CARER_NAME);
                    UpdatePatientActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(UpdatePatientActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
