package com.example.assignment4;

public class Admin {
    String name;
    String phone;

    public Admin(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Admin() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
