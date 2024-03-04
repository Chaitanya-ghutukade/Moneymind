package com.example.moneymind.utils;

import com.example.moneymind.R;
import com.example.moneymind.models.Account;
import com.example.moneymind.models.Category;

import java.util.ArrayList;

public class Constants {

    public static String INCOME = "INCOME";
    public static String EXPENSE = "EXPENSE";

    public static ArrayList<Category> categories;

    public static void setCategory() {

        categories = new ArrayList<>();

        categories.add(new Category("Business", R.drawable.business, R.color.category1));
        categories.add(new Category("Loan", R.drawable.loan, R.color.category2));
        categories.add(new Category("Rent", R.drawable.rent, R.color.category3));
        categories.add(new Category("Investment", R.drawable.investment, R.color.category4));
        categories.add(new Category("Salary", R.drawable.salary, R.color.category5));
        categories.add(new Category("Other", R.drawable.other_category, R.color.category6));
        categories.add(new Category("Other1", R.drawable.other_category, R.color.category6));
    }

    public static Category getCategoryDetails(String categoryName) {
        for (Category cat : categories) {
            if (cat.getCategoryName().equals(categoryName)) {
                return cat;
            }

        }
      return  null;
    }

    public static int getAccountColors(String accountName){
        switch (accountName){
            case "Cash":
                return  R.color.cash_color;
            case "Paytm":
                return  R.color.paytm_color;
            case "Card":
                return R.color.card_color;
            case "Bank":
                return R.color.bank_color;
            default:
                return R.color.default_color;
        }



    }
}
