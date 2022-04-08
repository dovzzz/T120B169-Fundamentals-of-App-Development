package com.example.studin.ui.addNewEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.studin.databinding.FragmentAddneweventBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddNewEventFragment extends Fragment{

    private EditText courseName;
    private EditText examName;
    private EditText examDesc;
    private EditText examDate0;
    private EditText examDate1;
    private EditText examTime0;
    private EditText examTime1;
    private EditText sources;

    Button buttonAddEvent;
    Button buttonCancelEvent;

    DatePickerDialog pickerDate;
    TimePickerDialog pickerTime;

    private FragmentAddneweventBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddNewEventViewModel addNewEventViewModel =
                new ViewModelProvider(this).get(AddNewEventViewModel.class);

        binding = FragmentAddneweventBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textView2;
        //addNewEventViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        courseName = binding.editTextCourseName;
        examName = binding.editTextTextExamName;
        examDesc = binding.editTextExamDesc;

        examDate0 = binding.editTextDate0;
        examDate0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show date picker dialog
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDate = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                examDate0.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });

        examDate1 = binding.editTextDate1;
        examDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                pickerDate = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                examDate1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                pickerDate.show();
            }
        });

        examTime0 = binding.editTextTime0;
        examTime0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show time picker dialog
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                pickerTime = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                examTime0.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickerTime.show();
            }
        });

        examTime1 = binding.editTextTime1;
        examTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                pickerTime = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                examTime1.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pickerTime.show();
            }
        });

        sources = binding.editTextSources;

        buttonAddEvent = binding.buttonAdd;
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on click opens data input view for new event
                Snackbar.make(view, "Button Add clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        buttonCancelEvent = binding.buttonCancel;
        buttonCancelEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on click opens data input view for new event
                Navigation.findNavController(view).popBackStack();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
