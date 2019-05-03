package com.hoe.model;

import java.io.Serializable;

public class Ticket implements Serializable {

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

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString() {
        return ticketID + "|" + getShowID() + "|" + getPhoneNumber() + "|" + getSeat();
    }


    public void setSeat(String seat) {
        this.seat = seat;
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

    public Location getLocation() {
        return show.getLocation();
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

    public String getShowName() {
        return show.getShowName();
    }

    public String getSeat() {
        return seat;
    }




}
