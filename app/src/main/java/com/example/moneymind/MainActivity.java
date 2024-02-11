package com.example.moneymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.view.Window;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab_btn);


        //rough code to check working
        TextView aname=findViewById(R.id.aname);
        TextView uname=findViewById(R.id.uname);
        Intent intent=getIntent();
        String A_name=String.valueOf(intent.getIntExtra("Account_id",0));
        String U_name=String.valueOf(intent.getIntExtra("User_id",0));
        String stored_user_name=intent.getStringExtra("User_name");
        aname.setText(A_name);
        uname.setText(U_name);




      //code to set fullname and email on navigation drawer

      // Inside your MainActivity or wherever you have access to the NavigationView
        NavigationView navigationView = findViewById(R.id.navigationview);
        View headerView = navigationView.getHeaderView(0); // Index 0 represents the first header

        TextView username = headerView.findViewById(R.id.User_name);
        TextView useremail = headerView.findViewById(R.id.User_email);

// Assume you have a variable containing the username from the login

        //fetching data from database
       MyDBHelper db1=new MyDBHelper(this);
        Userdata userdata=db1.getuserdetails(stored_user_name);
        if(userdata != null){
        String u_name=userdata.user_name;
        String u_email=userdata.user_email;
            //set username and email
            username.setText(u_name);
            useremail.setText(u_email);
        }

        //get the acc_id and user_id  and fetch the details from database and set on home screen

        Intent R_intent = new Intent(MainActivity.this, Records.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        //setting status bar color same as toolbar color

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.home_toolbar_color));
        }






        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.records_item) {

                    startActivity(R_intent);

                }
                else if (id == R.id.account_item) {
                    Intent intent=new Intent(MainActivity.this,Account_page.class);
                    startActivity(intent);
                }else if (id == R.id.goal_item) {
                    Toast.makeText(MainActivity.this, "no goals found", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.budget_item) {
                    Toast.makeText(MainActivity.this, "budget not fixed", Toast.LENGTH_SHORT).show();

                }  else if (id == R.id.login_item) {
                   Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                   startActivity(intent);

                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }


        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent add_records = new Intent(MainActivity.this, Add_Records.class);
                startActivity(add_records);
            }
        });


    }



}