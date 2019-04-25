package com.hoe.model;

import java.util.ArrayList;
import java.util.Date;

public class Show {

    private final String showID;
    private String showType;
    private String program;
    private String locationID;
    private String time;
    private int ticketPrice;
    private ArrayList<Ticket> soldTickets;


    private ArrayList<Ticket> availiableTickets;

    /**
     * Standard constructor for the Show class.
     * @param showID A unique ID for the Show object.
     * @param locationID The unique ID for a location object to connect to a show object.
     * @param ticketPrice The price for a ticket.
     */
    public Show(String showID, String locationID, int ticketPrice){
        this.showID = showID;       // TODO(1): Method that generates ID's
        this.locationID = locationID;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public String getShowID() {
        return showID;
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

    public String getShowType() {
        return showType;
    }

    public String getProgram() {
        return program;
    }

    public String getTime() {
        return time;
    }

    public String getLocationID() {
        return locationID;
    }

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString(){
        return getShowID() + "|" + getLocationID() + "|" + String.valueOf(getTicketPrice()) + "|"
                + getShowType() + "|" + getTime() + "|" + getProgram();
                        //String.valueOf(availiableTickets.size()) + "," + String.valueOf(soldTickets.size())
    }
}
