package com.example.assignment4;

import android.net.Uri;

public class StockModel {
    String id;
    String title;
    String manufacturer;
    String category;
    Double price;
    Uri image;

    public StockModel(String id, String title, String manufacturer, String category, Double price, Uri image) {
        this.id = id;
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.image = image;
    }

    public StockModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
