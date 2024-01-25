package com.example.moneymind;

import androidx.lifecycle.ViewModel;

public class Sharemodelview extends ViewModel {
    private String Usernamedata;

    public String getUsernamedata() {
        return Usernamedata;
    }

    public void setUsernamedata(String data) {
        this.Usernamedata = data;
    }


}
