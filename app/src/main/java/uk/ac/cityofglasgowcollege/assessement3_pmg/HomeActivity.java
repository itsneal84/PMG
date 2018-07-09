package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //method to start the trip activity
    public void onClickTrip(View v) {
        //create new intent for the trip activity
        Intent i = new Intent(this, TripActivity.class);
        //run the intent
        startActivity(i);
    }

    //method to start the events activity
    public void onClickWhatsOn(View v) {
        //create new intent for the event activity
        Intent i = new Intent(this, eventsActivity.class);
        //run the intent
        startActivity(i);
    }

    //method to start the visiting activity
    public void onClickVisiting(View v) {
        //create new intent for the visiting activity
        Intent i = new Intent(this, VisitingActivity.class);
        //run the intent
        startActivity(i);
    }

    //method to start the things to do activity
    public void onClickThingsToDo(View v) {
        //create new intent for the things to do activity
        Intent i = new Intent(this, ThingsToDoActivity.class);
        //run the intent
        startActivity(i);
    }
}
