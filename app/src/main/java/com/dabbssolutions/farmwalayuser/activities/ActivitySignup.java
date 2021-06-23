package com.dabbssolutions.farmwalayuser.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.R;
import com.dabbssolutions.farmwalayuser.dao.UserDao;
import com.dabbssolutions.farmwalayuser.model.users;

public class ActivitySignup extends AppCompatActivity {
    EditText FirstName,LastName,Phone,Cnic,Pass;
    RelativeLayout btnSave;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_adduser);
        FirstName=(EditText)findViewById(R.id.first_name);
        LastName=(EditText)findViewById(R.id.last_name);
        Phone=(EditText)findViewById(R.id.admin_phone_no);
        Cnic=(EditText)findViewById(R.id.admin_cnic);
        Pass=(EditText)findViewById(R.id.password);
        btnSave=(RelativeLayout)findViewById(R.id.btnSave);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        final ProgressDialog pd = new ProgressDialog(ActivitySignup.this);
        pd.setMessage("Please wait");

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
                u.setFirstname(firstName);
                u.setLastname(lastName);
                u.setCnic(cnic);
                u.setPhone(phone);
                u.setPassword(pass);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new UserDao().insertUsers(u,ActivitySignup.this);
                        pd.cancel();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(s.toLowerCase().contains("true")) {
                                    AlertDialog.Builder ab = new AlertDialog.Builder(ActivitySignup.this);
                                    ab.setMessage("Registration Successful");
                                    ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }).show();
                                }else {
                                    AlertDialog.Builder ab = new AlertDialog.Builder(ActivitySignup.this);
                                    ab.setMessage("Error adding user");
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
