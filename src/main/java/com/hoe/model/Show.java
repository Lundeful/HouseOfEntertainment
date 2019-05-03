package com.hoe.model;

import com.hoe.model.exceptions.IllegalLocationException;

import java.io.Serializable;

/**
 * This class is used for storing in shows class, formation about shows
 */
public class Show implements Serializable {

    private final String showID;
    private String showName;
    private String showType;
    private String program;
    private String date;
    private String time;
    private Location location;
    private String ticketPrice;
    private int availableTickets;
    private ContactPerson contactPerson;

    /**
     * Constructor for the show object. Needs ID and Name
     * @param showID Unique ID for the show
     * @param showName Name for the show
     * @auth 681
     */
    public Show(String showID, String showName){
        this.showName = showName;
        this.showID = showID;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * Sets the new location for the show
     * @auth 681
     * @param location The new location for this show
     * @throws IllegalLocationException Throws this exception if the location is a null object
     */
    public void setLocation(Location location) throws IllegalLocationException {
        if (location == null) {
            throw new IllegalLocationException("Show must have a location");
        } else {
            this.location = location;
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setShowName(String showName) {
        this.showName = showName;
      }
    public String getShowID() {
        return showID;
    }

    public void setDate(String s) {
        this.date = s;
    }

    /**
     * Creates a string with paramaters used for saving and loading in CSV
     * @auth 681
     * @return String with all needed attribute
     */
    public String toCSVString(){
        return showID + "|" + showName + "|" + showType + "|" + getLocationID() + "|" + getDate() + "|" +
                getTime() + "|" + getTicketPrice() + "|" + getAvailableTickets() + "|" + getProgram() + "|"
                + getContactPersonID();
    }

    private String getContactPersonID() {
        if(this.contactPerson == null) return "";
        return contactPerson.getContactID();
    }

    public String getLocationID() {
        if(location == null) return "";
        return location.getLocationID();
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getShowType() {
        return showType;
    }

    public String getTime() {
        return time;
    }

    public String getProgram() {
        return program;
    }

    public String getShowName() {
        return showName;
    }

    public String getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Used for displaying show info
     * @return
     */
    @Override
    public String toString() {
        return this.showName + " - " + this.date;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public ContactPerson getContact() {
        return this.contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setAvailableTickets(int availableTickets){
        this.availableTickets = availableTickets;
    }
}
