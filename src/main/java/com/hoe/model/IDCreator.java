package com.hoe.model;

import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Class that contains an ID creator.
 * Produces unique ID's depending on which type of passing object is needed.
 */

// TODO(1): TEST THE CLASS
// TODO(2): Better comments and JDoc.

public class IDCreator {
    private final String SHOW_ID = "5";
    private final String CONTACT_ID = "7";
    private final String TICKET_ID = "3";
    private final String LOCATION_ID = "4";
    private final String PROMOTION_ID = "6";
    private final SecureRandom random = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

    /**
     * Method that creates a random secure key-string.
     * @return Returns a random created key-string of a length of 10.
     */
    public String randomKeyGen(Location location) {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return LOCATION_ID + encoder.encodeToString(id);
    }

    public String randomKeyGen(ContactPerson contactPerson) {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return CONTACT_ID + encoder.encodeToString(id);
    }

    public String randomKeyGen(Show show) {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return SHOW_ID + encoder.encodeToString(id);
    }

    public String randomKeyGen(Promotion promotion) {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return PROMOTION_ID + encoder.encodeToString(id);
    }

    public String randomKeyGen(Ticket ticket) {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return TICKET_ID + encoder.encodeToString(id);
    }

    public int getObject(String ID){
        return Integer.parseInt(ID.substring(0,1));
    }
}
