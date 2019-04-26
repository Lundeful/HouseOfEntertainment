package com.hoe.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Show implements Serializable {

    private final String showID;
    private String showType;
    private String program;
    private Location location;
    private Date time;
    private int ticketPrice;
    private ArrayList<Ticket> soldTickets;
    private ArrayList<Ticket> availiableTickets;

    public Show(String showID, Location location, int ticketPrice){
        this.showID = showID;       // TODO(1): Method that generates ID's
        this.location = location;
        this.ticketPrice = ticketPrice;
    }

    public int getTicketPrice() {
        return ticketPrice;
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
}
