package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThingsToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);
    }

    public void onMoreClick(View v){
        //set string for web url
        String url = "https://peoplemakeglasgow.com/visiting/top-reasons-to-visit-glasgow";

        //create new intent to take user to external url
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));

        //start url activity
        startActivity(i);
    }
}
