package com.example.tbs.models;

public class Status {
    private String status;
    private int level;
    private int parentLevel;

     public Status(String status, int level, int parentLevel) {
        this.status = status;
        this.level = level;
        this.parentLevel = parentLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getParentLevel() {
        return parentLevel;
    }

    public String getStatus() {
        return status;
    }
}
