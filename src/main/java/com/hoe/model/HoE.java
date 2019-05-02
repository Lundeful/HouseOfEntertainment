package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.CSVSaver;
import com.hoe.model.datasaving.JobjSaver;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.InvalidFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class HoE {
    private Database database;
    private IDCreator id = new IDCreator();
    private Show s = new Show("","");
    private ContactPerson contact = new ContactPerson("","","");
    private Ticket ticket = new Ticket("",s,"","");
    private Location loc = new Location("","");


    public HoE() throws NotEnoughSeatsException {
        database = new Database();
        TestDataBase test = new TestDataBase();
        database = test.generateTestObjects();
    }

    public boolean addShow(String name, String type, String date, String time, Location location,
                           String ticketPrice, String program) {
        Show show = new Show(id.randomKeyGen(s), formatInput(name)); // TODO: Use ID-generator

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

    public Database getDataBase(){
        return this.database;
    }

    public void setDatabase(Database data){
        this.database = data;
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


    /**
     * Method that
     *
     * @param path
     * @return returns true and saves the given file-type if it's successful,
     * returns false if there is any exceptions, leading the save to not being applied.
     */
    public boolean save(String path) {
        try {
            JobjSaver jobj = new JobjSaver();
            CSVSaver csv = new CSVSaver();
            if (path.endsWith(".csv")) {
                new Thread(() -> {
                    csv.saveData(path, database);
                    Thread.currentThread().interrupt();
                }).start();
            } else if (path.endsWith(".ser")) {
                new Thread(() -> {
                    jobj.saveData(path, database);
                    Thread.currentThread().interrupt();
                }).start();
            }
            return true;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void load(String path) throws WrongCSVFormatException, InvalidFileException, CorruptFileException, NotEnoughSeatsException {
        JobjLoader jobj = new JobjLoader();
        CSVLoader csv = new CSVLoader();
        if (path.endsWith(".csv")) {
            database = csv.readData(path);
        } else if (path.endsWith(".ser")) {
            database = jobj.loadData(path);
        } else {
            throw new InvalidFileException("Wrong file type");
        }
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
        Location l = new Location(id.randomKeyGen(loc), name); // TODO: Bruk ID-generator
        l.setTypeOfLocation(formatInput(typeOfLocation));
        l.setNumberOfSeats(numberOfSeats);
        return database.addLocation(l);
    }

    public ArrayList<Show> filterShow(String filter) {
        ArrayList<Show> filterList = new ArrayList<>();
        for (Show show : database.getShows()) {
            if (show.getShowName().contains(filter)) {
                filterList.add(show);
            } else if (show.getShowType().contains(filter)) {
                filterList.add(show);
            } else if (show.getDate().contains(filter)) {
                filterList.add(show);
            } else if (show.getTime().contains(filter)) {
                filterList.add(show);
            } else if (show.getLocation().getName().contains(filter)) {
                filterList.add(show);
            } else if (show.getTicketPrice().contains(filter)) {
                filterList.add(show);
            }
            if (show.getContact() != null) {
                if (show.getContact().getName().contains(filter)) {
                    filterList.add(show);
                } else if (show.getContact().getPhoneNumber().contains(filter)) {
                    filterList.add(show);
                }
            }
        }
        return filterList;
    }


    public ArrayList<Location> filterLocation(String filter) {
        ArrayList<Location> filterList = new ArrayList<>();
        for (Location location : database.getLocations()) {
            if (location.getName().contains(filter)) {
                filterList.add(location);
            } else if (location.getTypeOfLocation().contains(filter)) {
                filterList.add(location);
            }
        }
        return filterList;
    }

    public ArrayList<ContactPerson> filterContactPerson(String filter) {
        ArrayList<ContactPerson> filterList = new ArrayList<>();
        for (ContactPerson person : database.getContacts()) {
            if (person.getName().contains(filter)) {
                filterList.add(person);
            } else if (person.getPhoneNumber().contains(filter)) {
                filterList.add(person);
            } else if (person.getEmail().contains(filter)) {
                filterList.add(person);
            } else if (person.getOther().contains(filter)) {
                filterList.add(person);
            } else if (person.getAffiliation().contains(filter)) {
                filterList.add(person);
            } else if (person.getWebsite().contains(filter)) {
                filterList.add(person);
            }
        }
        return filterList;
    }

    public ArrayList<Ticket> filterTickets(String filter) {
        ArrayList<Ticket> filterList = new ArrayList<>();
        for (Ticket ticket : database.getTickets()) {
            if (ticket.getDate().contains(filter)) {
                filterList.add(ticket);
            }
            if (ticket.getShow() != null) {
                if (ticket.getShow().getShowName().contains(filter)) {
                    filterList.add(ticket);
                }
            }
            if (ticket.getPhoneNumber().contains(filter)) {
                filterList.add(ticket);
            }
        }
        return filterList;
    }

    public boolean removeLocation(Location l) {
        return database.removeLocation(l);
    }

    public boolean removeTicket(Ticket t) {
        return database.removeTicket(t);
    }

    public boolean addTicket(Show show, String phonenumber, String seat) {
        Ticket t = new Ticket(id.randomKeyGen(ticket), show, phonenumber,seat); //TODO ID-generator
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
        ContactPerson c = new ContactPerson(id.randomKeyGen(contact), name, phone);
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
