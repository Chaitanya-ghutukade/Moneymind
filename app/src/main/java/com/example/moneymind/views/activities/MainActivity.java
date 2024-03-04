package com.example.moneymind.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;

import android.view.Menu;
import android.view.Window;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.moneymind.R;
import com.example.moneymind.adapters.TransactionAdapter;
import com.example.moneymind.databinding.ActivityMainBinding;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;
import com.example.moneymind.views.fragments.addtransactionfragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.dashboardToolbar);
        getSupportActionBar().setTitle("Dashboard");
        Constants.setCategory();

        calendar=Calendar.getInstance();
        updateDate();

        binding.nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE,1);
                updateDate();
            }

        });
        binding.previousDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.DATE,-1);
                updateDate();
            }

        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.custom_green));
        }

        binding.fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  new addtransactionfragment().show(getSupportFragmentManager(),null);
            }
        });


        ArrayList<Transaction> transactions=new ArrayList<>();
        transactions.add(new Transaction("INCOME","Business","some note here","Cash",new Date(),500,2));
        transactions.add(new Transaction("EXPENSE","Salary","some note here","Cash",new Date(),500,2));
        transactions.add(new Transaction("INCOME","Business","some note here","Cash",new Date(),500,2));
        transactions.add(new Transaction("EXPENSE","Business","some note here","Cash",new Date(),500,2));
        transactions.add(new Transaction("INCOME","Business","some note here","Cash",new Date(),500,2));

        TransactionAdapter transactionAdapter=new TransactionAdapter(this,transactions);
        binding.transactionlist.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionlist.setAdapter(transactionAdapter);

    }
     void updateDate(){

         binding.date.setText(Helper.format_date(calendar.getTime()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
