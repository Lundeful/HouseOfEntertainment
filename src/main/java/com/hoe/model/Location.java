package com.hoe.model;

public class Location {

    private final String locationID;
    private String name;
    private String typeOfLocation; // TODO: Enums instead of strings?
    private int numberOfSeats;

    public Location(String id, String name) {
        this.locationID = id;
        this.name = name;
    }

    // Simple getters and setters below
    public void setName(String name) {
        this.name = name;
    }

    public void setTypeOfLocation(String typeOfLocation) {
        this.typeOfLocation = typeOfLocation;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getTypeOfLocation() {
        return typeOfLocation;
    }

    public String getName() {
        return name;
    }
    public String getLocationID() {
        return locationID;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
