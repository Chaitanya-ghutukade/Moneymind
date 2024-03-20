package com.example.moneymind.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymind.MyDBHelper;
import com.example.moneymind.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        EditText user = findViewById(R.id.editTextUsername);
        EditText pass = findViewById(R.id.editTextPassword);
        Button loginbtn;
        TextView signupbtn;
        signupbtn = findViewById(R.id.textViewSignUp);
        loginbtn = findViewById(R.id.loginButton);


        MyDBHelper DB = new MyDBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = user.getText().toString();
                String Pass = pass.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("User_name", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", User);
                editor.apply();





                if (User.equals("") || Pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuser = DB.checkUsername(User);
                    if (checkuser == false) {
                        Toast.makeText(LoginActivity.this, "User does not exist please register", Toast.LENGTH_SHORT).show();
                    } else {

                        Boolean checkusernamepassword = DB.checkusernamepassword(User, Pass);
                        if (checkusernamepassword == true) {

                            SharedPreferences prefs=getSharedPreferences("userlogin",MODE_PRIVATE);
                            SharedPreferences.Editor edit=prefs.edit();
                            edit.putBoolean("IsLoggedIn",true);
                            edit.apply();


                            Intent account_page = new Intent(LoginActivity.this, MainActivity.class);

                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                            startActivity(account_page);


                        } else {
                            Toast.makeText(LoginActivity.this, "enter correct password", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg_page = new Intent(LoginActivity.this, Registration.class);
                startActivity(Reg_page);
            }
        });
    }
}