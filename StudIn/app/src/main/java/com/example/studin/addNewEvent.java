package com.example.studin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.OffsetDateTime;
import java.util.Date;

public class addNewEvent extends AppCompatActivity {

    private EditText courseName;
    private EditText examName;
    private EditText examDesc;
    private long examDate;
    private long firstRetakeDate;
    private EditText sources;

    Button buttonAddEvent;
    Button buttonCancelEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_event);

        courseName = (EditText) findViewById(R.id.editTextCourseName);
        examName = (EditText) findViewById(R.id.editTextTextExamName);
        examDesc = (EditText) findViewById(R.id.editTextExamDesc);
        examDate = R.id.editTextExamDate;
        firstRetakeDate = R.id.editFirstRetakeDate;
        sources = (EditText) findViewById(R.id.editTextSources);

        buttonAddEvent = (Button) findViewById(R.id.buttonAdd);
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // on click opens data input view for new event

                Toast toast = Toast.makeText(getApplicationContext(), "Data: " +
                                courseName.getText() + " " + examName.getText() + " " +
                                examDesc.getText() + " " + examDate + " " +
                                firstRetakeDate + " " + sources.getText() + " ",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        buttonCancelEvent = (Button) findViewById(R.id.buttonCancel);
        buttonCancelEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // on click opens data input view for new event

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
