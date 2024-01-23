package com.example.moneymind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        drawerLayout=findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationview);
        toolbar=findViewById(R.id.toolbar);
        fab=findViewById(R.id.fab_btn);


        Intent R_intent = new Intent(MainActivity.this,Records.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.records_item){

                   startActivity(R_intent);

                } else if (id==R.id.goal_item) {
                    Toast.makeText(MainActivity.this,"no goals found",Toast.LENGTH_SHORT).show();

                } else if (id==R.id.budget_item) {
                    Toast.makeText(MainActivity.this,"budget not fixed",Toast.LENGTH_SHORT).show();

                }

                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }



        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent add_records=new Intent(MainActivity.this,Add_Records.class);
                startActivity(add_records);
            }
        });



    }


}