package com.hoe.model;

public class Ticket {

    private final String ticketID;
    private Show show;
    private String phoneNumber;
    private String seat;

    public Ticket(String ticketID, Show show, String phoneNumber, String seat){
        this.ticketID = ticketID;
        this.show = show;
        this.phoneNumber = phoneNumber;
        this.seat = seat;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getTicketID() {
        return ticketID;
    }

    public Location getLocation() {
        return show.getLocation();
    }

    public String getDate() {
        return show.getDate();
    }

    public String getTime() {
        return show.getTime();
    }

    public String getPrice() {
        return show.getTicketPrice();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShowName() {
        return show.getShowName();
    }

    public Show getShow() {
        return show;
    }

    public String getSeat() {
        return seat;
    }




}
