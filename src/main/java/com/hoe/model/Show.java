package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

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
    private ContactPerson contactPerson;

    public Show(String showID, String showName){
        this.showName = showName;
        this.showID = showID;
        this.soldTickets = new ArrayList<>();
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

    public void setLocation(Location location) throws NotEnoughSeatsException {
        if (soldTickets.size() <= location.getNumberOfSeats()) {
            this.location = location;
            availableTickets = location.getNumberOfSeats();
        } else {
            throw new NotEnoughSeatsException("Tickets sold is larger than number of seats");
        }
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setContactPerson(ContactPerson c) {
        this.contactPerson = c;
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

    public ContactPerson getContact() {
        return this.contactPerson;
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
