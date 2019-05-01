package com.hoe.model;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

    private final String ticketID;
    private String phoneNumber;
    private String seatNumber;
    private Show show;


    public Ticket(String ticketID, Show show, String seat) {
        this.ticketID = ticketID;
        this.show = show;
        this.seatNumber = seat;
    }

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString() {
        return getTicketID() + "|" + getShowID() + "|" + getPhoneNumber() + "|" + getSeatNumber();
    }

    public String getTime() {
        return show.getTime();
    }

    public String getDate() {
        return show.getDate();
    }

    public String getPrice() {
        return show.getTicketPrice();
    }

    public String getShowID() {
        return show.getShowID();
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setDate(String s) {
        show.setDate(s);
    }

    public void setPhoneNumber(String s) {
        this.phoneNumber = s;
    }

    public void setSeatNumber(String s) {
        this.seatNumber = s;
    }

    public void setTime(String s) {
        show.setTime(s);
    }
}
