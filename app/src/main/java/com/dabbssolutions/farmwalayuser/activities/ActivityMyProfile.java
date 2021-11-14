package com.dabbssolutions.farmwalayuser.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.UserDao;
import com.dabbssolutions.farmwalayuser.model.users;
import com.google.gson.Gson;

public class ActivityMyProfile extends AppCompatActivity {
    EditText FirstName,LastName,Phone,Cnic,Pass;
    RelativeLayout btnSave;
    TextView adminHeader;
    users[] a=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adduser);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        FirstName=(EditText)findViewById(R.id.first_name);
        LastName=(EditText)findViewById(R.id.last_name);
        Phone=(EditText)findViewById(R.id.admin_phone_no);
        Cnic=(EditText)findViewById(R.id.admin_cnic);
        Pass=(EditText)findViewById(R.id.password);
        adminHeader=(TextView)findViewById(R.id.admin_header);
        adminHeader.setText("Profile");
        TextView txtSave=(TextView)findViewById(R.id.txtSave);
        btnSave=(RelativeLayout)findViewById(R.id.btnSave);
        txtSave.setText("Update");
        final ProgressDialog pd = new ProgressDialog(ActivityMyProfile.this);
        pd.setMessage("Please wait");
        SharedPreferences sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String s =sharedPreferences.getString("userprofile",null);

        if(s!=null){
            a = new Gson().fromJson(s,users[].class);
            if(a.length>0){
                FirstName.setText(a[0].getFirstname());
                LastName.setText(a[0].getLastname());
                Cnic.setText(a[0].getCnic());
                Phone.setText(a[0].getPhone());
                Pass.setText(a[0].getPassword());
            }
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                String firstName=FirstName.getText().toString().trim();
                String lastName=LastName.getText().toString().trim();
                String phone=Phone.getText().toString().trim();
                String cnic=Cnic.getText().toString().trim();
                String pass= Pass.getText().toString();

                final users u = new users();
                u.setUid(a[0].getUid());
                u.setFirstname(firstName);
                u.setLastname(lastName);
                u.setCnic(cnic);
                u.setPhone(phone);
                u.setPassword(pass);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                      final String s = new UserDao().updateUsers(u, ActivityMyProfile.this);
                        pd.cancel();
                        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              if(s.toLowerCase().contains("true")) {
                                  AlertDialog.Builder ab = new AlertDialog.Builder(ActivityMyProfile.this);
                                  String s = new Gson().toJson(a);
                                  sharedPreferences.edit().putString("userprofile",s).apply();

                                  ab.setMessage("Profile Updated Successfully");
                                  ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          dialog.cancel();
                                      }
                                  }).show();
                              }else {
                                  AlertDialog.Builder ab = new AlertDialog.Builder(ActivityMyProfile.this);
                                  ab.setMessage("Error Updated Sucessfully");
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=new MenuInflater(ActivityMyProfile.this);
        menuInflater.inflate(R.menu.menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.btnLogout){
            startActivity(new Intent(ActivityMyProfile.this,ActivityLogin.class));
            finish();
            return  true;
        }
        else if(item.getItemId()==R.id.btnMyBookings){
            startActivity(new Intent(ActivityMyProfile.this,ActivityViewBookings.class));
            return  true;
        }
        else if(item.getItemId()==R.id.btnProfile){
            startActivity(new Intent(ActivityMyProfile.this,ActivityMyProfile.class));
            return true;
        }
        return  false;

    }
}
