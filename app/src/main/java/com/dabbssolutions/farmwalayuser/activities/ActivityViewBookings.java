package com.dabbssolutions.farmwalayuser.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.adapters.AdapterFarmhouseBookings;
import com.dabbssolutions.farmwalayuser.adapters.AdapterGuesthouseBookings;
import com.dabbssolutions.farmwalayuser.dao.bookingDao;
import com.dabbssolutions.farmwalayuser.model.bookings;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.SocketHandler;

public class ActivityViewBookings extends AppCompatActivity {
    RelativeLayout guestHouseBtn,farmHouseBtn;
    ListView lv;
    EditText txtSearch;
    RelativeLayout BtnSearch;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbookings);
        txtSearch=(EditText)findViewById(R.id.txtSearchingData);
        BtnSearch = (RelativeLayout) findViewById(R.id.btnSearch);
        guestHouseBtn=(RelativeLayout)findViewById(R.id.guesthousebtn);
        farmHouseBtn=(RelativeLayout)findViewById(R.id.farmhousebtn);
        lv=(ListView)findViewById(R.id.lvItems);
        final ProgressDialog pd = new ProgressDialog(ActivityViewBookings.this);
        pd.setMessage("Please wait");
        SharedPreferences sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        UserID=sharedPreferences.getString("uid",null);

        guestHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new bookingDao().getGuesthouseBookingsByID(UserID,ActivityViewBookings.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    pd.cancel();
                                    bookings[] u = new Gson().fromJson(s,bookings[].class);
                                    ArrayList<bookings> userslist = new ArrayList();
                                    ArrayList<bookings> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterGuesthouseBookings aa = new AdapterGuesthouseBookings(userslist, ActivityViewBookings.this,0);
                                    lv.setAdapter(aa);

                                    txtSearch.addTextChangedListener(new TextWatcher() {
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


                                        }
                                    });

                                }catch (Exception d){
                                    Toast.makeText(ActivityViewBookings.this, "no bookings found", Toast.LENGTH_SHORT).show();
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
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new bookingDao().getFarmhouseBookingsByID(UserID,ActivityViewBookings.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    pd.cancel();
                                    bookings[] u = new Gson().fromJson(s,bookings[].class);
                                    ArrayList<bookings> userslist = new ArrayList();
                                    ArrayList<bookings> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterFarmhouseBookings aa = new AdapterFarmhouseBookings(userslist, ActivityViewBookings.this,0);
                                    lv.setAdapter(aa);

                                    txtSearch.addTextChangedListener(new TextWatcher() {
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


                                        }
                                    });

                                }catch (Exception d){
                                    Toast.makeText(ActivityViewBookings.this, "no bookings found", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

    }
}
