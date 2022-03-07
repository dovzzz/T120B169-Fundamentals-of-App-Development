package com.example.studin;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities={TaskInPlanner.class},version=1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDAO taskDAO();

}
