package com.example.assignment4;

import java.util.Date;

public class User {

    String name;
    String id;
    String address;
    String cardNumber;
    String cardExpiry;
    String cvs;


    public User() {

    }

    public User(String name, String id, String address, String cardNumber, String cardExpiry,
            String cvs) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cvs = cvs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
