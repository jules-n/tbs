package com.example.tbs.models;

public class Owner extends User{

    public Owner(int id, String name, String surname, long phone, String login, String pass) {
        super(id, name, surname, phone, login, pass);
        this.role = "owner";
    }

    public Owner(int id, String name, String surname, long phone) {
        super(id, name, surname, phone);
        this.role = "owner";
    }
}
