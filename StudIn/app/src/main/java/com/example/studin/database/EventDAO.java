package com.example.studin.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDAO {

    @Insert
    void insert(EventTable task);

    @Query("UPDATE EventTable SET course_name=:cname, exam_name=:ename, exam_desc=:examd, " +
            "exam_date=:date0, first_retake_date=:date1, exam_time=:time0, " +
            "first_retake_time=:time1, sources=:source WHERE id=:id")
    void update(int id, String cname, String ename, String examd, String date0, String date1,
                String time0, String time1, String source);

    @Query("DELETE FROM EventTable WHERE id=:id")
    void deleteOne(int id);

    @Query("DELETE FROM EventTable")
    void deleteAll();

    @Query("SELECT * from EventTable WHERE id=:id")
    EventTable getTask(int id);

    @Query("SELECT * from EventTable")
    List<EventTable> getAllTasks();

}
