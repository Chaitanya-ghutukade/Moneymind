package com.example.moneymind.views.activities;// SplashActivity.java

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moneymind.R;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs= getSharedPreferences("userlogin",MODE_PRIVATE);
                Boolean IsLoggedIn=prefs.getBoolean("IsLoggedIn",false);
                if(IsLoggedIn){

                    startActivity(new Intent(Splashscreen.this, Account_page.class));
                    finish();
                }
                else{
                    startActivity(new Intent(Splashscreen.this, LoginActivity.class));
                }



            }
        }, 2000); // 2000 milliseconds (2 seconds) delay
    }
}
