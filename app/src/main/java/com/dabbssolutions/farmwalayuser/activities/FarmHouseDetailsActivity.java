package com.dabbssolutions.farmwalayuser.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseFeaturesDao;
import com.dabbssolutions.farmwalayuser.model.bookings;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

public class FarmHouseDetailsActivity extends AppCompatActivity {
    RelativeLayout btnViewDetails;
    TextView btnBookNow;
    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmhousedetails);
        btnBookNow=(TextView) findViewById(R.id.btnBookNow);
        String Location=getIntent().getStringExtra("location");
        String Price=getIntent().getStringExtra("price");
        String Name=getIntent().getStringExtra("name");
        String id=getIntent().getStringExtra("id");
        details=(TextView)findViewById(R.id.txtdetails);
        final StringBuilder sb = new StringBuilder();
        TextView btnBookNow = (TextView) findViewById(R.id.btnBookNow);
        sb.append("Name: "+Name+"\n" +
                "Location: "+Location+"\n" +
                "Price: "+Price);
        details.setText(sb);
        ProgressDialog pd = new ProgressDialog(FarmHouseDetailsActivity.this);
        pd.setMessage("Verifying booking please wait");
        btnBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final bookings b = new bookings();
                new SpinnerDatePickerDialogBuilder()
                        .context(FarmHouseDetailsActivity.this)

                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                               b.setCheckinDate(year+"-"+monthOfYear+1+"-"+dayOfMonth);

                            }
                        })

                        .spinnerTheme(R.style.NumberPickerStyle)
                        .showTitle(true)

                        .showDaySpinner(true)
                        .defaultDate(2021, 0, 1)
                        .maxDate(2050, 0, 1)
                        .minDate(2000, 0, 1)
                        .build()
                        .show();


                b.setBookingprice(Float.parseFloat(Price));
                b.setLocation(Location);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        String s = new bookingDao().insertBookings(b,FarmHouseDetailsActivity.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                if(s.toLowerCase().contains("true")){
                                    AlertDialog.Builder ab = new AlertDialog.Builder(FarmHouseDetailsActivity.this);
                                    ab.setMessage("Your booking has been confirmed. Thank You for chosing farmwalay");
                                    ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });



    }
}