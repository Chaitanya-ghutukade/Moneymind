package com.example.moneymind.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moneymind.MyDBHelper;
import com.example.moneymind.models.Transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainViewModel extends AndroidViewModel {

    MyDBHelper db = new MyDBHelper(this.getApplication());


    public MutableLiveData<ArrayList<Transaction>> transaction = new MutableLiveData<>();
    public MutableLiveData<Double> totalIncome = new MutableLiveData<>();
    public MutableLiveData<Double> totalExpense = new MutableLiveData<>();
    public MutableLiveData<Double> totalAccount= new MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
    }



    public void getTransactions(Calendar calendar) {
        ArrayList<Transaction> newTransactions = new ArrayList<>();
        newTransactions = db.getTransactionDetailsForAccount(calendar);
        double income=db.getTotalIncomeForDate(calendar);
        double expense=db.getTotalExpenseForDate(calendar);
        double total=db.getTotalAccountForDate(calendar);
        totalAccount.setValue(total);
        totalExpense.setValue(expense);
        totalIncome.setValue(income);
        transaction.setValue(newTransactions);
    }

    public void addTransactions(Transaction transaction) {

        MyDBHelper db = new MyDBHelper(this.getApplication());
        db.addtransaction(transaction);


    }
}
