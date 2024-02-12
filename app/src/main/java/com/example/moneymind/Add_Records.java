package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Records extends AppCompatActivity {
    AppCompatButton btnIncome;
    AppCompatButton btnExpense;
    AppCompatButton btnTransfer;
    TextView txtIcon;

    Toolbar toolbar;
    ImageView close;
    ImageView check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        btnIncome = findViewById(R.id.btn_income);
        btnExpense = findViewById(R.id.btn_expense);
        btnTransfer = findViewById(R.id.btn_transfer);
        txtIcon = findViewById(R.id.txt_icon);
        toolbar = findViewById(R.id.Add_Records_toolbar);
        close = findViewById(R.id.close_icon);
        check = findViewById(R.id.check_icon);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.add_record_toolbar_color));
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        setDefaultSelections();
        btnIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(btnIncome, "+", R.color.green);
            }
        });

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(btnExpense, "-", R.color.red);
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(btnTransfer, "->", R.color.blue);
            }
        });
    }

    private void setDefaultSelections() {
        // Set Income button as default
        btnIncome.setSelected(true);
        btnExpense.setSelected(false);
        btnTransfer.setSelected(false);
        // Show plus icon
        txtIcon.setText("+");
        // Set text color
        txtIcon.setTextColor(ContextCompat.getColor(Add_Records.this, R.color.green));
    }

    private void handleButtonClick(AppCompatButton button, String iconText, int textColor) {
        // Reset all buttons
        btnIncome.setSelected(false);
        btnExpense.setSelected(false);
        btnTransfer.setSelected(false);
        // Set selected button
        button.setSelected(true);
        // Set icon text and color
        txtIcon.setText(iconText);
        txtIcon.setTextColor(ContextCompat.getColor(Add_Records.this, textColor));
    }
}
