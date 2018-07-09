package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by 30257320 on 12/02/2018.
 */

public class MyDB {
    //global variables to hold the table name & each column in the database
    private static final String TABLE_NAME = "UserInfo";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_FIRSTNAME = "FirstName";
    private static final String COLUMN_LASTNAME = "LastName";
    private static final String COLUMN_EMAIL = "Email";

    //variables for the comments columns
    private static final String TABLE_COMMENTS = "comments";
    private static final String COLUMN_PRODUCT_ID = "productId";
    private static final String COLUMN_COMMENTS = "comments";
    private static final String COLUMN_REVIEWRATING = "reviewRating";

    //global variables to hold  DBHelper & a SQLiteDatabase
    private DBHelper dbhelper;
    private SQLiteDatabase database;

    public MyDB(Context context) {
        dbhelper = new DBHelper(context);
        database = dbhelper.getWritableDatabase();
    }

    public void insertUser(Users u) {

        //Create a new ContentValues object & populate it
        //Call the database.insert method
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, u.getUsername());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_FIRSTNAME, u.getEmail());
        values.put(COLUMN_LASTNAME, u.getLastname());
        values.put(COLUMN_PASSWORD, u.getPassword());
        values.put(COLUMN_FIRSTNAME, u.getFirstname());

        //try to insert the table
        try {
            database.insert(TABLE_NAME, null, values);
        } catch (SQLiteException ex) {
            //show error id not possible
            Log.e("Table insert error", ex.toString());
        }
    }

    public void insertComment(Comments c) {
        //Create a new ContentValues object & populate it
        //Call the database.insert method
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_COMMENTS, c.getComments());
        values.put(COLUMN_REVIEWRATING, c.getReviewRating());

        //try to insert the table
        try {
            database.insert(TABLE_COMMENTS, null, values);
        } catch (SQLiteException ex) {
            Log.e("Table insert error", ex.toString());
        }
    }

    //Check if new username is already in table - takes in a parameter of username
    public boolean checkUser(String username, String password){
        //build a string which contains the necessary SQL to check if the username exists
        String sql = "Select * from UserInfo Where Username = ? and Password = ?";
        String[] args = {username, password};
        try {

            //declare a cursor which will be used to read the data from the database table
            Cursor cursor = database.rawQuery(sql,args);

            //Carry out SQL query on username from users Table
            //Use 'Cursor' to hold any results - should be 1 result if found and 0 if not
        if (cursor.getCount() == 0) {
            //the username is available
            return false;
        } else {
            //then the username already exists
            return true;
        }
        } catch (SQLiteException ex){
            String e = ex.getMessage();
            return false;
        }
    }

    //get the entered comments
    public ArrayList<Comments> getcomments(String productId){

        //declare an array list for Comments class objects
        ArrayList<Comments> comments = new ArrayList<Comments>();

        //build a string which contains the sql for the comments
        String query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COLUMN_PRODUCT_ID + " = '" + productId + "'";

        //connect ot the database
        database = dbhelper.getWritableDatabase();

        //a cursor will be used to read the data from the database table
        Cursor cursor = database.rawQuery(query, null);

        //use the cursor count to hold the comments from the database
        if(cursor.getCount() > 0){
            ////move the cursor to the beginning after each record is returned
            cursor.moveToFirst();

            //create an instance of the comments class
            Comments c = new Comments();

            c.setUsername(cursor.getString(1));
            c.setComments(cursor.getString(2));
            c.setReviewRating(cursor.getInt(3));

            //add the comments to the arraylist
            comments.add(c);

            //keep repeating while there are still records in table
            while (cursor.moveToNext() == true){
                c = new Comments();

                c.setUsername(cursor.getString(1));
                c.setComments(cursor.getString(2));
                c.setReviewRating(cursor.getInt(3));

                //add to the list
                comments.add(c);

            }

            //close cursor when there are no more records
            cursor.close();

            //close database
            database.close();
        }
        //return the populated comments list
        return comments;
    }
}



