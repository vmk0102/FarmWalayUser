package com.dabbssolutions.farmwalayuser.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dabbssolutions.farmwalayuser.R;
import com.google.gson.internal.$Gson$Preconditions;

public class ActivitySelect extends AppCompatActivity {

    LinearLayout farmHouses,guestHouses,transporters,Caterers
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select);
        farmHouses=(LinearLayout)findViewById(R.id.mFarmhouses);
        guestHouses=(LinearLayout)findViewById(R.id.mGuesthouses);
        transporters=(LinearLayout)findViewById(R.id.mTransporters);
        Caterers=(LinearLayout)findViewById(R.id.mCaterers);
        farmHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        guestHouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        transporters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
