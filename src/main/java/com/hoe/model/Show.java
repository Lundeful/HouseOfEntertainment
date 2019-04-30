package com.hoe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Show implements Serializable {

    private final String showID;
    private String showName;
    private String showType;
    private String program;
    private String date;
    private String locationID;
    private String time;
    private ArrayList<Ticket> soldTickets = new ArrayList<>();
    private Location location;
    private String ticketPrice;
    private int availableTickets;

    /**
     * Standard constructor for the Show class.
     * @param showID A unique ID for the Show object.
     * @param locationID The unique ID for a location object to connect to a show object.
     * @param ticketPrice The price for a ticket.
     */
    public Show(String showID, String locationID, String ticketPrice){
        this.showID = showID;       // TODO(1): Method that generates ID's
        this.locationID = locationID;
        this.ticketPrice = ticketPrice;
    }

    public Show(String showID, String showName){
        this.showName = showName;
        this.showID = showID;
    }


    public boolean setAvailableTickets(int n) {
        if (soldTickets.size() <= n) {
            availableTickets = n;
            return true;
        } else {
            return false;
        }
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

    public void setLocationID(String location) {
        this.locationID = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShowID() {
        return showID;
    }

    public void setSoldTickets(ArrayList<Ticket> ticket) {
        this.soldTickets = ticket;
    }

    public void setDate(String s) {
        this.date = s;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toCSVString(){
        return getShowID() + "|" + getShowName() + "|" + getShowType() + "|" + getLocationID() + "|" + getDate() + "|" +
                getTime() + "|" + getTicketPrice() + "|" + String.valueOf(getAvailableTickets())  + "|" +
                String.valueOf(getSoldTickets().size());
}

    public String getLocationID() {
        if(locationID != null){
            return locationID;
        }else{
            return location.getLocationID();
        }
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

    public ArrayList<Ticket> getSoldTickets(){
        return this.soldTickets;
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

    @Override
    public String toString() {
        return this.showName + " - " + this.date;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }
}
