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
        return ticketID + "|" + getShowID() + "|" + phoneNumber + "|" + seat;
    }


    public void setSeat(String seat) {
        this.seat = seat;
      }

    public String getDate() {
        return show.getDate();
    }

    public String getShowID() {
        if (this.show == null) return "";
        return show.getShowID();
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

    public void setPhoneNumber(String s) {
        this.phoneNumber = s;
    }

    public String getSeat() {
        return seat;
    }

    // Getters used for tableview columns
    public String getTime() {
        return show.getTime();
    }

    public String getShowName() {
        return show.getShowName();
    }
}
