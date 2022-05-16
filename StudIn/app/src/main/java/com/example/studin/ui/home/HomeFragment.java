package com.example.studin.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.studin.R;
import com.example.studin.database.AppActivity;
import com.example.studin.database.AppDatabase;
import com.example.studin.database.EventTable;
import com.example.studin.databinding.FragmentHomeBinding;
import com.google.android.material.color.MaterialColors;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AppDatabase db;

    private LinearLayout linearLayout;
    private ScrollView scrollView;
    Button buttonAddEvent;
    Button buttonVisible;

    CalendarView calendarView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        db = AppActivity.getDatabase();
        //Spinner pradzia

        String[] items = getResources().getStringArray(R.array.calendar_array);

        //get the spinner from the xml.
        Spinner spinner = binding.calendarDropDown;
        //create an adapter to describe how the items are displayed,
        //adapters are used in several places in android.
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                //Toast.makeText(adapterView.getContext(), item, Toast.LENGTH_SHORT).show();
                if(item.equals("Month"))
                {
                    //Toast.makeText(adapterView.getContext(), "FIVE", Toast.LENGTH_SHORT).show();

                    scrollView.invalidate();

                    scrollView = binding.scrollViewE;
                    linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    getMonthlyEventList();
                    scrollView.addView(linearLayout);

                    linearLayout.setClickable(false);
                    int childCount = linearLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = linearLayout.getChildAt(i);
                        int childViewId = childView.getId();
                        childView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // open new fragment to view event and edit or remove it
                                EventTable event = db.eventDAO().getTask(childViewId);
                                final Bundle bundle = new Bundle();
                                bundle.putInt("id", event.getId());
                                Navigation.findNavController(view).navigate(R.id.nav_existingEvent, bundle);
                            }
                        });
                    }

                }
                else if(item.equals("All"))
                {
                    scrollView.invalidate();

                    scrollView = binding.scrollViewE;
                    linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    getEventList();
                    scrollView.addView(linearLayout);

                    linearLayout.setClickable(false);
                    int childCount = linearLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = linearLayout.getChildAt(i);
                        int childViewId = childView.getId();
                        childView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // open new fragment to view event and edit or remove it
                                EventTable event = db.eventDAO().getTask(childViewId);
                                final Bundle bundle = new Bundle();
                                bundle.putInt("id", event.getId());
                                Navigation.findNavController(view).navigate(R.id.nav_existingEvent, bundle);
                            }
                        });
                    }
                }
                else if(item.equals("Day"))
                {
                    scrollView.invalidate();

                    scrollView = binding.scrollViewE;
                    linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    getDailyEventList();
                    scrollView.addView(linearLayout);

                    linearLayout.setClickable(false);
                    int childCount = linearLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = linearLayout.getChildAt(i);
                        int childViewId = childView.getId();
                        childView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // open new fragment to view event and edit or remove it
                                EventTable event = db.eventDAO().getTask(childViewId);
                                final Bundle bundle = new Bundle();
                                bundle.putInt("id", event.getId());
                                Navigation.findNavController(view).navigate(R.id.nav_existingEvent, bundle);
                            }
                        });
                    }
                }
                else if(item.equals("Week"))
                {
                    scrollView.invalidate();

                    scrollView = binding.scrollViewE;
                    linearLayout = new LinearLayout(getActivity());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    getWeeklyEventList();
                    scrollView.addView(linearLayout);

                    linearLayout.setClickable(false);
                    int childCount = linearLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = linearLayout.getChildAt(i);
                        int childViewId = childView.getId();
                        childView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // open new fragment to view event and edit or remove it
                                EventTable event = db.eventDAO().getTask(childViewId);
                                final Bundle bundle = new Bundle();
                                bundle.putInt("id", event.getId());
                                Navigation.findNavController(view).navigate(R.id.nav_existingEvent, bundle);
                            }
                        });
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        buttonVisible = binding.buttonVisible;
        buttonVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarView cc = (CalendarView) getView().findViewById(R.id.calendarView);

                if (cc.getVisibility() == View.VISIBLE)
                {
                    cc.setVisibility(View.GONE);
                    scrollView.getLayoutParams().height = 1614;
                }
                else if (cc.getVisibility() == View.GONE)
                {
                    cc.setVisibility(View.VISIBLE);
                    scrollView.getLayoutParams().height = 688;
                }
            }
        });


        buttonAddEvent = binding.buttonAddEvent;
        buttonAddEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // on click opens data input view for new event
                showAlertDialogButtonClicked(view);
            }
        });


        scrollView = binding.scrollViewE;
        linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        getEventList();
        scrollView.addView(linearLayout);

        linearLayout.setClickable(false);
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = linearLayout.getChildAt(i);
            int childViewId = childView.getId();
            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // open new fragment to view event and edit or remove it
                    EventTable event = db.eventDAO().getTask(childViewId);
                    final Bundle bundle = new Bundle();
                    bundle.putInt("id", event.getId());
                    Navigation.findNavController(view).navigate(R.id.nav_existingEvent, bundle);
                }
            });
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    public void showAlertDialogButtonClicked(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new task");

        // add a radio button list
        String[] opts = {"Manually", "Scan QR code"};
        int checkedItem = -1;
        builder.setSingleChoiceItems(opts, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // user checked an item
                // user clicked OK
                if (which == 0){
                    Navigation.findNavController(view).navigate(R.id.nav_addNewEvent);
                    //Toast.makeText(view.getContext(), "MANUALLY", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
                else if (which == 1) {
                    Navigation.findNavController(view).navigate(R.id.nav_camera);
                    //Toast.makeText(view.getContext(), "QR", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();


        /**builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", null); **/

// create and show the alert dialog

        // setup the alert builder
        /**AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add new task");

        // add a list
        String[] options = {"Manually", "Scan QR code", "Testas"};
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // manually
                        Navigation.findNavController(view).navigate(R.id.nav_addNewEvent);
                        Toast.makeText(view.getContext(), "MANUALLY", Toast.LENGTH_SHORT).show();
                    case 1: // qr code
                        Navigation.findNavController(view).navigate(R.id.nav_camera);
                        Toast.makeText(view.getContext(), "QR", Toast.LENGTH_SHORT).show();

                }
            }
        });



        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show(); **/
    }



    private void getEventList() {
        scrollView.removeAllViews();
        List<EventTable> eventList = db.eventDAO().getAllTasks();
        ViewList(eventList);
    }

    private void getMonthlyEventList() {
        scrollView.removeAllViews();
        List<EventTable> eventList = db.eventDAO().getMonthlyTasks();
        ViewList(eventList);
    }

    private void getDailyEventList() {
        scrollView.removeAllViews();
        List<EventTable> eventList = db.eventDAO().getDailyTasks();
        ViewList(eventList);
    }

    private void getWeeklyEventList() {
        scrollView.removeAllViews();
        List<EventTable> eventList = db.eventDAO().getWeeklyTasks();
        ViewList(eventList);
    }

    private void ViewList(List<EventTable> eventList){
        for (EventTable event : eventList) {
            Button button = new Button(getActivity());
            button.setId(event.getId());
            button.setGravity(Gravity.LEFT);
            button.setPadding(20,20,20,20);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );
            params.setMargins(5, 5, 50, 20);
            button.setLayoutParams(params);

            GradientDrawable shape =  new GradientDrawable();
            shape.setCornerRadius(15);

            shape.setColor(MaterialColors.getColor(button, com.google.android.material.R.attr.colorSecondaryVariant));
            button.setBackground(shape);

            button.setText(event.getStringMain());
            linearLayout.addView(button);
        }
    }

}
