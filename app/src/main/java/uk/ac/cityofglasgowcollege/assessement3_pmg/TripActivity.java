package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TripActivity extends AppCompatActivity {

    //global variable for username
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        //get last opened intent
        Intent i = getIntent();

        //get the stored extra username
        username = i.getStringExtra("username");

        //call the displayComments method to show all comments in database
        displayComments();
    }

    public void displayComments(){
        //find the list view to store the comments
        ListView comments_lst = (ListView)findViewById(R.id.comments_lst);

        //create instance of database
        DBHandler handler = new DBHandler(this);

        //call the reviews for the Must See & save in arraylist
        ArrayList<Comments> allComments = handler.getReviews("Must See");

        ArrayList<String> commentsStrings = new ArrayList<>();

        //loop through all reviews & build string for each
        for (Comments c : allComments){
            commentsStrings.add("Reviewer:\t" + c.getUsername() + "\nRating:\t\t\t\t" + c.getReviewRating() + "\nComment:\t\t\t\t" + c.getComments());
        }

        //declare array adapter to add all string to listview
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commentsStrings);

        //add the adapter to the listview
        comments_lst.setAdapter(listAdapter);

        //add a header for the listview
        TextView textView = new TextView(this);
        textView.setText("User Comments");

        comments_lst.addHeaderView(textView);
    }

    public void onAddCommentClick(View v){
        Intent i = new Intent(this,AddCommentActivity.class);
        startActivity(i);
    }

}
