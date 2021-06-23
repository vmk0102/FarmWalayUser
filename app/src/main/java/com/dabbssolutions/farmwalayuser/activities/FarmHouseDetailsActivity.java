package com.dabbssolutions.farmwalayuser.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.adapters.AdapterFarmhousesFeatures;
import com.dabbssolutions.farmwalayuser.adapters.AdapterFarmhousesPictures;
import com.dabbssolutions.farmwalayuser.adapters.AdapterGuesthousePictures;
import com.dabbssolutions.farmwalayuser.adapters.AdapterGuesthousesFeatures;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.dao.farmhouseFeaturesDao;
import com.dabbssolutions.farmwalayuser.dao.farmhousePicturesDao;
import com.dabbssolutions.farmwalayuser.dao.guesthousePicturesDao;
import com.dabbssolutions.farmwalayuser.dao.guesthousefeatureDao;
import com.dabbssolutions.farmwalayuser.model.bookings;
import com.dabbssolutions.farmwalayuser.model.farmhousefeatures;
import com.dabbssolutions.farmwalayuser.model.farmhousepictures;
import com.dabbssolutions.farmwalayuser.model.farmhouses;
import com.dabbssolutions.farmwalayuser.model.guesthousefeatures;
import com.dabbssolutions.farmwalayuser.model.guesthousepictures;
import com.dabbssolutions.farmwalayuser.model.guesthouses;
import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog;
import com.google.gson.Gson;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FarmHouseDetailsActivity extends AppCompatActivity {
    RelativeLayout btnViewDetails;
    TextView btnBookNow;
    TextView details;
    DoubleDateAndTimePickerDialog.Builder doubleBuilder;
    GridView lvpics;
    TextView txtFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmhousedetails);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        btnBookNow=(TextView) findViewById(R.id.btnBookNow);
        String Location=getIntent().getStringExtra("location");
        String Price=getIntent().getStringExtra("price");
        String Name=getIntent().getStringExtra("name");
        String[] id=getIntent().getStringExtra("id").split(":");
        ArrayList<guesthousefeatures> gfs = new ArrayList<>();
        ArrayList<farmhousefeatures> ffs = new ArrayList<>();
        ArrayList<guesthousepictures>gps= new ArrayList<>();
        ArrayList<farmhousepictures> fps = new ArrayList<>();
        Log.v("ads",id[0]+" "+id[1]);
        final Gson gson= new Gson();
        ProgressDialog pd = new ProgressDialog(FarmHouseDetailsActivity.this);
        pd.setMessage("Loading details, please wait");
        details=(TextView)findViewById(R.id.txtdetails);
        final StringBuilder sb = new StringBuilder();
        TextView btnBookNow = (TextView) findViewById(R.id.btnBookNow);
        TextView txtname=(TextView)findViewById(R.id.txtname);
        txtFeatures=(TextView)findViewById(R.id.txtFeatures);
        lvpics=(GridView)findViewById(R.id.lvPics);
        txtname.setText(Name);
        sb.append(
                "Location: "+Location+"\t" +
                "Price: Rs. "+Price);
        details.setText(sb);
        pd.show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    farmhouses f = new farmhouses();
                    f.setFarmhouseid(Integer.parseInt(id[1]));
                    String s = new farmhouseFeaturesDao().getAllFarmhouseFeatures(FarmHouseDetailsActivity.this,f);
                    String s1=new farmhousePicturesDao().getAllFarmhouseFeatures(FarmHouseDetailsActivity.this,f);
                    Log.v("ffs",s);
                    Log.v("fps",s);


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.cancel();
                                if(s!=null) {
                                    farmhousefeatures[] gf = gson.fromJson(s, farmhousefeatures[].class);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("Features: ");
                                    for(int i =0;i<gf.length;i++){
                                        if(i!=gf.length-1) {
                                            sb.append(gf[i].getFeatureName() + ", ");
                                        }else {
                                            sb.append(gf[i].getFeatureName());
                                        }
                                    }
                                    txtFeatures.setText(sb);

                                }
                                if(s1!=null){
                                    farmhousepictures[] gp = gson.fromJson(s1,farmhousepictures[].class);
                                    Collections.addAll(fps,gp);
                                    AdapterFarmhousesPictures agp = new AdapterFarmhousesPictures(fps,FarmHouseDetailsActivity.this,0);
                                    lvpics.setAdapter(agp);
                                }
                                pd.setMessage("Verifying booking please wait");




                            }
                        });
                    }


            }).start();





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
                                Toast.makeText(FarmHouseDetailsActivity.this, "Set Checkout Date", Toast.LENGTH_SHORT).show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=new MenuInflater(FarmHouseDetailsActivity.this);
        menuInflater.inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btnLogout){
            startActivity(new Intent(FarmHouseDetailsActivity.this,ActivityLogin.class));
            finish();
            return  true;
        }
        else if(item.getItemId()==R.id.btnMyBookings){
            return  true;
        }
        else if(item.getItemId()==R.id.btnProfile){
            startActivity(new Intent(FarmHouseDetailsActivity.this,ActivityMyProfile.class));
            startActivity(new Intent(FarmHouseDetailsActivity.this,ActivityMyProfile.class));
        }
        return  false;

    }


}