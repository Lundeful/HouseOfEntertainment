package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

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
    private ContactPerson contactPerson;

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
        if (location == null) {
            this.location = null;
        } else if (soldTickets.size() <= location.getNumberOfSeats()) {
            this.location = location;
            availableTickets = location.getNumberOfSeats();
        } else {
            throw new NotEnoughSeatsException("Tickets sold is larger than number of seats");
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

    public void setSoldTickets(ArrayList<Ticket> ticket) {
        this.soldTickets = ticket;
    }

    public void setDate(String s) {
        this.date = s;
    }


    public String toCSVString(){
        return getShowID() + "|" + getShowName() + "|" + getShowType() + "|" + getLocationID() + "|" + getDate() + "|" +
                getTime() + "|" + getTicketPrice() + "|" + String.valueOf(getAvailableTickets())
                + "|" + getProgram() + "|" + getContactPersonID();
}

    private String getContactPersonID() {
        if(this.contactPerson == null) return "";
        return contactPerson.getContactID();
    }

    public String getLocationID() {
        if(location == null) return ""; // TODO: Sjekk at alle getID gjør dette der det må gjøres
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
