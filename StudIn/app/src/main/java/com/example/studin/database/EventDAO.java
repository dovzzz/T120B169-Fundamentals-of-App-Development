package com.example.studin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDAO {

    @Insert
    void insert(EventTable task);

    @Query("DELETE FROM EventTable")
    void deleteAll();

    @Query("SELECT * from EventTable")
    List<EventTable> getAllTasks();

}
