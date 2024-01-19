package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginbtn;
        TextView signupbtn;
        signupbtn=findViewById(R.id.textViewSignUp);
        loginbtn=findViewById(R.id.loginButton);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainactivity_page=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(mainactivity_page);
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg_page=new Intent(LoginActivity.this,Registration.class);
                startActivity(Reg_page);
            }
        });
    }
}