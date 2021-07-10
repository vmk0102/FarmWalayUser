package com.dabbssolutions.farmwalayuser.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dabbssolutions.farmwalayuser.R;

public class ActivityBookings extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_viewdetails);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
    }
}
