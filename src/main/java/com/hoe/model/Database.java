package com.hoe.model;

import java.util.ArrayList;

// TODO: JavaDoc
public class Database {
    private ArrayList<Location> locations;
    private ArrayList<Show> shows;
    private ArrayList<Promotion> promotions;
    private ArrayList<ContactPerson> contacts;
    private ArrayList<Ticket> tickets;

    // New fresh database without content
    public Database() {
        locations = new ArrayList<>();
        shows = new ArrayList<>();
        promotions = new ArrayList<>();
        contacts = new ArrayList<>();
        tickets = new ArrayList<>();
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
            return locations.add(l);
    }

    public boolean addShow(Show s) {
        return shows.add(s);

    }

    public boolean addPromotion(Promotion p) {
        return promotions.add(p);
    }

    public boolean addContact(ContactPerson c) {
        return contacts.add(c);
    }

    public boolean addTicket(Ticket t) {
        return tickets.add(t);
    }

    public boolean removeLocation(Location l) {
        return locations.remove(l);
    }

    public boolean removeShow(Show s) {
        return shows.remove(s);
    }

    public boolean removeTicket(Ticket t) {
        return tickets.remove(t);
    }

    public boolean removePromotion(Promotion p) {
        return promotions.remove(p);
    }

    public boolean removeContact(ContactPerson c) {
        return contacts.remove(c);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public ArrayList<ContactPerson> getContacts() {
        return contacts;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

}
