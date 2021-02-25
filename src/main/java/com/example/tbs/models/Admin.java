package com.example.tbs.models;


public class Admin extends User{

    public Admin(int id, String name, String surname, long phone, String login, String pass) {
        super(id, name, surname, phone, login, pass);
        this.role = "admin";
    }
    public Admin(int id, String name, String surname, long phone) {
        super(id, name, surname, phone);
        this.role = "admin";
    }

    public Admin(String name, String surname, long phone, String login, String pass) {
        super(name, surname, phone, login, pass);
        this.role = "admin";
    }
}
