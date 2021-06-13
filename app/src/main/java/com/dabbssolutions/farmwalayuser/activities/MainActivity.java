package com.dabbssolutions.farmwalayuser.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.adapters.AdapterFarmhouses;
import com.dabbssolutions.farmwalayuser.adapters.AdapterGuesthouses;
import com.dabbssolutions.farmwalayuser.dao.farmhouseDao;
import com.dabbssolutions.farmwalayuser.dao.guesthouseDao;
import com.dabbssolutions.farmwalayuser.model.farmhouses;
import com.dabbssolutions.farmwalayuser.model.guesthouses;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RelativeLayout guestHouseBtn,farmHouseBtn;
    ListView lv;
    EditText txtSearch;
    RelativeLayout BtnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSearch=(EditText)findViewById(R.id.txtSearchingData);
        BtnSearch = (RelativeLayout) findViewById(R.id.btnSearch);
        guestHouseBtn=(RelativeLayout)findViewById(R.id.guesthousebtn);
        farmHouseBtn=(RelativeLayout)findViewById(R.id.farmhousebtn);
        lv=(ListView)findViewById(R.id.lvItems);
        final ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Please wait");


        guestHouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


                                    });

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
                pd.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new farmhouseDao().getAllFarmhouses(MainActivity.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    pd.cancel();
                                    farmhouses[] u = new Gson().fromJson(s,farmhouses[].class);
                                    ArrayList<farmhouses> userslist = new ArrayList();
                                    ArrayList<farmhouses> userslist2 = new ArrayList();
                                    Collections.addAll(userslist,u);
                                    Collections.addAll(userslist2,u);
                                    AdapterFarmhouses aa = new AdapterFarmhouses(userslist, MainActivity.this,0);
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
                                    });

                                }catch (Exception d){
                                    Toast.makeText(MainActivity.this, d.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });

    }
}