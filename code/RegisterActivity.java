package coogans2.dcu.ie.wordnu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // access database
        myDb = new DatabaseHelper(this);
        // link layout with code
        final EditText setuserET = (EditText) findViewById(R.id.setuserET);
        final EditText setpassET = (EditText) findViewById(R.id.setpassET);
        final EditText confirmpassET = (EditText) findViewById(R.id.confirmpassET);
        final Button regBTN = (Button) findViewById(R.id.regBTN);
        // Register, check if passwords match and if injection succeeds
        regBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isIn = false;
                if(!setpassET.getText().toString().equals(confirmpassET.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    isIn = myDb.insertCarer(setuserET.getText().toString(),setpassET.getText().toString());
                }

                if (isIn) {
                    Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
