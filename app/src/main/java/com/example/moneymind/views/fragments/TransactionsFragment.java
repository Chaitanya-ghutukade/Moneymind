package com.example.moneymind.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneymind.R;
import com.example.moneymind.adapters.TransactionAdapter;
import com.example.moneymind.databinding.FragmentTransactionsBinding;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;
import com.example.moneymind.viewmodel.MainViewModel;
import com.example.moneymind.views.activities.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;


public class TransactionsFragment extends Fragment {


    public TransactionsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentTransactionsBinding binding;
    Calendar calendar;

    /*
        0=Daily
        1=Monthly
        2=Calender
        3=Summary
        4=Notes
     */


    public MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTransactionsBinding.inflate(inflater);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        calendar = Calendar.getInstance();
        updateDate();
        binding.nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.SELECTED_TAB == Constants.DAILY) {
                    calendar.add(Calendar.DATE, 1);
                } else if (Constants.SELECTED_TAB == Constants.MONTHLY) {
                    calendar.add(Calendar.MONTH, 1);
                }
                updateDate();
            }

        });
        binding.previousDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.SELECTED_TAB == Constants.DAILY) {
                    calendar.add(Calendar.DATE, -1);
                } else if (Constants.SELECTED_TAB == Constants.MONTHLY) {
                    calendar.add(Calendar.MONTH, -1);
                }
                updateDate();
            }

        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().equals("Daily")) {
                    Constants.SELECTED_TAB = Constants.DAILY;
                    updateDate();
                } else if (tab.getText().equals("Monthly")) {
                    Constants.SELECTED_TAB = Constants.MONTHLY;
                    updateDate();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });


        binding.fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new addtransactionfragment().show(getParentFragmentManager(), null);

            }
        });


        binding.transactionlist.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.transaction.observe(getViewLifecycleOwner(), new Observer<ArrayList<Transaction>>() {
            @Override
            public void onChanged(ArrayList<Transaction> transactions) {


                TransactionAdapter transactionAdapter = new TransactionAdapter(getActivity(), transactions);

                binding.transactionlist.setAdapter(transactionAdapter);
                if (transactions.size() > 0) {
                    binding.emptyState.setVisibility(View.GONE);

                } else {
                    binding.emptyState.setVisibility(View.VISIBLE);
                }

            }
        });

        viewModel.totalIncome.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.incomeLabel.setText(String.valueOf(aDouble));
            }
        });

        viewModel.totalExpense.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.expenseLabel.setText(String.valueOf(aDouble));
            }
        });
        viewModel.totalAccount.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                binding.totalLabel.setText(String.valueOf(aDouble));
            }
        });
        viewModel.getTransactions(calendar);

        return binding.getRoot();
    }

    void updateDate() {
        if (Constants.SELECTED_TAB == Constants.DAILY) {
            binding.date.setText(Helper.format_date(calendar.getTime()));
        } else if (Constants.SELECTED_TAB == Constants.MONTHLY) {
            binding.date.setText(Helper.format_date_month(calendar.getTime()));
        }
        viewModel.getTransactions(calendar);
    }
}