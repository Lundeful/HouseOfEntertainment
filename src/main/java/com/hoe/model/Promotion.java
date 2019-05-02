package com.hoe.model;

import java.io.Serializable;
import java.util.Date;

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
        return getPromotionID() + "|" + getShowID() + "|" + getFrom() + "|" + getTo();
    }

    private String getShowID() {
        if(show == null) return "";
        return show.getShowID();
    }

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }
}