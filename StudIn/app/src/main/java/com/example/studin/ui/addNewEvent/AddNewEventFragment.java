package com.example.studin.ui.addNewEvent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.studin.MainActivity;
import com.example.studin.R;
import com.example.studin.databinding.FragmentAddneweventBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddNewEventFragment extends Fragment {

    private EditText courseName;
    private EditText examName;
    private EditText examDesc;
    private long examDate;
    private long firstRetakeDate;
    private EditText sources;

    Button buttonAddEvent;
    Button buttonCancelEvent;

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
        examDate = R.id.editTextExamDate;
        firstRetakeDate = R.id.editFirstRetakeDate;
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