package com.hoe.model;

import java.io.Serializable;

public class Promotion implements Serializable {
    private String promotionID;
    private Show show;
    private String from;
    private String to;

    public Promotion(String promotionID,Show show){
        this.promotionID = promotionID;
        this.show = show;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toCSVString(){
        return promotionID + "|" + getShowID() + "|" + from + "|" + to;
    }

    private String getShowID() {
        if(show == null) return "";
        return show.getShowID();
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show s) {
        this.show = s;
    }

    // Getters used for TableView columns
    public String getShowName() {
        return show.getShowName();
    }

    public String getDate() {
        return show.getDate();
    }

}