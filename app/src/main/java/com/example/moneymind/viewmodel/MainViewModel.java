package com.example.moneymind.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moneymind.MyDBHelper;
import com.example.moneymind.models.Transaction;
import com.example.moneymind.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainViewModel extends AndroidViewModel {

    MyDBHelper db = new MyDBHelper(this.getApplication());


    public MutableLiveData<ArrayList<Transaction>> transaction = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Transaction>> categorytransaction = new MutableLiveData<>();
    public MutableLiveData<Double> totalIncome = new MutableLiveData<>();
    public MutableLiveData<Double> totalExpense = new MutableLiveData<>();
    public MutableLiveData<Double> totalAccount = new MutableLiveData<>();

    Calendar calendar;


    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    SharedPreferences sh = getApplication().getSharedPreferences("userDetails", Context.MODE_PRIVATE);
    int user_id = sh.getInt("userId", -1);
    int account_id = sh.getInt("accountId", -1);


    public void getTransactionsChart(Calendar calendar, String Type) {
        this.calendar = calendar;
        ArrayList<Transaction> newTransactions = new ArrayList<>();


        if (Constants.SELECTED_TAB_STATS == Constants.DAILY) {


            newTransactions = db.getTransactionDetailsOfDayChart(calendar, Type, account_id);


        } else if (Constants.SELECTED_TAB_STATS == Constants.MONTHLY) {

            newTransactions = db.getTransactionDetailsOfMonthsChart(calendar, Type, account_id);

        }


        categorytransaction.setValue(newTransactions);
    }


    public void getTransactions(Calendar calendar) {
        this.calendar = calendar;
        ArrayList<Transaction> newTransactions = new ArrayList<>();
        double income = 0;
        double expense = 0;
        double total = 0;

        if (Constants.SELECTED_TAB == Constants.DAILY) {


            newTransactions = db.getTransactionDetailsForAccount(calendar, account_id);
            income = db.getTotalIncomeForDate(calendar, account_id);
            expense = db.getTotalExpenseForDate(calendar, account_id);
            total = db.getTotalAccountForDate(calendar, account_id);

        } else if (Constants.SELECTED_TAB == Constants.MONTHLY) {

            newTransactions = db.getTransactionDetailsOfMonths(calendar, account_id);
            income = db.getTotalIncomeForMonth(calendar, account_id);
            expense = db.getTotalExpenseForMonth(calendar, account_id);
            total = db.getTotalAccountForMonth(calendar, account_id);
        }


        totalAccount.setValue(total);
        totalExpense.setValue(expense);
        totalIncome.setValue(income);
        transaction.setValue(newTransactions);
    }

    public void addTransactions(Transaction transaction) {


        db.addtransaction(transaction);


    }

    public void deleteTransaction(Transaction transaction) {

        db.deleteTransaction(transaction);
        getTransactions(calendar);


    }

}
