package com.example.android.myhabittrackerapp.data;

/**
 * Created by guido on 16/07/2017.
 */

import android.provider.BaseColumns;

/**
 * API contract for the HabitTracker app.
 */
public class HabitContract {

    // empty constructor to prevent someone from accidentally instantiating the contract class
    private HabitContract() {
    }

    /**
     * Inner class that defines constant values for a habits database table.
     * Each entry in the table represents a single habit.
     */
    public static final class HabitEntry implements BaseColumns {
        /**
         * Name of the habit.
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_NAME = "name";
        /**
         * Time of the habit.
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_TIME = "time";
        /**
         * Name of database table for habits
         */
        static final String TABLE_NAME = "habits";
        /**
         * Unique ID number for the habits (only for use in the database table).
         * Type: INTEGER
         */
        static final String _ID = BaseColumns._ID;
    }
}

