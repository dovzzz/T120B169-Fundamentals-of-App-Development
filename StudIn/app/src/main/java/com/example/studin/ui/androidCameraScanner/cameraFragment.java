package com.example.studin.ui.androidCameraScanner;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.studin.R;
import com.example.studin.database.AppActivity;
import com.example.studin.database.AppDatabase;
import com.example.studin.database.EventTable;
import com.example.studin.databinding.FragmentCameraBinding;
import com.google.zxing.Result;




public class cameraFragment extends Fragment {

    private CodeScanner mCodeScanner;
    private static final int REQUEST_CAMERA = 1;
    private static final String[] CAMERA_PERMISSION = {Manifest.permission.CAMERA};
    private FragmentCameraBinding binding;
    private AppDatabase db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();

        binding = FragmentCameraBinding.inflate(inflater, container, false);
        View root = inflater.inflate(R.layout.fragment_camera, container, false);
        CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        db= AppActivity.getDatabase();


        verifyCameraPermissions(getActivity());
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
                        handleResult(result);

                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }



    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    public static void verifyCameraPermissions(Activity activity) {
        // check if write permission is true
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.CAMERA);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // ask user for the permission
            ActivityCompat.requestPermissions(activity, CAMERA_PERMISSION,
                    REQUEST_CAMERA);
        }
    }

    public void handleResult(Result result) {
        String rawRes = result.getText();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Scan result:");
        builder.setMessage(result.getText());

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String[] seperated = rawRes.split(";");
                EventTable event = new EventTable(seperated[0].toString(),
                        seperated[1].toString(), seperated[2].toString(),
                        seperated[3].toString(), seperated[4].toString(),
                        seperated[5].toString(), seperated[6].toString(),
                        seperated[7].toString());
                db.eventDAO().insert(event);
                Toast toast = Toast.makeText(getContext(), "Event was successfully added!" + event.getStringAll(),
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
