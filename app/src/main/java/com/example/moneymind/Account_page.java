package com.example.moneymind;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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

        accountNames = db2.fetchAccountNames(user_id);

        adapter = new ArrayAdapter<>(this, R.layout.list_item_account, accountNames);
        listView.setAdapter(adapter);

        // Set OnClickListener for each item in the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedAccount = adapter.getItem(position);
                int Account_id = db2.get_account_id(selectedAccount, user_id);
                Intent intent = new Intent(Account_page.this, MainActivity.class);
                intent.putExtra("Account_id", Account_id);
                intent.putExtra("User_id", user_id);
                intent.putExtra("User_name", stored_user_name);
                startActivity(intent);
                // Handle button click here
                Toast.makeText(Account_page.this, "Selected Account: " + selectedAccount, Toast.LENGTH_SHORT).show();
            }
        });


        // Long press listener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showPopupMenu(view, position);
                return true;
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

                Boolean checkaccount = db2.checkAccountname(newAccountName);
                if (checkaccount == true) {

                    Toast.makeText(Account_page.this, "Account with this name is already exists", Toast.LENGTH_SHORT).show();
                } else {

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
            }

        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    private void showPopupMenu(View view, final int position) {


        PopupMenu popupMenu = new PopupMenu(this, view, Gravity.START, 0, R.style.PopupMenuStyle);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_edit) {
                    // Handle edit action
                    editItem(position);
                    return true;
                } else if (itemId == R.id.menu_delete) {
                    // Handle delete action
                    deleteItem(position);
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }

    private void editItem(int position) {
        MyDBHelper db = new MyDBHelper(this);
        String Acc_Name = listView.getItemAtPosition(position).toString();

        int Account_id = db.get_account_id(Acc_Name, user_id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Text");
        final EditText input = new EditText(this);
        input.setText(accountNames.get(position));
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText = input.getText().toString();
                Boolean checkaccount = db.checkAccountname(newText);
                if (checkaccount == true) {

                    Toast.makeText(Account_page.this, "Account with this name is already exists", Toast.LENGTH_SHORT).show();

                }
                else {
                    Boolean accountedited = db.edit_account(Account_id, user_id, newText);
                    //Refresh account name list
                    accountNames.clear();
                    accountNames.addAll(db.fetchAccountNames(user_id));
                    adapter.notifyDataSetChanged();
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteItem(int position) {
        // Implement delete functionality
        MyDBHelper db = new MyDBHelper(this);
        String Acc_Name = listView.getItemAtPosition(position).toString();
        Boolean accountdeleted = db.delete_account(Acc_Name, user_id);
        if (accountdeleted == true) {
            Toast.makeText(this, "Account deleted:" + Acc_Name, Toast.LENGTH_SHORT).show();
        }


        //Refresh account name list
        accountNames.clear();
        accountNames.addAll(db.fetchAccountNames(user_id));
        adapter.notifyDataSetChanged();

    }


}




