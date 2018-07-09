package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickLogin(View v){
        EditText username = (EditText) findViewById(R.id.username_txt);
        EditText password = (EditText) findViewById(R.id.password_txt);

        String usernameLog = username.getText().toString();
        String passwordLog = password.getText().toString();

        myDatabase = new MyDB(this);

        if (myDatabase.checkUser(usernameLog, passwordLog)){
            Toast toast1 = Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT);
            toast1.show();

            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }else{
            Toast toast2 = Toast.makeText(this, "Error please register", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    public void onClickSignup(View v){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
