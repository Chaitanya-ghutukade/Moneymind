package com.example.moneymind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Records extends AppCompatActivity {

    Toolbar Records_toolbar;
    RecyclerView recyclerView;
    ArrayList<Record_Model> arr_records = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Records_toolbar = findViewById(R.id.Records_toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        setSupportActionBar(Records_toolbar);
        getSupportActionBar().setTitle("Records");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.record_toolbar_color));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "100rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "110rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "200rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "150rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "189rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "135rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "102rs", "today"));
        arr_records.add(new Record_Model(R.drawable.tools, "food and drink", "cash", "gpay", "12rs", "today"));


        RecyclerRecordsAdapter adapter = new RecyclerRecordsAdapter(this, arr_records);
        recyclerView.setAdapter(adapter);

    }
}