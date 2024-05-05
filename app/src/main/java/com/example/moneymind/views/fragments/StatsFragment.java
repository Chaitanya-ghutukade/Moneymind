package com.example.moneymind.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;
import com.example.moneymind.R;
import com.example.moneymind.databinding.FragmentStatsBinding;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;
import com.example.moneymind.utils.Helper;
import com.example.moneymind.viewmodel.MainViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatsFragment extends Fragment {


    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    FragmentStatsBinding binding;
    Calendar calendar;

    /*
        0=Daily
        1=Monthly

     */


    public MainViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStatsBinding.inflate(inflater);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        calendar = Calendar.getInstance();
        updateDate();


        binding.incomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
                binding.incomeBtn.setTextColor(getContext().getColor(R.color.green));
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
                binding.expenseBtn.setTextColor(getContext().getColor(R.color.textcolor));
                Constants.SELECTED_STATS_TYPE = Constants.INCOME;
                updateDate();
            }
        });

        binding.expenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.expenseBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
                binding.expenseBtn.setTextColor(getContext().getColor(R.color.red));
                binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
                binding.incomeBtn.setTextColor(getContext().getColor(R.color.textcolor));
                Constants.SELECTED_STATS_TYPE = Constants.EXPENSE;
                updateDate();
            }
        });
        binding.nextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.SELECTED_TAB_STATS == Constants.DAILY) {
                    calendar.add(Calendar.DATE, 1);
                } else if (Constants.SELECTED_TAB_STATS == Constants.MONTHLY) {
                    calendar.add(Calendar.MONTH, 1);
                }
                updateDate();
            }

        });
        binding.previousDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.SELECTED_TAB_STATS == Constants.DAILY) {
                    calendar.add(Calendar.DATE, -1);
                } else if (Constants.SELECTED_TAB_STATS == Constants.MONTHLY) {
                    calendar.add(Calendar.MONTH, -1);
                }
                updateDate();
            }

        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getText().equals("Daily")) {
                    Constants.SELECTED_TAB_STATS = Constants.DAILY;
                    updateDate();
                } else if (tab.getText().equals("Monthly")) {
                    Constants.SELECTED_TAB_STATS = Constants.MONTHLY;
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

        Pie pie = AnyChart.pie();

        viewModel.categorytransaction.observe(getViewLifecycleOwner(), new Observer<ArrayList<Transaction>>() {
            @Override
            public void onChanged(ArrayList<Transaction> transactions) {
                if (transactions.size() > 0) {

                    binding.emptyState.setVisibility(View.GONE);
                    binding.anyChart.setVisibility(View.VISIBLE);

                    List<DataEntry> data = new ArrayList<>();

                    Map<String, Double> categoryMap = new HashMap<>();

                    for (Transaction transaction : transactions) {
                        String category = transaction.getCategory();
                        double amount = transaction.getAmount();

                        if (categoryMap.containsKey(category)) {
                            double currentTotal = categoryMap.get(category).doubleValue();
                            currentTotal += Math.abs(amount);

                            categoryMap.put(category, currentTotal);
                        } else {
                            categoryMap.put(category, Math.abs(amount));
                        }
                    }

                    for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
                        data.add(new ValueDataEntry(entry.getKey(), entry.getValue()));
                    }
                    pie.data(data);

                } else {
                    binding.emptyState.setVisibility(View.VISIBLE);
                    binding.anyChart.setVisibility(View.GONE);
                }


            }


        });

        viewModel.getTransactionsChart(calendar, Constants.SELECTED_STATS_TYPE);



       /* pie.title("Fruits imported in 2015 (in kg)");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Retail channels")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("center-bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);*/

        binding.anyChart.setChart(pie);


        return binding.getRoot();
    }

    void updateDate() {
        if (Constants.SELECTED_TAB_STATS == Constants.DAILY) {
            binding.date.setText(Helper.format_date(calendar.getTime()));
        } else if (Constants.SELECTED_TAB_STATS == Constants.MONTHLY) {
            binding.date.setText(Helper.format_date_month(calendar.getTime()));
        }
        viewModel.getTransactionsChart(calendar, Constants.SELECTED_STATS_TYPE);
    }
}