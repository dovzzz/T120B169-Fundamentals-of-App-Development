package com.example.studin;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.util.Date;

@Entity
public class TaskInPlanner {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "course_name")
    private String courseName;

    @NonNull
    @ColumnInfo(name = "exam_name")
    private String examName;

    @NonNull
    @ColumnInfo(name = "exam_desc")
    private String examDesc;

    @NonNull
    @ColumnInfo(name = "exam_date")
    private Date examDate;

    @NonNull
    @ColumnInfo(name = "first_retake_date")
    private Date firstRetakeDate;

    @NonNull
    @ColumnInfo(name = "sources")
    private String sources;

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setCourseName(@NonNull String name) {
        this.courseName = name;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setExamName(@NonNull String name) {
        this.examName = name;
    }

    public String getExamName() {
        return this.examName;
    }

    public void setExamDesc(@NonNull String description) {
        this.examDesc = description;
    }

    public String getExamDesc() {
        return this.examDesc;
    }

    public void setExamDate(@NonNull Date date) {
        this.examDate = date;
    }

    public Date getExamDate() {
        return this.examDate;
    }

    public void setFirstRetakeDate(@NonNull Date date) {
        this.firstRetakeDate = date;
    }

    public Date getFirstRetakeDate() {
        return this.firstRetakeDate;
    }

    public void setSources(@NonNull String sources) {
        this.sources = sources;
    }

    public String getSources() {
        return this.sources;
    }

}
