package uk.ac.cityofglasgowcollege.assessement3_pmg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 30257320 on 12/02/2018.
 */

public class DBHelper extends SQLiteOpenHelper{

    //final variables to hold the names of the tables
    private static final String DATABASE_NAME = "GlasgowToursDB";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "UserInfo";
    private static final String TABLE_COMMENTS = "comments";

    //build a string which contains the necessary SQL to create the USERS table
    private static final String DATABASE_CREATE = "CREATE TABLE UserInfo (Username text primary key, Password text not null, Firstname text not null, Lastname text not null,Email text not null);";

    //build a string which contains the necessary SQL to create the REVIEWS table
    private static final String DATABASE_CREATE2 ="CREATE TABLE Comments (Username text primary key, productId text not null, comments text not null, reviewRating text not null);";

    //constructor for the DBHelper class - takes in a parameter defining the context
    public DBHelper (Context context){
        //UsersDB is the name of the database which will be created
        //null is to say use the standard SQL Cursor behaviours   //1 is the version number of the DB
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //Create tables for database
    @Override
    public void onCreate(SQLiteDatabase db) {
        //try to execute the sql by calling the execSQL method
        try {
            db.execSQL(DATABASE_CREATE);
        }catch (SQLiteException ex){
            //show error if there is a problem
            String e = ex.getMessage();
        }

        try{
            db.execSQL(DATABASE_CREATE2);
        }catch(SQLiteException ex){
            String e = ex.getMessage();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+ TABLE_NAME + TABLE_COMMENTS;
        db.execSQL(query);
        this.onCreate(db);

    }

}