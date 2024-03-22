package com.example.moneymind.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import android.view.Menu;
import android.view.MenuItem;
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
import com.example.moneymind.views.fragments.StatsFragment;
import com.example.moneymind.views.fragments.TransactionsFragment;
import com.example.moneymind.views.fragments.addtransactionfragment;
import com.google.android.material.navigation.NavigationBarView;
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

        SharedPreferences sh=getSharedPreferences("userDetails",MODE_PRIVATE);
        int user_id=sh.getInt("userId",-1);
        int account_id=sh.getInt("accountId",-1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.custom_green));
        }

        //This code snippet adds TransactionFragment class with this activity FrameLayout
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new TransactionsFragment());
        transaction.commit();



      binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
              FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

              if(Item.getItemId() == R.id.transactions){
                  getSupportFragmentManager().popBackStack();

              } else if (Item.getItemId() == R.id.stats) {
                  transaction.replace(R.id.content,new StatsFragment());
                  transaction.addToBackStack(null);

              }
              transaction.commit();

              return true;
          }
      });



    }

    public void getTransaction(){
        viewModel.getTransactions(calendar);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
