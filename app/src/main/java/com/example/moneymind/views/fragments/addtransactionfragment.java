package com.example.moneymind.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.moneymind.R;
import com.example.moneymind.adapters.Account_Adapter;
import com.example.moneymind.adapters.Category_Adapter;
import com.example.moneymind.databinding.FragmentAddtransactionfragmentBinding;
import com.example.moneymind.databinding.ListDialogBinding;
import com.example.moneymind.models.Account;
import com.example.moneymind.models.Category;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class addtransactionfragment extends BottomSheetDialogFragment {


    public addtransactionfragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    FragmentAddtransactionfragmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddtransactionfragmentBinding.inflate(inflater);

        binding.incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
                binding.incomeBtn.setTextColor(getContext().getColor(R.color.green));
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
                binding.expenseBtn.setTextColor(getContext().getColor(R.color.textcolor));
            }
        });

        binding.expenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
                binding.expenseBtn.setTextColor(getContext().getColor(R.color.red));
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
                binding.incomeBtn.setTextColor(getContext().getColor(R.color.textcolor));
            }
        });

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datepicker = new DatePickerDialog(getContext());
                datepicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(calendar.DAY_OF_MONTH, datepicker.getDatePicker().getDayOfMonth());
                        calendar.set(calendar.MONTH, datepicker.getDatePicker().getMonth());
                        calendar.set(calendar.YEAR, datepicker.getDatePicker().getYear());


                        String showdate = Helper.format_date(calendar.getTime());

                        binding.date.setText(showdate);
                    }
                });


                datepicker.show();
            }
        });

        binding.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListDialogBinding dialogbinding = ListDialogBinding.inflate(inflater);
                AlertDialog categorydialog = new AlertDialog.Builder(getContext()).create();
                categorydialog.setView(dialogbinding.getRoot());


                Category_Adapter categoryAdapter = new Category_Adapter(getContext(), Constants.categories, new Category_Adapter.CategoryClickedListner() {
                    @Override
                    public void onCategoryClicked(Category category) {
                        binding.category.setText(category.getCategoryName());
                        categorydialog.dismiss();
                    }
                });
                dialogbinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                dialogbinding.recyclerView.setAdapter(categoryAdapter);

                categorydialog.show();

            }
        });

        binding.account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDialogBinding dialogbinding = ListDialogBinding.inflate(inflater);
                AlertDialog accountdialog = new AlertDialog.Builder(getContext()).create();
                accountdialog.setView(dialogbinding.getRoot());

                ArrayList<Account> accountArrayList = new ArrayList<>();
                accountArrayList.add(new Account("Bank", 1000));
                accountArrayList.add(new Account("Cash", 1000));
                accountArrayList.add(new Account("paytm", 1000));
                accountArrayList.add(new Account("Card", 1000));


                Account_Adapter adapter = new Account_Adapter(getContext(), accountArrayList, new Account_Adapter.AccountClickListner() {
                    @Override
                    public void onAccountClick(Account account) {
                        binding.account.setText(account.getAccountName());
                        accountdialog.dismiss();
                    }
                });
                dialogbinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                dialogbinding.recyclerView.setAdapter(adapter);

                accountdialog.show();

            }
        });

        binding.saveTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return binding.getRoot();
    }
}