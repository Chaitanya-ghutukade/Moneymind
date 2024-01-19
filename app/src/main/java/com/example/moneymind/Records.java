package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Records extends AppCompatActivity {
ListView listView1;
Toolbar Records_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Records_toolbar=findViewById(R.id.Records_toolbar);
        listView1=findViewById(R.id.listview);


        String[] countries ={"india","us","canada","russia","china"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,countries);
        setSupportActionBar(Records_toolbar);
        getSupportActionBar().setTitle("Records");
        listView1.setAdapter(adapter);




    }
}