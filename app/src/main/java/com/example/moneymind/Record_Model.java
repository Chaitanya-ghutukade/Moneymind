package com.example.moneymind;

public class Record_Model {

    int img;
    String record_type,acc_name,payment_type,amount,date;

     public Record_Model(int img,String record_type,String acc_name,String payment_type,String amount,String date){
        this.img=img;
        this.record_type=record_type;
        this.acc_name=acc_name;
        this.payment_type=payment_type;
        this.amount=amount;
        this.date=date;
    }
}
