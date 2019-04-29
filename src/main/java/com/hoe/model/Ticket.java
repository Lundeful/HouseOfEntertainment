package com.hoe.model;

public class Ticket {

    private final String ticketID;
    private String location;
    private String date;
    private String price;
    private String phoneNumber;
    private String showName;
    private String showID;

    public Ticket(String ticketID, Show show, String phoneNumber){
        this.ticketID = ticketID; // TODO(1): Make method that generates ID's
        this.showID = show.getShowID();
        this.showName = show.getShowName();
        this.location = show.getLocation().getName();
        this.price = show.getTicketPrice();
        this.phoneNumber = phoneNumber;
        this.date = show.getDate();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShowName() {
        return showName;
    }
}
