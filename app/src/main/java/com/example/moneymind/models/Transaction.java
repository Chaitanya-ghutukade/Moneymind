package com.example.moneymind.models;

import java.util.Date;

public class Transaction {

    private String type,category,note,account;
    private Date date;
    private double amount;
    private long id;

    private long acc_id;

    public Transaction() {
    }

    public Transaction(String type, String category, String note, String account, Date date, double amount, long id) {
        this.type = type;
        this.category = category;
        this.note = note;
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.id = id;
        this.acc_id=acc_id;
    }


    public long getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(long acc_id) {
        this.acc_id = acc_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
