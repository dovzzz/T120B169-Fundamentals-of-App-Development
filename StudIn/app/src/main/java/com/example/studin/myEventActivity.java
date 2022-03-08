package com.example.studin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.zip.Inflater;

public class myEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Already viewing my events", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Intent newInt2 = new Intent(this, settingsActivity.class);
                startActivity(newInt2);
                return true;
            case R.id.item3:
                Toast.makeText(this, "About was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Intent newInt = new Intent(this, MainActivity.class);
                startActivity(newInt);
        }
        return super.onOptionsItemSelected(item);
    }
}