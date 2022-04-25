package com.example.assignment4;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.Date;

public class User implements IUser {

    private String email, password;
    String name;
    String address;
    String cardNumber;
    String cardExpiry;
    String cvs;


    public User() {

    }

    public User(String name, String address, String cardNumber, String cardExpiry,
                String cvs) {
        this.name = name;
        this.address = address;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cvs = cvs;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValid() {
        if (TextUtils.isEmpty(getEmail()))
            return 0;
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return 1;
        else if (TextUtils.isEmpty(getPassword()))
            return 2;
        else if (getPassword().length() <= 6)
            return 3;
        else
            return -1;
    }
}
