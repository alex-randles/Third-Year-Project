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

public class AddPatientActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    String CARER_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        // Access database
        myDb = new DatabaseHelper(this);
        // retrieve carer name from previous activity
        final String CARER_NAME = getIntent().getStringExtra("CARER_NAME");
        // Link layout with code
        final TextView patNameET = (TextView) findViewById(R.id.patNameET);
        final Spinner languageSPIN = (Spinner) findViewById(R.id.languageSPIN);
        final Spinner captionSPIN = (Spinner) findViewById(R.id.captionSPIN);
        final Button addPatBTN = (Button) findViewById(R.id.addPatBTN);

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
        // Give add patient button functionality
        addPatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isIn = myDb.insertPatient(patNameET.getText().toString(),
                        languageSPIN.getSelectedItem().toString(),captionSPIN.getSelectedItem().toString(), CARER_NAME);

                if(isIn){
                    Toast.makeText(AddPatientActivity.this,"Patient added",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddPatientActivity.this, CarerActivity.class);
                    intent.putExtra("CARER_NAME", CARER_NAME);
                    AddPatientActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(AddPatientActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
