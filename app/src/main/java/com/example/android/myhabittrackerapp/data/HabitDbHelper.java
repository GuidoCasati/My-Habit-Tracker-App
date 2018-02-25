package com.example.android.myhabittrackerapp.data;

/**
 * Created by guido on 16/07/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.myhabittrackerapp.data.HabitContract.HabitEntry;

/**
 * Database helper for Habits app. Manages database creation and version management.
 */
public class HabitDbHelper extends SQLiteOpenHelper {
    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habit_tracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        final String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_TIME + " INTEGER NOT NULL);";
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Create a String that contains the SQL statement to delete habits table
        final String SQL_DELETE_HABITS_TABLE = "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;
        // Execute the SQL statement
        db.execSQL(SQL_DELETE_HABITS_TABLE);
        // Call onCreate method to recreate the DB
        onCreate(db);
    }

    /**
     * This is called when a new habit need to be inserted into habits table
     */
    public void insertEntry(String name, int time) {
        // get instance of the db
        SQLiteDatabase db = getWritableDatabase();

        // add habit to contentValues object
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitEntry.COLUMN_HABIT_NAME, name);
        contentValues.put(HabitEntry.COLUMN_HABIT_TIME, time);

        //insert to db
        db.insert(HabitEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * This is called to read out the entries from the table
     */
    public Cursor readEntries() {
        //create a cursor object
        Cursor cursor;

        //get instance of the db
        SQLiteDatabase db = getReadableDatabase();

        // columns array to retrieve
        String[] columns = {HabitEntry.COLUMN_HABIT_NAME, HabitEntry.COLUMN_HABIT_TIME};

        // execute the query to the db
        cursor = db.query(HabitEntry.TABLE_NAME, columns, null, null, null, null, null);

        return cursor;
    }
}
