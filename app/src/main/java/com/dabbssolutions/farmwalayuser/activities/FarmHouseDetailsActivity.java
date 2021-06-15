package com.dabbssolutions.farmwalayuser.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseFeaturesDao;
import com.dabbssolutions.farmwalayuser.model.bookings;
import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FarmHouseDetailsActivity extends AppCompatActivity {
    RelativeLayout btnViewDetails;
    TextView btnBookNow;
    TextView details;
    DoubleDateAndTimePickerDialog.Builder doubleBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmhousedetails);
        btnBookNow=(TextView) findViewById(R.id.btnBookNow);
        String Location=getIntent().getStringExtra("location");
        String Price=getIntent().getStringExtra("price");
        String Name=getIntent().getStringExtra("name");
        String[] id=getIntent().getStringExtra("id").split(":");


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
                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
                Toast.makeText(FarmHouseDetailsActivity.this, "Select CheckIn Date", Toast.LENGTH_SHORT).show();
                new SpinnerDatePickerDialogBuilder()
                        .context(FarmHouseDetailsActivity.this)
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                b.setCheckinDate(year+"-"+monthOfYear+1+"-"+dayOfMonth);
                                new SpinnerDatePickerDialogBuilder()
                                        .context(FarmHouseDetailsActivity.this)
                                        .callback(new DatePickerDialog.OnDateSetListener() {
                                            @Override
                                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                                b.setCheckoutDate(year+"-"+monthOfYear+1+"-"+dayOfMonth);
                                                pd.show();
                                                try {

                                                    Date date1 = myFormat.parse(b.getCheckinDate());
                                                    Date date2 = myFormat.parse(b.getCheckoutDate());
                                                    long diff = date2.getTime() - date1.getTime();
                                                    b.setBookingprice(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) * Double.parseDouble(Price));
                                                    b.setLocation(Location);
                                                    b.setIsConfirmed(1);
                                                    SharedPreferences sharedPreferences =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                                                    int uid=Integer.parseInt(sharedPreferences.getString("uid","0"));
                                                    b.setUid(uid);
                                                    if(id[0].toString().trim()=="g"){
                                                        b.setGuesthouseid(Integer.parseInt(id[1]));
                                                    }else if(id[0].toString().trim()=="f"){
                                                        b.setFarmhouseid(Integer.parseInt(id[1]));
                                                    }
                                                    new Thread(new Runnable() {
                                                        @Override
                                                        public void run() {

                                                            String s = new bookingDao().insertBookings(b,FarmHouseDetailsActivity.this);
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    try {
                                                                        pd.cancel();
                                                                        AlertDialog.Builder ab = new AlertDialog.Builder(FarmHouseDetailsActivity.this);
                                                                        if (s.toLowerCase().contains("true")) {

                                                                            ab.setMessage("Your booking has been confirmed. Thank You for chosing farmwalay. Your total bill is Rs." + b.getBookingprice());
                                                                        }else{
                                                                            ab.setMessage("Error confirming your booking. Please try again");
                                                                        }
                                                                        ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                            @Override
                                                                            public void onClick(DialogInterface dialog, int which) {
                                                                                dialog.cancel();
                                                                            }
                                                                        }).show();

                                                                    }catch (Exception e){
                                                                        Toast.makeText(FarmHouseDetailsActivity.this, "Please try again."+e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }).start();
                                                }catch (Exception e){
                                                    Log.v("error formatting date: ",e.getMessage());
                                                }
                                            }
                                        })

                                        .spinnerTheme(R.style.NumberPickerStyle)
                                        .showTitle(true)
                                        .showDaySpinner(true)
                                        .defaultDate(2017, 0, 1)
                                        .maxDate(2050, 0, 1)
                                        .minDate(2000, 0, 1)
                                        .build()
                                        .show();

                            }
                        })

                        .spinnerTheme(R.style.NumberPickerStyle)
                        .showTitle(true)
                        .showDaySpinner(true)
                        .defaultDate(2017, 0, 1)
                        .maxDate(2050, 0, 1)
                        .minDate(2000, 0, 1)
                        .build()
                        .show();




            }
        });



    }


}