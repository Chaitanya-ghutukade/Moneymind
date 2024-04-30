package com.example.moneymind.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Build;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.moneymind.BudgetFragment;
import com.example.moneymind.R;
import com.example.moneymind.databinding.ActivityMainBinding;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.viewmodel.MainViewModel;
import com.example.moneymind.views.fragments.StatsFragment;
import com.example.moneymind.views.fragments.TransactionsFragment;
import com.example.moneymind.views.fragments.UserFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Calendar;


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
        getSupportActionBar().setTitle("MoneyMind");
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

              }else if(Item.getItemId()==R.id.user){
               transaction.replace(R.id.content,new UserFragment());
               transaction.addToBackStack(null);
              }else if(Item.getItemId()==R.id.budget_goal){
                  transaction.replace(R.id.content,new BudgetFragment());
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
