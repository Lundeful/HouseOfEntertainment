package com.hoe.model;

import java.util.Date;

public class Ticket {

    private final String ticketID;
    private String location;
    private Date date;
    private int price;
    private String phoneNumber;
    private Show show;

    public Ticket(String ticketID, String showID, String location, int price){
        this.ticketID = ticketID; // TODO(1): Make method that generates ID's
        this.location = location;
        this.price = price;

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
