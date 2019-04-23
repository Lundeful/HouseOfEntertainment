package com.hoe.model;

import java.util.ArrayList;
import java.util.Date;

public class Show {

    private final String showID;
    private String showName;
    private String showType;
    private String program;
    private Location location;
    private Date time;
    private int ticketPrice;
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

    public void setTicketPrice(int ticketPrice) {
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

    public void setTime(Date time) {
        this.time = time;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getTicketPrice() {
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

    public Date getTime() {
        return time;
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
}
