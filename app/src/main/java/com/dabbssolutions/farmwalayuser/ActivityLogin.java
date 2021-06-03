package com.dabbssolutions.farmwalayuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLogin extends AppCompatActivity {
    RelativeLayout btnLogin;
    TextView requestPass,createaccount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        btnLogin=(RelativeLayout)findViewById(R.id.btnLogin);
        requestPass=(TextView)findViewById(R.id.txtForgotPass);
        createaccount=(TextView)findViewById(R.id.txtRegisterNow);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,MainActivity.class));
            }
        });
        requestPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivityForgotPass.class));
            }
        });
        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivitySignup.class));
            }
        });
    }
}
