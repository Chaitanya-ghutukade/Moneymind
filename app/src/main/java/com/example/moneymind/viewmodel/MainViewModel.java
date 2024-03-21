package com.example.moneymind.viewmodel;

import android.app.Application;

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
    public MutableLiveData<Double> totalIncome = new MutableLiveData<>();
    public MutableLiveData<Double> totalExpense = new MutableLiveData<>();
    public MutableLiveData<Double> totalAccount = new MutableLiveData<>();

    Calendar calendar;


    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public void getTransactions(Calendar calendar) {
        this.calendar = calendar;
        ArrayList<Transaction> newTransactions = new ArrayList<>();
        double income = 0;
        double expense = 0;
        double total = 0;

        if (Constants.SELECTED_TAB == Constants.DAILY) {


            newTransactions = db.getTransactionDetailsForAccount(calendar);
            income = db.getTotalIncomeForDate(calendar);
            expense = db.getTotalExpenseForDate(calendar);
            total = db.getTotalAccountForDate(calendar);

        }else if(Constants.SELECTED_TAB==Constants.MONTHLY){

            newTransactions=db.getTransactionDetailsOfMonths(calendar);
            income=db.getTotalIncomeForMonth(calendar);
            expense=db.getTotalExpenseForMonth(calendar);
            total=db.getTotalAccountForMonth(calendar);
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
