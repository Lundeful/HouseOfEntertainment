package com.hoe.model;

import java.util.Date;

public class Ticket {

    private final String ticketID;
    private int seatNumber;
    private String location;
    private Date date;
    private int price;
    private String phoneNumber;

    public Ticket(String ticketID, String location, int price){
        this.ticketID = ticketID; // TODO(1): Make method that generates ID's
        this.location = location;
        this.price = price;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
