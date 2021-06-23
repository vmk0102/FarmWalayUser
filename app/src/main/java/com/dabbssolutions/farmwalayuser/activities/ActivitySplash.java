package com.dabbssolutions.farmwalayuser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;

public class ActivitySplash extends AppCompatActivity {
    RelativeLayout rl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        rl=(RelativeLayout)findViewById(R.id.btnGetStarted);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
                finish();
            }
        });
    }
}
