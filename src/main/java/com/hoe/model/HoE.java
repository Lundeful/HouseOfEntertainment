package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HoE {
    private Database database;

    public HoE() {
        database = new Database();
        generateTestObjects();
    }

    // TODO: Remove this method before final delivery
    private void generateTestObjects() {
        Location l1 = new Location("Temp-ID", "Big Hall");
        l1.setNumberOfSeats(578);
        l1.setTypeOfLocation("Theatre");

        Location l2 = new Location("Temp-ID", "Small stage");
        l2.setTypeOfLocation("Theatre");
        l2.setNumberOfSeats(300);

        Location l3 = new Location("Temp-ID", "Supreme");
        l2.setNumberOfSeats(258);
        l2.setTypeOfLocation("Movie theatre");

        Location l4 = new Location("Temp-iD", "IMAX");
        l4.setTypeOfLocation("Movie Theatre");
        l4.setNumberOfSeats(420);

        database.addLocation(l1);
        database.addLocation(l2);
        database.addLocation(l3);
        database.addLocation(l4);
        addShow("Harry potter", "Movie", "28-10-2019", "", l4, "120", "");
        addShow("Cats", "Stage show", "", "Midnight", l2, "260", "");
        addShow("Bohemian Rhapsody", "Movie", "", "", l4, "190", "");
        addShow("AC/DC", "Concert", "", "", l1, "499", "");

        for (int i = 0; i < 2000; i++) {
            addShow("Show " + i, "Type " + i, "Date " + i, "Time " + i, new Location("temp-id", "Location " + i%6), String.valueOf(ThreadLocalRandom.current().nextInt(100, 501)), "");
        }
    }

    public boolean addShow(String name, String type, String date, String time, Location location,
                           String ticketPrice, String program) {
        Show show = new Show("TEMP-ID", formatInput(name)); // TODO: Use ID-generator

        show.setShowType(formatInput(type));
        show.setDate(formatInput(date));
        show.setTime(formatInput(time));
        try {
            show.setLocation(location);
        } catch (NotEnoughSeatsException e) {
            e.printStackTrace();
        }
        show.setTicketPrice(formatInput(ticketPrice));
        show.setProgram(formatInput(program));

        return database.addShow(show);
    }

    private String formatInput(String s) {
        return s.trim();
    }

    /**
     * Removes a show and tickets for that show
     * @param s Show to remove
     * @return Returns true if shows and tickets are removed
     */
    public boolean removeShow(Show s) {
        String id = s.getShowID();
        boolean b = database.removeShow(s);
        if (b) {
            database.getTickets().removeIf(t -> t.getShow().getShowID().equals(id));
        }
        return b;
    }

    public ArrayList<Show> getShows() {
        return database.getShows();
    }

    public ArrayList<Location> getLocations() {
        return database.getLocations();
    }

    public ArrayList<Ticket> getTickets() {
        return database.getTickets();
    }

    public ArrayList<ContactPerson> getContacts() {
        return database.getContacts();
    }

    public ArrayList<Promotion> getPromotions() {
        return database.getPromotions();
    }

    public boolean addLocation(String name, String typeOfLocation, int numberOfSeats) {
        Location l = new Location("TEMP-ID", name); // TODO: Bruk ID-generator
        l.setTypeOfLocation(formatInput(typeOfLocation));
        l.setNumberOfSeats(numberOfSeats);
        return database.addLocation(l);
    }

    public boolean removeLocation(Location l) {
        return database.removeLocation(l);
    }

    public boolean removeTicket(Ticket t) {
        return database.removeTicket(t);
    }

    public boolean addTicket(Show show, String phonenumber, String seat) {
        Ticket t = new Ticket("TEMP-ID", show, phonenumber,seat); //TODO ID-generator
        return database.addTicket(t);
    }

    public void updateTicket(Ticket t, Show show, String phoneNumber, String seat) {
        t.setShow(show);
        t.setPhoneNumber(phoneNumber);
        t.setSeat(seat);
    }

    public boolean removeContact(ContactPerson c) {
        return database.removeContact(c);
    }

    public boolean addContact(String name, String phone, String mail, String website, String affiliation, String other){
        ContactPerson c = new ContactPerson("TEMP-ID", name, phone);
        c.setWebsite(website);
        c.setAffiliation(affiliation);
        c.setEmail(mail);
        c.setOther(other);
        return database.addContact(c);
    }


    public void updateContact(ContactPerson c, String nameText, String phoneText, String mailText, String websiteText,
                              String affiliationText, String otherText) {
        c.setName(nameText);
        c.setPhoneNumber(phoneText);
        c.setEmail(mailText);
        c.setWebsite(websiteText);
        c.setAffiliation(affiliationText);
        c.setOther(otherText);
    }

    public void updateLocation(Location location, String name, String type, int seats) {
        location.setName(name);
        location.setTypeOfLocation(type);
        location.setNumberOfSeats(seats);
    }


    public void updateShow(Show show, String name, String type, String date, String time, String ticketprice, Location location, ContactPerson cp, String program) throws NotEnoughSeatsException {
        show.setShowName(name);
        show.setShowType(type);
        show.setDate(date);
        show.setTicketPrice(ticketprice);
        show.setTime(time);
        show.setProgram(program);
        show.setLocation(location);
        show.setContactPerson(cp);
    }
}
