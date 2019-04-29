package com.hoe.model;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

    private final String ticketID;
    private String locationID;
    private String showID;
    private String date;
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
    private int price;
    private String phoneNumber;
    private int seatNumber;
    private Show show;

    public Ticket(String ticketID, String showID, int price) {
        this.ticketID = ticketID;
        this.showID = showID;
    }

    public Ticket(String ticketID, Show show, String phoneNumber){
        this.ticketID = ticketID; // TODO(1): Make method that generates ID's
        this.location = location;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getTicketID() {
        return ticketID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String location) {
        this.locationID = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShowID(){
        return showID;
    }

    public void setShowID(String showID) {
        this.showID = showID;
    }

    /**
     * This method is meant to print out a string with a CSV format so that is it easier to save.
     * @return Returns a String with complete information and in CSV-format.
     */
    public String toCSVString(){
        return getTicketID() + "|" + getShowID() +"|" + String.valueOf(getPrice()) + "|" + getDate()
                + "|" + getPhoneNumber() + "|" + getSeatNumber();
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
