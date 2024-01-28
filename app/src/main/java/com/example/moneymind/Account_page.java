package com.example.moneymind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Account_page extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> accountNames;
    private static final int MAX_ACCOUNTS = 5;
    private int accountCounter = 0;
    int user_id;

    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);


        Intent intent = getIntent();
        String stored_user_name = intent.getStringExtra("username");
        MyDBHelper db2 = new MyDBHelper(this);
        user_id = db2.getuserid(stored_user_name);

        listView = findViewById(R.id.listview);
        MyDBHelper dbHelper = new MyDBHelper(this);
        accountNames =  dbHelper.fetchAccountNames(user_id);

        adapter = new ArrayAdapter<>(this,R.layout.list_item_account, accountNames);
        listView.setAdapter(adapter);

        // Set OnClickListener for each item in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedAccount = adapter.getItem(position);
                // Handle button click here
                Toast.makeText(Account_page.this, "Selected Account: " + selectedAccount, Toast.LENGTH_SHORT).show();
            }
        });





        Button createAccountButton = findViewById(R.id.create_btn);
        AlertDialog.Builder builder = new AlertDialog.Builder(Account_page.this);
        //Inflate add_accounts view
        View view = getLayoutInflater().inflate(R.layout.add_accounts, null);
        EditText account_name, inital_value;
        Button create, cancel;

        create = view.findViewById(R.id.create_account);
        cancel = view.findViewById(R.id.cancel_account);
        //set this view to dialog
        builder.setView(view);
        //create dialog
        dialog = builder.create();
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();

            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText accountNameEditText = view.findViewById(R.id.account_name_edittext);
                String newAccountName = accountNameEditText.getText().toString();
                if (!newAccountName.isEmpty()) {
                    // Update database

                    db2.createAccount(user_id, newAccountName);

                    // Refresh account names list
                    accountNames.clear();
                    accountNames.addAll(db2.fetchAccountNames(user_id));
                    adapter.notifyDataSetChanged();

                    accountNameEditText.setText("");

                    dialog.dismiss();
                }
            }

        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

}




