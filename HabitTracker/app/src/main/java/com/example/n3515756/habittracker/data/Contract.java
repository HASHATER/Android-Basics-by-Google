package com.example.n3515756.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by n3515756 on 11/28/2016.
 */

public final class Contract {

    private Contract() {}

    public static final class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME ="name";
        public final static String COLUMN_HABIT_TIME = "time";
        public final static String COLUMN_HABIT_DURATION = "duration";


    }
}
