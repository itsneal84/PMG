package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        //get the username entered during login
        Intent i = getIntent();
        String username = i.getStringExtra("username");

        //find & set in the username_txt
        EditText username_txt = (EditText) findViewById(R.id.username_txt);
        username_txt.setText(username);
    }

    public void onClickPost(View v){

        //use try catch to avoid errors
        try{
            //find the EditTexts
            EditText username = (EditText) findViewById(R.id.username_txt);
            EditText productId = (EditText) findViewById(R.id.productid_txt);
            EditText comment = (EditText) findViewById(R.id.comment_txt);
            EditText rating = (EditText) findViewById(R.id.rating_txt);

            //get the text & save it as variables
            String usernameCom = username.getText().toString();
            String productIdCom = productId.getText().toString();
            String commentCom = comment.getText().toString();
            int ratingCom = Integer.parseInt(rating.getText().toString());

            //create instance of comments class to populate values
            Comments c = new Comments();
            c.setUsername(usernameCom);
            c.setProductId(productIdCom);
            c.setComments(commentCom);
            c.setReviewRating(ratingCom);

            //create instance of database
            DBHandler handler = new DBHandler(this);

            //call the getComments method
            handler.addReview(c);

            //display message to confirm review added
            Toast toast1 = Toast.makeText(this, "Comment added", Toast.LENGTH_SHORT);
            toast1.show();

            //create intent for trip activity
            Intent i = new Intent(this,TripActivity.class);

            //add the username as extra
            i.putExtra("username", usernameCom);

            startActivity(i);

        }catch (Exception ex){
            //display error message
            Toast toast2 = Toast.makeText(this, "error posting message", Toast.LENGTH_SHORT);
        }

    }
}
