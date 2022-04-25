package com.example.assignment4;

import java.util.Date;

public class OrderReceipt {

    String userID;
    String userEmail;
    String totalPrice;
    String paymentType;
    String date;

    public OrderReceipt(String userID, String userEmail, String paymentType, String totalPrice, String date) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.paymentType = paymentType;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public OrderReceipt() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
