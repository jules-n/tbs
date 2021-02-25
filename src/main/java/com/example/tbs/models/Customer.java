package com.example.tbs.models;

public class Customer extends User{

    public Customer(String name, String surname, long phone, String login, String pass) {
        super(name, surname, phone, login, pass);
        this.role = "customer";
    }
    public Customer(int id, String name, String surname, long phone) {
        super(id, name, surname, phone);
        this.role = "customer";
    }
}
