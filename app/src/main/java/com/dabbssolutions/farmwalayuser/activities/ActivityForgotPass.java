package com.dabbssolutions.farmwalayuser.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;

public class ActivityForgotPass extends AppCompatActivity {
    LinearLayout Add, View, Update, Delete;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_pass);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        android.view.View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);

    }
}
