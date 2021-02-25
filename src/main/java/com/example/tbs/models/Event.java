package com.example.tbs.models;

import java.sql.Timestamp;

public class Event{
private Status status;
private Timestamp from;
private Timestamp to;
private User user;
private Area area;
private int id;
private int numberOfClients;

    public Event(){

    }
    public Event(Status status, Timestamp from, Timestamp to, User user, Area area, int id) {
        this.status = status;
        this.from = from;
        this.to = to;
        this.user = user;
        this.area = area;
        this.id = id;
    }

    public Event(int numberOfClients, Status status, Timestamp from, Timestamp to, User user, Area area) {
        this.status = status;
        this.from = from;
        this.to = to;
        this.user = user;
        this.area = area;
        this.numberOfClients = numberOfClients;
    }
    public int getNumberOfClients() {
        return numberOfClients;
    }
    public Status getStatus() {
        return status;
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

    public User getUser() {
        return user;
    }

    public Area getArea() {
        return area;
    }

    public int getId(){ return id;}

    @Override
    public String toString() {
        return "Event#" + id+ ", client - "+user+
                ", area: "+area.getNumber()+", time: "+from.toLocalDateTime().toString()+
                "-"+to.toLocalDateTime().toString()+" status: "+status.getStatus();
    }
}
