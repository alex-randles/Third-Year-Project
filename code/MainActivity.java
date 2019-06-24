package coogans2.dcu.ie.wordnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load database
        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        // Retrive buttons from layout
        final EditText loginET = (EditText) findViewById(R.id.loginET);
        final EditText passwordET = (EditText) findViewById(R.id.passwordET);
        final Button loginBTN = (Button) findViewById(R.id.loginBTN);
        final TextView regTV = (TextView) findViewById(R.id.regTV);

        // Set login button functionality
        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginET.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please enter username",Toast.LENGTH_LONG).show();
                    return;
                }
                if(passwordET.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please enter password",Toast.LENGTH_LONG).show();
                    return;
                }
                if(db.login(loginET.getText().toString(),passwordET.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, CarerActivity.class);
                    intent.putExtra("CARER_NAME", loginET.getText().toString());
                    MainActivity.this.startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        // Register button functionality
        regTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(regIntent);
            }
        });

    }
}
