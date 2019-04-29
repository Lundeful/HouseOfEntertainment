package com.hoe.model;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class HoE {
    private Database database;

    public HoE() {
        database = new Database();
        generateTestObjects();
    }

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

        for (int i = 0; i < 1000; i++) {
            addShow("Show " + i, "Type " + i, "Date " + i, "Time " + i, new Location("temp-id", "Location " + i%6), String.valueOf(ThreadLocalRandom.current().nextInt(100, 501)), "");
        }
    }

    public boolean addShow(String name, String type, String date, String time, Location location,
                           String ticketPrice, String program) {
        Show show = new Show("TEMP-ID", formatInput(name)); // TODO: Use ID-generator

        show.setShowType(formatInput(type));
        show.setDate(formatInput(date));
        show.setTime(formatInput(time));
        show.setLocation(location);
        show.setTicketPrice(formatInput(ticketPrice));
        show.setProgram(formatInput(program));

        return database.addShow(show);
    }

    private String formatInput(String s) {
        return s.trim();
    }

    public boolean removeShow(Show s) {
        return database.removeShow(s);
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

}
