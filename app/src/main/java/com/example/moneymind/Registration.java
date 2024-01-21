package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button reg;
        TextView signin;
        EditText name;
        EditText email;
        EditText username;
        EditText password;


        reg=findViewById(R.id.buttonRegister);
        signin=findViewById(R.id.textViewSignIn);
        name=findViewById(R.id.editTextFullName);
        email=findViewById(R.id.editTextEmail);
        username=findViewById(R.id.editTextUsername);
        password=findViewById(R.id.editTextPassword);

        MyDBHelper dbHelper=new MyDBHelper(this);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=name.getText().toString();
                String Email=email.getText().toString();
                String Username=username.getText().toString();
                String Password=password.getText().toString();

                dbHelper.register_user(Name,Email,Username,Password);
                Toast.makeText(Registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signin_to_login = new Intent(Registration.this,LoginActivity.class);
                startActivity(signin_to_login);
            }
        });

    }
}