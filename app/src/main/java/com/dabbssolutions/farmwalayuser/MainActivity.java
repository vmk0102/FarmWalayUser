package com.dabbssolutions.farmwalayuser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RelativeLayout guestHouseBtn,farmHouseBtn,ViewDetails,BookNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guestHouseBtn=(RelativeLayout)findViewById(R.id.guesthousebtn);
        farmHouseBtn=(RelativeLayout)findViewById(R.id.farmhousebtn);
        ViewDetails=(RelativeLayout)findViewById(R.id.viewDetails);
        BookNow=(RelativeLayout)findViewById(R.id.bookNow);
        guestHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Loading Guesthouses", Toast.LENGTH_SHORT).show();
            }
        });
        farmHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Loading farmhouses", Toast.LENGTH_SHORT).show();
            }
        });
        ViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FarmHouseDetailsActivity.class));
            }
        });
        BookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
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