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

    public boolean addTicket(Ticket t){
        tickets.add(t);
        // TODO: Exceptionhandling, confirmation-messages, logging etc
        return true;
    }

    public boolean removeLocation(Location l) {
        return locations.remove(l);
    }

    public boolean removeShow(Show s) {
        return shows.remove(s);
    }

    public boolean removePromotion(Promotion p) {
        return promotions.remove(p);
    }

    public boolean removeContact(ContactPerson c) {
        return contacts.remove(c);
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

    public Location findLocation(String id){
        for(Location location : locations){
            if(location.getLocationID().equals(id)){
                return location;
            }
        }
        return null;
    }

    public Show findShow(String showID) {
        for(Show s : shows){
            if(s.getShowID().equals(showID)){
                return s;
            }
        }
        return null;
    }

    public ContactPerson findContactPerson(String s) {
        for(ContactPerson contactPerson : contacts){
            if(contactPerson.getContactID().equals(s)){
                return contactPerson;
            }
        }
        return null;
    }
}
