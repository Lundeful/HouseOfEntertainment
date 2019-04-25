package com.hoe.model;

import java.util.Date;

public class Promotion {
    private final String LOCATION_ID;
    private Show show;
    private Date from;
    private Date to;

    public Promotion (String id, Show s){
        LOCATION_ID = id;
        show = s;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getLOCATION_ID() {
        return LOCATION_ID;
    }

    public Show getShow() {
        return show;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
