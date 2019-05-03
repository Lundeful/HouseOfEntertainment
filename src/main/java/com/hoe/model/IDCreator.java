package com.hoe.model;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Class that contains an ID creator.
 * Produces unique ID's depending on which type of object is needed.
 * All the different objects has its own start value, to differ the them from each other
 * Utilizes Base64 to create unique
 */


public class IDCreator {
    private final SecureRandom random = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    public String generateLocationID() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        String locationID = "4";
        return locationID + encoder.encodeToString(id);
    }

    public String generateContactID() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        String contactID = "7";
        return contactID + encoder.encodeToString(id);
    }

    public String generateShowID() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        String showId = "5";
        return showId + encoder.encodeToString(id);
    }

    public String generatePromotionID() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        String promotionID = "6";
        return promotionID + encoder.encodeToString(id);
    }

    public String generateTicketID() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        String ticketID = "3";
        return ticketID + encoder.encodeToString(id);
    }

    public int getObject(String ID){
        return Integer.parseInt(ID.substring(0,1));
    }
}
