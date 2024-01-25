package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class Account_page extends AppCompatActivity {
    private static final int MAX_ACCOUNTS = 5;
    private int accountCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);

        LinearLayout linearLayoutAccounts = findViewById(R.id.buttonContainer);

        Button createAccountButton = findViewById(R.id.create_btn);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (accountCounter < MAX_ACCOUNTS) {

                    accountCounter++;


                    Button newAccountButton = new Button(Account_page.this);
                    newAccountButton.setText("Account " + accountCounter);
                    newAccountButton.setTextColor(Color.parseColor("#000000"));

                    GradientDrawable gradientDrawable = new GradientDrawable();
                    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                    gradientDrawable.setColor(Color.parseColor("#ABE665"));
                    gradientDrawable.setCornerRadius(20);
                    newAccountButton.setBackground(gradientDrawable);

                    LayoutParams layoutParams = new LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.WRAP_CONTENT
                    );
                    layoutParams.setMargins(0, 0, 0, 25);
                    layoutParams.width = 500;
                    newAccountButton.setLayoutParams(layoutParams);


                    linearLayoutAccounts.addView(newAccountButton);


                    newAccountButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // handleAccountButtonClick(newAccountButton.getText().toString());
                            Toast.makeText(Account_page.this, "button clicked", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });
    }

    private void handleAccountButtonClick(String accountName) {
        // Navigate to the specific account page or perform actions based on the account clicked
        // You can start a new activity, fragment, or update the UI accordingly
    }


}
