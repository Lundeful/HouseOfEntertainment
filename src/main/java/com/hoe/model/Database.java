package com.hoe.model;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: JavaDoc
public class Database  implements Serializable {
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
                    ArrayList<ContactPerson> contacts, ArrayList<Ticket> tickets) {
        this.locations = locations;
        this.shows = shows;
        this.promotions = promotions;
        this.contacts = contacts;
        this.tickets = tickets;
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

    public boolean addTicket(Ticket t){
        tickets.add(t);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeLocation(Location l) {
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeShow(Show s) {
        return shows.remove(s);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
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

    public boolean removeTicket(Ticket t){
        // TODO: Iterate through list and remove according to unique ID
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public ArrayList<Location> getLocations(){
        return this.locations;
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

    public ArrayList<Ticket> getTickets(){
        return this.tickets;
    }
}
