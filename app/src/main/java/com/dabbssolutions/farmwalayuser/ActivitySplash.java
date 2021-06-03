package com.dabbssolutions.farmwalayuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

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
            }
        });
    }
}
