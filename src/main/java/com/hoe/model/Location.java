package com.hoe.model;

import java.io.Serializable;

public class Location implements Serializable {

    private final String locationID;
    private String name;
    private String typeOfLocation; // TODO: Enums instead of strings?
    private int numberOfSeats;

    /**
     * Standard constructor for the Location class.
     * @param id A unique ID for the Location class.
     * @param name A name for the Location.
     */
    public Location(String id, String name) {
        this.locationID = id;
        this.name = name;
    }

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

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString(){
        return getLocationID() + "|" + getName() + "|" + getTypeOfLocation() + "|" + String.valueOf(getNumberOfSeats());
    }
}
