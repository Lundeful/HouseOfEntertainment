package com.hoe.model;

import java.util.ArrayList;

// TODO: JavaDoc
public class Database {
    private ArrayList<Location> locations;
    private ArrayList<Show> shows;
    private ArrayList<Promotion> promotions;
    private ArrayList<ContactPerson> contacts;

    // New fresh database without content
    public Database() {
        locations = new ArrayList<>();
        shows = new ArrayList<>();
        promotions = new ArrayList<>();
        contacts = new ArrayList<>();
    }

    // Database from existing sources
    public Database(ArrayList<Location> locations, ArrayList<Show> shows, ArrayList<Promotion> promotions,
                    ArrayList<ContactPerson> contacts) {
        this.locations = locations;
        this.shows = shows;
        this.promotions = promotions;
        this.contacts = contacts;
    }

    public boolean addLocation(Location l) {
        locations.add(l);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean addShow(Show s) {
        shows.add(s);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean addPromotion(Promotion p) {
        promotions.add(p);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean addContact(ContactPerson c) {
        contacts.add(c);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeLocation(Location l) {
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeShow(Show s) {
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removePromotion(Promotion p) {
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeContact(ContactPerson c) {
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }
}
