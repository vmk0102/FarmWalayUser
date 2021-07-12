package com.dabbssolutions.farmwalayuser.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.adapters.AdapterFarmhouses;
import com.dabbssolutions.farmwalayuser.adapters.AdapterGuesthouses;
import com.dabbssolutions.farmwalayuser.dao.farmhouseDao;
import com.dabbssolutions.farmwalayuser.dao.guesthouseDao;
import com.dabbssolutions.farmwalayuser.dao.adsDAO;
import com.dabbssolutions.farmwalayuser.model.farmhouses;
import com.dabbssolutions.farmwalayuser.model.guesthouses;
import com.dabbssolutions.farmwalayuser.model.ads;
import com.google.gson.Gson;
import com.tsongkha.spinnerdatepicker.DatePicker;
import com.tsongkha.spinnerdatepicker.DatePickerDialog;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    RelativeLayout guestHouseBtn,farmHouseBtn;
    ListView lv;
    EditText txtSearch, startDate, endDate;
    RelativeLayout BtnSearch;
    String checkinDate,checkoutDate;
    ImageView imgbanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtSearch=(EditText)findViewById(R.id.txtSearchingData);
        BtnSearch = (RelativeLayout) findViewById(R.id.btnSearch);
        guestHouseBtn=(RelativeLayout)findViewById(R.id.guesthousebtn);
        farmHouseBtn=(RelativeLayout)findViewById(R.id.farmhousebtn);
        lv=(ListView)findViewById(R.id.lvItems);
        startDate = (EditText)findViewById(R.id.txtStartDate);
        endDate = (EditText)findViewById(R.id.txtEndDate);
        imgbanner = (android.widget.ImageView)findViewById(R.id.imgbanner);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);

        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                checkinDate = startDate.getText().toString();
                checkoutDate = endDate.getText().toString();
                if( !farmHouseBtn.isEnabled() ){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String farms = new farmhouseDao().getFarmhouseByDate(checkinDate, checkoutDate, MainActivity.this);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.cancel();
                                    farmhouses[] u = new Gson().fromJson(farms,farmhouses[].class);
                                    ArrayList<farmhouses> userslist = new ArrayList();
                                    ArrayList<farmhouses> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterFarmhouses aa = new AdapterFarmhouses(userslist, MainActivity.this,0);
                                    lv.setAdapter(aa);
                                }
                            });
                        }
                    }).start();
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final String guests = new guesthouseDao().getGuesthouseByDate(checkinDate, checkoutDate, MainActivity.this);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pd.cancel();
                                    guesthouses[] u = new Gson().fromJson(guests,guesthouses[].class);
                                    ArrayList<guesthouses> userslist = new ArrayList();
                                    ArrayList<guesthouses> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterGuesthouses aa = new AdapterGuesthouses(userslist, MainActivity.this,0);
                                    lv.setAdapter(aa);
                                }
                            });
                        }
                    }).start();
                }
            }
        });





        guestHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDate.setText("");
                startDate.setText("");
                farmHouseBtn.setEnabled(true);
                guestHouseBtn.setEnabled(false);
                farmHouseBtn.setBackgroundResource(R.drawable.roundedbtn);
                guestHouseBtn.setBackgroundResource(R.drawable.round_btn_disable);
                pd.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new guesthouseDao().getAllGuestHouses(MainActivity.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                   pd.cancel();
                                    guesthouses[] u = new Gson().fromJson(s,guesthouses[].class);
                                    ArrayList<guesthouses> userslist = new ArrayList();
                                    ArrayList<guesthouses> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterGuesthouses aa = new AdapterGuesthouses(userslist, MainActivity.this,0);
                                    lv.setAdapter(aa);

                                 /*  txtSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            userslist.clear();
                                            if(txtSearch.getText().length()>0){

                                                for(int i=0;i<userslist2.size();i++){


                                                }
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {
                                            userslist.clear();
                                            if(!txtSearch.getText().toString().trim().equals("")){

                                                for(int i=0;i<userslist2.size();i++){
                                                    if(userslist2.get(i).getGuesthousename().toLowerCase().contains(txtSearch.getText().toString().toLowerCase().trim())){
                                                        userslist.add(userslist2.get(i));
                                                    }
                                                }
                                                if(userslist.size()>0){
                                                    aa.notifyDataSetChanged();
                                                }else{
                                                    userslist.addAll(userslist2);
                                                    aa.notifyDataSetChanged();
                                                }

                                            }else{
                                                userslist.addAll(userslist2);
                                                aa.notifyDataSetChanged();
                                            }


                                        }


                                    });*/
                                }catch (Exception d){
                                    Toast.makeText(MainActivity.this, d.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

        farmHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endDate.setText("");
                startDate.setText("");
                farmHouseBtn.setEnabled(false);
                guestHouseBtn.setEnabled(true);
                farmHouseBtn.setBackgroundResource(R.drawable.round_btn_disable);
                guestHouseBtn.setBackgroundResource(R.drawable.roundedbtn);
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new farmhouseDao().getAllFarmhouses(MainActivity.this);
                        final String image = new adsDAO().getAllAds(MainActivity.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    pd.cancel();
                                    farmhouses[] u = new Gson().fromJson(s,farmhouses[].class);
                                    ads[] img = new Gson().fromJson(image,ads[].class);

                                    imgbanner= findViewById(R.id.imgbanner);
                                    byte[] decodedString = Base64.decode(img[0].getImage(), Base64.DEFAULT);
                                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                    imgbanner.setImageBitmap(decodedByte);

                                    ArrayList<farmhouses> userslist = new ArrayList();
                                    ArrayList<farmhouses> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterFarmhouses aa = new AdapterFarmhouses(userslist, MainActivity.this,0);
                                    lv.setAdapter(aa);

                                    /*txtSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            userslist.clear();
                                            if(txtSearch.getText().length()>0){

                                                for(int i=0;i<userslist2.size();i++){


                                                }
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {
                                            userslist.clear();
                                            if(!txtSearch.getText().toString().trim().equals("")){

                                                for(int i=0;i<userslist2.size();i++){
                                                    if(userslist2.get(i).getFarmname().toLowerCase().contains(txtSearch.getText().toString().toLowerCase().trim())){
                                                        userslist.add(userslist2.get(i));
                                                    }
                                                }
                                                if(userslist.size()>0){
                                                    aa.notifyDataSetChanged();
                                                }else{
                                                    userslist.addAll(userslist2);
                                                    aa.notifyDataSetChanged();
                                                }

                                            }else{
                                                userslist.addAll(userslist2);
                                                aa.notifyDataSetChanged();
                                            }


                                        }
                                    });*/

                                }catch (Exception d){
                                    Toast.makeText(MainActivity.this, d.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });


        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(MainActivity.this)
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                startDate.setText(dayOfMonth+"-"+monthOfYear+1+"-"+year);
                                checkinDate=year+"-"+monthOfYear+1+"-"+dayOfMonth;
                                //startDate.setText(checkinDate);
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

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SpinnerDatePickerDialogBuilder()
                        .context(MainActivity.this)
                        .callback(new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                endDate.setText(dayOfMonth+"-"+monthOfYear+1+"-"+year);
                                checkoutDate=year+"-"+monthOfYear+1+"-"+dayOfMonth;
                                //endDate.setText(checkoutDate);
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



//      SHOWING FARMHOUSE LIST BY DEFUALT ON PAGE LOAD UP
//      TRIGGERING THE ON CLICK LISTENER
        farmHouseBtn.performClick();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btnLogout){
            startActivity(new Intent(MainActivity.this,ActivityLogin.class));
            finish();
            return  true;
        }
        else if(item.getItemId()==R.id.btnMyBookings){
            return  true;
        }
        else if(item.getItemId()==R.id.btnProfile){
            startActivity(new Intent(MainActivity.this,ActivityMyProfile.class));
        }
        return  false;

    }
}