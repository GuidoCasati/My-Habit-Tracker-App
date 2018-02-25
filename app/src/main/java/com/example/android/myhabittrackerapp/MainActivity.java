package com.example.android.myhabittrackerapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.myhabittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.myhabittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the textview to display habits
        TextView habitsTV = (TextView) findViewById(R.id.habits);

        //create helper object
        HabitDbHelper habitdbhelper = new HabitDbHelper(this);

        // Get entries from habits table
        Cursor cursor = habitdbhelper.readEntries();

        if (!cursor.moveToFirst() || cursor.getCount() == 0 || cursor == null)
        {
                    /* Insert data to the db */
            habitdbhelper.insertEntry("GO TO THE GYM", 19);
            habitdbhelper.insertEntry("GO TO THE ZOO", 9);
            habitdbhelper.insertEntry("CALL MY GF", 21);
            cursor = habitdbhelper.readEntries();
        }


        try {
            // get indexes of columns
            int nameColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int timeColIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TIME);

            // loop through all entries
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameColIndex);
                int time = cursor.getInt(timeColIndex);

                String line = "\n I use to " + name + " at " + time + " CET.";
                habitsTV.append(line);
            }

        } finally {
            cursor.close();
        }
    }
}

