package com.example.tbs.models;

import java.util.List;

public class Area {
    private int number;
    private String description;
    private boolean availability;
    private List<Facility> facilities;
    private int[] idFacilities;

    public Area(int number, String description, boolean availability, List<Facility> facilities) {
        this.number = number;
        this.description = description;
        this.availability = availability;
        this.facilities = facilities;
    }

    public Area(int number, String description, int[] idFacilities) {
        this.number = number;
        this.description = description;
        this.idFacilities = idFacilities;
    }
    public int[] getIdFacilities() {
        return idFacilities;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }


}
