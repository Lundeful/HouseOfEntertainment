package com.hoe.model;

import java.util.Date;

public class Promotion {
    private final String LOCATION_ID;
    private String showID;
    private String from;
    private String to;

    public Promotion (String id, String s){
        this.LOCATION_ID = id;
        this.showID = s;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getLOCATION_ID() {
        return LOCATION_ID;
    }

    public String getShowID() {
        return showID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toCSVString(){
        return getLOCATION_ID() + "|" + getShowID() + "|" + String.valueOf(getFrom() + "|" +
                String.valueOf(getTo()));
    }
}