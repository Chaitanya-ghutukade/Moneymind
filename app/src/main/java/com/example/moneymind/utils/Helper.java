package com.example.moneymind.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    public static String format_date(Date date){

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd MMMM,YY");
        return dateFormat.format(date);
    }


}
