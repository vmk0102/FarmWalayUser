package com.dabbssolutions.farmwalayuser;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignup extends AppCompatActivity {
    LinearLayout Add, View, Update, Delete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

    }
}