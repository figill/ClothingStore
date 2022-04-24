package com.example.assignment4;

public class OrderReceipt {

    String userID;
    String userEmail;
    String itemsBought;
    String totalPrice;

    public OrderReceipt(String userID, String userEmail, String itemsBought, String totalPrice) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.itemsBought = itemsBought;
        this.totalPrice = totalPrice;
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

    public String getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(String itemsBought) {
        this.itemsBought = itemsBought;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
