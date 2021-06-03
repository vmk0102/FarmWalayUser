package com.dabbssolutions.farmwalayuser;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class FarmHouseDetailsActivity extends AppCompatActivity {
    RelativeLayout btnViewDetails;
    TextView btnBookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmhousedetails);
        btnBookNow=(TextView) findViewById(R.id.btnBookNow);
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(FarmHouseDetailsActivity.this);
                ab.setTitle(getResources().getString(R.string.app_name));
                ab.setMessage("Thank you for choosing farm walay. You will be contacted by admin to confirm your booking");
                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });

    }
}