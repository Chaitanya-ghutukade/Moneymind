package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Button reg;
        TextView signin;
        reg=findViewById(R.id.buttonRegister);
        signin=findViewById(R.id.textViewSignIn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(Registration.this,"registered successfully",Toast.LENGTH_SHORT).show();
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