package com.example.tbs.models;


public abstract class User {
    protected String name;
    protected int id;
    protected String surname;
    protected String role;
    protected long phone;
    private String login;
    private String pass;

    public User(String name, String surname, long phone, String login, String pass) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.pass = pass;
    }
    public User(int id, String name, String surname, long phone, String login, String pass) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.login = login;
        this.pass = pass;
    }
    public User(int id, String name, String surname, long phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public long getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        if(surname!=null)
        return "FIO: "+name + " " + surname+", phone: "+phone;
        else return "FIO: "+name+", phone: "+phone;
    }
}
