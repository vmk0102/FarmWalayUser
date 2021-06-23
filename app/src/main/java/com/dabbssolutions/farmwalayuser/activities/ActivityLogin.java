//package com.dabbssolutions.farmwalayuser.activities;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.dabbssolutions.farmwalayuser.R;
//
//public class ActivityLogin extends AppCompatActivity {
//    RelativeLayout btnLogin;
//    TextView requestPass,createaccount;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_login);
//        btnLogin=(RelativeLayout)findViewById(R.id.btnLogin);
//        requestPass=(TextView)findViewById(R.id.txtForgotPass);
//        createaccount=(TextView)findViewById(R.id.txtRegisterNow);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ActivityLogin.this,MainActivity.class));
//            }
//        });
//        requestPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ActivityLogin.this,ActivityForgotPass.class));
//            }
//        });
//        createaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ActivityLogin.this,ActivitySignup.class));
//            }
//        });
//    }
//}







package com.dabbssolutions.farmwalayuser.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dabbssolutions.farmwalayuser.activities.ActivitySignup;
import com.dabbssolutions.farmwalayuser.activities.MainActivity;
import com.dabbssolutions.farmwalayuser.dao.UserDao;
import com.dabbssolutions.farmwalayuser.model.users;
import com.dabbssolutions.farmwalayuser.dao.UserDao;

import com.dabbssolutions.farmwalayuser.R;
import com.google.gson.Gson;

public class ActivityLogin extends AppCompatActivity {
    RelativeLayout btnLogin;
    TextView requestPass,createaccount;
    EditText phone, pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setElevation(0);
        View view = getSupportActionBar().getCustomView();
        Toolbar toolbar=(Toolbar) view.getParent();
        toolbar.setContentInsetsAbsolute(0,0);
        btnLogin=(RelativeLayout)findViewById(R.id.btnLogin);
        requestPass=(TextView)findViewById(R.id.txtForgotPass);
        createaccount=(TextView)findViewById(R.id.txtRegisterNow);
        phone=(EditText)findViewById(R.id.txtPhoneNumber);
        pass=(EditText) findViewById(R.id.txtPassword);
        final ProgressDialog pd = new ProgressDialog(ActivityLogin.this);
        pd.setMessage("Please wait");

        final SharedPreferences sharedPreferences=getSharedPreferences("mypref", Context.MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd.show();
                final users  a = new users();
                a.setPhone(phone.getText().toString().trim());
                a.setPassword(pass.getText().toString().trim());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final String s = new UserDao().verifyUserLogin(a,ActivityLogin.this);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                users[] a = new Gson().fromJson(s,users[].class);
                                pd.cancel();
                                if(a.length>0) {
                                    sharedPreferences.edit().putString("uid",String.valueOf(a[0].getUid())).apply();
                                    sharedPreferences.edit().putString("userprofile",s).apply();

                                    startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                                    ActivityLogin.this.finish();
                                }else {
                                    Toast.makeText(ActivityLogin.this, "Invalid phone no. or password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();

                //startActivity(new Intent(ActivityLogin.this,MainActivity.class));
            }
        });
        requestPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ActivityLogin.this,ActivityForgotPass.class));
            }
        });
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this, ActivitySignup.class));
                finish();
            }
        });
    }
}
