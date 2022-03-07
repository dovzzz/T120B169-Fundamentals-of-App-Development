package com.example.studin;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insert(TaskInPlanner task);

    @Query("DELETE FROM TaskInPlanner")
    void deleteAll();

    @Query("SELECT * from TaskInPlanner")
    List<TaskInPlanner> getAllTasks();

}
