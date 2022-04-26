package com.example.assignment4;

import android.view.View;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Cart {
    String title;
    String price;
    String quantity;

    public Cart(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public Cart() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String pay(PaymentStrategy paymentMethod){
        return paymentMethod.pay();
    }

    public interface OnStateChangeListener {
        void onStateChange(View v);
    }

}
