package com.example.assignment4;

public class StockModel {

    String title;
    String manufacturer;
    String category;
    String quantity;
    String price;
    String image;

    public StockModel(String title, String manufacturer, String category, String quantity, String price, String image) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public StockModel() {

    }

    public StockModel(String title, String manufacturer, String category, String quantity, String price) {
        this.title = title;
        this.manufacturer = manufacturer;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
