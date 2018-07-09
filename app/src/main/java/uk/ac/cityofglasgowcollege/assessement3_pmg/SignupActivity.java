package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private MyDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    //method fires when register button is clicked
    public void onSignupClick (View v){

        //find the editTexts
        EditText firstName = (EditText) findViewById(R.id.firstname_txt);
        EditText lastName = (EditText) findViewById(R.id.lastname_Txt);
        EditText email = (EditText) findViewById(R.id.email_txt);
        EditText userName = (EditText) findViewById(R.id.username_txt);
        EditText password = (EditText) findViewById(R.id.password_txt);
        EditText reEnterPassword = (EditText) findViewById(R.id.reenterpassword_txt);

        //get the text & save as variables
        String firstNameReg = firstName.getText().toString();
        String lastNameReg = lastName.getText().toString();
        String emailReg = email.getText().toString();
        String userNameReg = userName.getText().toString();
        String passwordReg = password.getText().toString();
        String reEnterPasswordReg = reEnterPassword.getText().toString();

        //check if both passwords match
        if(passwordReg.equals(reEnterPasswordReg)){
            //call the users class setter methods & pass in entered data
            Users u = new Users();
            u.setFirstname(firstNameReg);
            u.setEmail(emailReg);
            u.setLastname(lastNameReg);
            u.setPassword(passwordReg);
            u.setUsername(userNameReg);

            myDatabase = new MyDB(this);

            myDatabase.insertUser(u);

            Intent myIntent = new Intent(this,MainActivity.class);
            startActivity(myIntent);

            Toast toast1 = Toast.makeText(this, "Thank you for registering", Toast.LENGTH_SHORT);
            toast1.show();
        }else{
            Toast toast2 = Toast.makeText(this, "Your passwords don't match please enter again", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }
}
