package com.hoe.model;

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
    private final SecureRandom random = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    ContactPerson contactPerson;
    Show show;
    Ticket ticket;
    Location location;
    Database data;
    /**
     * Method that created a unique key for different ID's
     * @param object passes in a object of needed type to check if the ID exists or not.
     * @return returns a produced key.
     */
    public String personID(Object object) {
        if (!contactPerson.equals(object)) return CONTACT_ID + randomKeyGen();
        if (!show.equals(object)) return SHOW_ID + randomKeyGen();
        if (!ticket.equals(object)) return TICKET_ID + randomKeyGen();
        if (!location.equals(object)) return LOCATION_ID + randomKeyGen();
        return null;
    }

    /**
     * Method that creates a random secure key-string.
     * @return Returns a random created key-string of a length of 10.
     */
    public String randomKeyGen() {
        byte[] id = new byte[10];
        random.nextBytes(id);
        return encoder.encodeToString(id);
    }

    /**
     *
     * @param obj passes in a object to check if there is a duplicate key.
     * @return returns true or false depending on the checks
     */
    @Override
    public boolean equals(Object obj) {
        // Check if the object type is the same.
        if (this == obj) return true;
        // Check if the object type is different.
        if (getClass() != obj.getClass()) return false;

        // Casting classes to objects for ID checks
        ContactPerson otherContact = (ContactPerson) obj;
        Show otherShow = (Show) obj;
        Ticket otherTicket = (Ticket) obj;
        Location otherLocation = (Location) obj;
        // Data fields for comparison
        String contactID1 = this.contactPerson.getContactID();
        String contactID2 = otherContact.getContactID();
        String showID1 = this.show.getShowID();
        String showID2 = otherShow.getShowID();
        String ticketID1 = this.ticket.getTicketID();
        String ticketID2 = otherTicket.getTicketID();
        String locationID1 = this.location.getLocationID();
        String locationID2 = otherLocation.getLocationID();
        // Checking if ID already exist
        if (contactID1 == contactID2) return true;
        if (showID1 == showID2) return true;
        if (ticketID1 == ticketID2) return true;
        if (locationID1 == locationID2) return true;
        return false;
    }
}
