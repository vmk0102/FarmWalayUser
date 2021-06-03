package com.dabbssolutions.farmwalayuser;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityForgotPass extends AppCompatActivity {
    LinearLayout Add, View, Update, Delete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_pass);

    }
}
