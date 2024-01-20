package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Records extends AppCompatActivity {

Toolbar Records_toolbar;
RecyclerView recyclerView;
ArrayList<Record_Model> arr_records=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Records_toolbar=findViewById(R.id.Records_toolbar);
        recyclerView=findViewById(R.id.recyclerview);
        setSupportActionBar(Records_toolbar);
        getSupportActionBar().setTitle("Records");


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));
        arr_records.add(new Record_Model(R.drawable.baseline_food_bank_24,"food and drink","cash","gpay","100rs","today"));


        RecyclerRecordsAdapter adapter=new RecyclerRecordsAdapter(this,arr_records);
        recyclerView.setAdapter(adapter);

    }
}