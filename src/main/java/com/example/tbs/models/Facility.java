package com.example.tbs.models;

public class Facility {
    private String name;
    private int id;

    public Facility(String name) {
        this.name = name;
    }

    public Facility(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

}
