package com.example.studin.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.studin.R;
import com.example.studin.databinding.FragmentHomeBinding;
import com.example.studin.ui.addNewEvent.AddNewEventFragment;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Button buttonAddEvent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        String[] items = getResources().getStringArray(R.array.calendar_array);

        //get the spinner from the xml.
        Spinner spinner = binding.calendarDropDown;
        //create an adapter to describe how the items are displayed,
        //adapters are used in several places in android.
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(),
                android.R.layout.simple_spinner_item,items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        buttonAddEvent = binding.buttonAddEvent;
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // on click opens data input view for new event

                showAlertDialogButtonClicked(view);
                Snackbar.make(view, "Button + clicked", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new task");

        // add a list
        String[] options = {"Manually", "Scan QR code"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // manually
                        Navigation.findNavController(view).navigate(R.id.nav_addNewEvent);
                    case 1: // qr code
                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}