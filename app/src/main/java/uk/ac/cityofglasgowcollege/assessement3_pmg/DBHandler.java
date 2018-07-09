package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by nealnisbet on 04/03/2018.
 */

public class DBHandler extends SQLiteOpenHelper
{
    //FINAL variables to hold the names of the two TABLES
    public static final String TABLE_USERS = "users";
    public static final String TABLE_REVIEWS = "reviews";

    //FINAL variables to hold the COLUMNS for the USERS table
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AGE = "age";

    //FINAL variables to hold the COLUMNS for the REVIEWS table (reuse "username" from above)
    public static final String COLUMN_PRODUCT_ID = "productId";
    public static final String COLUMN_COMMENTS = "Comments";
    public static final String COLUMN_REVIEWRATING = "reviewRating";


    //constructor for the DBHandler class - takes in a parameter defining the context
    public DBHandler(Context context)
    {
        //UsersDB is the name of the database which will be created
        //null is to say use the standard SQL Cursor behaviours   //1 is the version number of the DB
        super(context, "UsersDB", null, 1);
    }


    //Create tables for database
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //build a string which contains the necessary SQL to create the USERS table
        final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_USERNAME + " TEXT," + COLUMN_PASSWORD + " TEXT," +
                COLUMN_EMAIL + " TEXT," + COLUMN_AGE + " INT" +
                ")";

        //execute the sql by calling the execSQL method
        db.execSQL(CREATE_USERS_TABLE);


        //build a string which contains the necessary SQL to create the REVIEWS table
        final String CREATE_COMMENTS_TABLE = "CREATE TABLE " + TABLE_REVIEWS +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCT_ID + " TEXT," + COLUMN_USERNAME + " TEXT," +
                COLUMN_COMMENTS + " TEXT," + COLUMN_REVIEWRATING + " INT" +
                ")";

        //execute the sql by calling the execSQL method
        db.execSQL(CREATE_COMMENTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //we need to have this method as we inherit from SQLiteOpenHelper
        //but don't need to code it as we won't use it
    }


    //add user to users table - takes in a parameter of User
    public void addUser(Users users)
    {
        //content values class allows us to store all the data we wish to insert for the new user
        ContentValues values = new ContentValues();

        //call the put method to add the data we wish for a certain column
        values.put(COLUMN_USERNAME, users.getUsername().toUpperCase());
        values.put(COLUMN_PASSWORD, users.getPassword());
        values.put(COLUMN_EMAIL, users.getEmail());

        //get a connection to the db we setup
        SQLiteDatabase db = this.getWritableDatabase();

        //call the insert method to add all the data in the ContentValues object to a new row in the db
        db.insert(TABLE_USERS, null, values);

        //close the db
        db.close();
    }

    //Check if new username is already in table - takes in a parameter of username
    public boolean usernameTaken(String username)
    {
        //build a string which contains the necessary SQL to check if the username exists
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " =  '" + username.toUpperCase() + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getWritableDatabase();

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //declare a boolean to hold whether the username is found or not
        boolean taken = false;

        //Carry out SQL query on username from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if(cursor.getCount() > 0)    //if there has been a result
        {
            taken = true;
            //close the cursor
            cursor.close();
        }
        else
        {
            taken = false;
        }

        //close the db connection
        db.close();

        //return the boolean value
        return taken;
    }

    //CheckLogin method confirms if username is in database and if so checks if stored password matches input
    //both checks must pass to return true - the user logged in correctly
    public boolean checkLogin(String username, String password)
    {
        //build a string which contains the necessary SQL to check if the username and password combo ecist
        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USERNAME + " = '" + username +
                "' AND " + COLUMN_PASSWORD + " = '" +  password + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getWritableDatabase();

        //db.execSQL("delete from "+ TABLE_USERS);
        //db.execSQL("delete from "+ TABLE_REVIEWS);

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //Carry out SQL query on username and password from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if(cursor.getCount() > 0)    //if there has been a result
        {
            //close the cursor
            cursor.close();

            return true;
        }
        else                        //if no result with username AND password
        {
            return false;
        }
    }

    //add review to reviews table - takes in a parameter of Review
    public void addReview(Comments comments)
    {
        //content values class allows us to store all the data we wish to insert for the new review
        ContentValues values = new ContentValues();

        //call the put method to add the data we wish for a certain column
        values.put(COLUMN_PRODUCT_ID, comments.getProductId());
        values.put(COLUMN_USERNAME, comments.getUsername().toUpperCase());
        values.put(COLUMN_COMMENTS, comments.getComments());
        values.put(COLUMN_REVIEWRATING, comments.getReviewRating());

        //get a connection to the db we setup
        SQLiteDatabase db = this.getWritableDatabase();

        //call the insert method to add all the data in the ContentValues object to a new row in the db
        db.insert(TABLE_REVIEWS, null, values);

        //close the db
        db.close();
    }

    //Fetch reviews left for a product - takes in the parameter ProductId
    public ArrayList<Comments> getReviews(String productId)
    {
        ArrayList<Comments> reviews = new ArrayList<Comments>();

        //build a string which contains the necessary SQL to check if the username and password combo ecist
        String query = "SELECT * FROM " + TABLE_REVIEWS + " WHERE " + COLUMN_PRODUCT_ID + " =  '" + productId + "'";

        //get a connection to the db we setup
        SQLiteDatabase db = this.getWritableDatabase();

        //declare a cursor which will be used to read the data from the database table
        Cursor cursor = db.rawQuery(query, null);

        //Carry out SQL query on username and password from users Table
        //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if(cursor.getCount() > 0)    //if there has been a result
        {
            //move the cursor to the first row in the returned set of data
            cursor.moveToFirst();

            //declare an instance of te Review class
            Comments c = new Comments();

            int ID = cursor.getInt(0);
            c.setProductId(cursor.getString(1));
            c.setUsername(cursor.getString(2));
            c.setComments(cursor.getString(3));
            c.setReviewRating(cursor.getInt(4));

            reviews.add(c);

            //keep repeating whilst there are still rows / review in the returned set of data
            while (cursor.moveToNext())
            {
                c = new Comments();

                ID = cursor.getInt(0);
                c.setProductId(cursor.getString(1));
                c.setUsername(cursor.getString(2));
                c.setComments(cursor.getString(3));
                c.setReviewRating(cursor.getInt(4));

                reviews.add(c);
            }

            //close the cursor
            cursor.close();
        }

        return reviews;
    }
}
