package com.example.moneymind.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import android.view.Menu;
import android.view.Window;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.moneymind.MyDBHelper;
import com.example.moneymind.R;
import com.example.moneymind.adapters.TransactionAdapter;
import com.example.moneymind.databinding.ActivityMainBinding;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;
import com.example.moneymind.viewmodel.MainViewModel;
import com.example.moneymind.views.fragments.addtransactionfragment;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Callable;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Calendar calendar;

    /*
        0=Daily
        1=Monthly
        2=Calender
        3=Summary
        4=Notes
     */

    int selectedTab=0;

     public MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel=new ViewModelProvider(this).get(MainViewModel.class);
        setSupportActionBar(binding.dashboardToolbar);
        getSupportActionBar().setTitle("Dashboard");
        Constants.setCategory();
        calendar=Calendar.getInstance();
        updateDate();

        SharedPreferences sh=getSharedPreferences("userDetails",MODE_PRIVATE);
        int user_id=sh.getInt("userId",-1);
        int account_id=sh.getInt("accountId",-1);




        binding.nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Constants.SELECTED_TAB ==Constants.DAILY ) {
                    calendar.add(Calendar.DATE, 1);
                }else if(Constants.SELECTED_TAB ==Constants.MONTHLY){
                    calendar.add(Calendar.MONTH,1);
                }
                updateDate();
            }

        });
        binding.previousDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Constants.SELECTED_TAB == Constants.DAILY) {
                    calendar.add(Calendar.DATE, -1);
                }else if( Constants.SELECTED_TAB == Constants.MONTHLY){
                    calendar.add(Calendar.MONTH,-1);
                }
                updateDate();
            }

        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getText().equals("Daily")){
                    Constants.SELECTED_TAB=Constants.DAILY;
                    updateDate();
                }else if(tab.getText().equals("Monthly")){
                   Constants.SELECTED_TAB=Constants.MONTHLY;
                    updateDate();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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




        binding.transactionlist.setLayoutManager(new LinearLayoutManager(this));

        viewModel.transaction.observe(this,new Observer<ArrayList<Transaction>>(){
            @Override
            public void onChanged(ArrayList<Transaction> transactions) {


                TransactionAdapter transactionAdapter=new TransactionAdapter(MainActivity.this,transactions);

                binding.transactionlist.setAdapter(transactionAdapter);
                if(transactions.size() > 0){
                    binding.emptyState.setVisibility(View.GONE);

                }else{
                    binding.emptyState.setVisibility(View.VISIBLE);
                }

            }
        });

        viewModel.totalIncome.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.incomeLabel.setText(String.valueOf(aDouble));
            }
        });

        viewModel.totalExpense.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.expenseLabel.setText(String.valueOf(aDouble));
            }
        });
        viewModel.totalAccount.observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.totalLabel.setText(String.valueOf(aDouble));
            }
        });
        viewModel.getTransactions(calendar);




    }

    public void getTransaction(){
        viewModel.getTransactions(calendar);
    }
     void updateDate(){
        if( Constants.SELECTED_TAB ==Constants.DAILY) {
            binding.date.setText(Helper.format_date(calendar.getTime()));
        }else if(Constants.SELECTED_TAB ==Constants.MONTHLY){
            binding.date.setText(Helper.format_date_month(calendar.getTime()));
        }
         viewModel.getTransactions(calendar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
