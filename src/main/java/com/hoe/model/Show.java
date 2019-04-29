package com.hoe.model;

import java.util.ArrayList;

public class Show {

    private final String showID;
    private String showName;
    private String showType;
    private String program;
    private Location location;
    private String time;
    private String date;
    private String ticketPrice;
    private ArrayList<Ticket> soldTickets;
    private int availableTickets;

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

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getShowID() {
        return showID;
    }

    public String getShowName() {
        return showName;
    }

    public String getShowType() {
        return showType;
    }

    public String getProgram() {
        return program;
    }

    public Location getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    public ArrayList<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public boolean addSoldTicket(Ticket t) {
        if (availableTickets > 0) {
            soldTickets.add(t);
            availableTickets--;
            return true;
        } else {
            return false;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.showName + " - " + this.date;
    }
}
