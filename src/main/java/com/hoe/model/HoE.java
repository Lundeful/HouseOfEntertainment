package com.hoe.model;

import com.hoe.model.exceptions.NotEnoughSeatsException;

import java.util.ArrayList;
import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.CSVSaver;
import com.hoe.model.datasaving.JobjSaver;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.InvalidFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;

public class HoE {
    private Database database;
    private IDCreator id = new IDCreator();

    public HoE() throws NotEnoughSeatsException {
        database = new Database();

        // TODO: Fjern testdatabase før levering
        TestDataBase test = new TestDataBase();
        database = test.generateTestObjects();
    }

    /**
     * Method used to save either a csv or jobj file on the given choice of the user
     * The method starts its own threads on either csv or jobj file, this is to make sure that the program it self
     * does'nt freeze when saving the program files
     * @param path is the given path that the user wants to save the given file.
     * @return returns true and saves the given file-type if it's successful,
     * returns false if there is any exceptions, leading to that the save is not applied.
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
            } else if (path.endsWith(".jobj")) {
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

    /**
     * Method used to load either a csv or jobj file on the users own choice
     * @param path is the given path of the users saved file
     * @throws WrongCSVFormatException throws an exception if the csv file is wrongly formatted
     * @throws InvalidFileException throws an exception if the file that is attempted to load is not within csv or jobj
     * @throws CorruptFileException throws an exception if the attempted load file is corrupted
     * @throws NotEnoughSeatsException throws an exception if the given seat number of the csv file is wrongly formatted
     */
    public void load(String path) throws WrongCSVFormatException, InvalidFileException, CorruptFileException, NotEnoughSeatsException {
        JobjLoader jobj = new JobjLoader();
        CSVLoader csv = new CSVLoader();
        if (path.endsWith(".csv")) {
            database = csv.readData(path);
        } else if (path.endsWith(".jobj")) {
            database = jobj.loadData(path);
        } else {
            throw new InvalidFileException("Wrong file type");
        }
    }

    public boolean addShow(String name, String type, String date, String time, Location location,
                           String ticketPrice, String program) {
        Show show = new Show(id.generateShowID(), name.trim());

        show.setShowType(type.trim());
        show.setDate(date.trim());
        show.setTime(time.trim());
        try {
            show.setLocation(location);
        } catch (NotEnoughSeatsException e) {
            e.printStackTrace();
        }
        show.setTicketPrice(ticketPrice.trim());
        show.setProgram(program.trim());

        return database.addShow(show);
    }

    /**
     * Removes a show and all the tickets and promotions connected to it
     * @param s Show to remove
     * @return Returns true if shows and tickets are removed
     */
    public boolean removeShow(Show s) {
        String id = s.getShowID();
        boolean b = database.removeShow(s);
        if (b) {
            database.getTickets().removeIf(t -> t.getShow().getShowID().equals(id));
            database.getPromotions().removeIf(p -> p.getShow().getShowID().equals(id));
        }
        return b;
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

    public void addLocation(String name, String typeOfLocation, int numberOfSeats) {
        Location l = new Location(id.generateLocationID(), name);
        l.setTypeOfLocation(typeOfLocation.trim());
        l.setNumberOfSeats(numberOfSeats);
        database.addLocation(l);
    }

    public boolean removeLocation(Location l) throws NotEnoughSeatsException {
        String locationID = l.getLocationID();
        boolean b = database.removeLocation(l);
        if (b) {
            for (Show s :  database.getShows()) {
                if (s.getLocationID().equals(locationID)) s.setLocation(null);
            }
        }  // TODO: Behold eller ta bort fjerning av location?
        return b;
    }

    public void updateLocation(Location location, String name, String type, int seats) {
        location.setName(name);
        location.setTypeOfLocation(type);
        location.setNumberOfSeats(seats);
    }

    public void addTicket(Show show, String phonenumber, String seat) {
        Ticket t = new Ticket(id.generateTicketID(), show, phonenumber,seat);
        database.addTicket(t);
    }

    public boolean removeTicket(Ticket t) {
        return database.removeTicket(t);
    }

    public void updateTicket(Ticket t, Show show, String phoneNumber, String seat) {
        t.setShow(show);
        t.setPhoneNumber(phoneNumber);
        t.setSeat(seat);
    }

    public boolean addContact(String name, String phone, String mail, String website, String affiliation, String other){
        ContactPerson c = new ContactPerson(id.generateContactID(), name, phone);
        c.setWebsite(website);
        c.setAffiliation(affiliation);
        c.setEmail(mail);
        c.setOther(other);
        return database.addContact(c);
    }

    public boolean removeContact(ContactPerson c) {
        return database.removeContact(c);
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

    public boolean addPromotion(Show s, String from, String to) {
        Promotion p = new Promotion(id.generatePromotionID(), s);
        p.setFrom(from);
        p.setTo(to);
        return database.addPromotion(p);
    }

    public boolean removePromotion(Promotion p) {
        return database.removePromotion(p);
    }

    public void updatePromotion(Promotion p, Show s, String from, String to) {
        p.setShow(s);
        p.setFrom(from);
        p.setTo(to);
    }

    /**
     * Method that filters shows on the given search criteria of the user on the given possible fields that is shown
     * in the show view
     * @param filter passes in the search word of the user as a string
     * @return returns a array list with the given search to override the database array list temporarily to just show
     * the given search criteria
     */
    public ArrayList<Show> filterShow(String filter) {
        filter = filter.toLowerCase();
        ArrayList<Show> filterList = new ArrayList<>();
        for (Show show : database.getShows()) {
            if (show.getShowName() != null && show.getShowName().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getShowType() != null && show.getShowType().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getDate() != null && show.getDate().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getTime() != null && show.getTime().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getLocation() != null && show.getLocation().getName().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getTicketPrice() != null && show.getTicketPrice().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getContact() != null && show.getContact().getName().toLowerCase().contains(filter)) filterList.add(show);
            else if (show.getContact() != null && show.getContact().getPhoneNumber().toLowerCase().contains(filter)) filterList.add(show);
        }
        return filterList;
    }

    /**
     * Method that filters location on the given search criteria of the user on the given possible fields that is shown
     * in the location view
     * @param filter passes in the search word of the user as a string
     * @return returns a array list with the given search to override the database array list temporarily to just show
     * the given search criteria
     */
    public ArrayList<Location> filterLocation(String filter) {
        filter = filter.toLowerCase();
        ArrayList<Location> filterList = new ArrayList<>();
        for (Location location : database.getLocations()) {
            if (location.getName() != null && location.getName().toLowerCase().contains(filter)) filterList.add(location);
            else if (location.getTypeOfLocation() != null && location.getTypeOfLocation().toLowerCase().contains(filter)) filterList.add(location);
        }
        return filterList;
    }

    /**
     * Method that filters contact person on the given search criteria of the user on the given possible fields that is shown
     * in the contact person view
     * @param filter passes in the search word of the user as a string
     * @return returns a array list with the given search to override the database array list temporarily to just show
     * the given search criteria
     */
    public ArrayList<ContactPerson> filterContactPerson(String filter) {
        filter = filter.toLowerCase();
        ArrayList<ContactPerson> filterList = new ArrayList<>();
        for (ContactPerson person : database.getContacts()) {
            if (person.getName() != null && person.getName().toLowerCase().contains(filter)) filterList.add(person);
            else if (person.getPhoneNumber() != null && person.getPhoneNumber().toLowerCase().contains(filter)) filterList.add(person);
            else if (person.getEmail() != null  && person.getEmail().toLowerCase().contains(filter)) filterList.add(person);
            else if (person.getOther() != null && person.getOther().toLowerCase().contains(filter)) filterList.add(person);
            else if (person.getAffiliation() != null && person.getAffiliation().toLowerCase().contains(filter)) filterList.add(person);
            else if (person.getWebsite() != null && person.getWebsite().toLowerCase().contains(filter)) filterList.add(person);
        }
        return filterList;
    }

    /**
     * Method that filters ticket on the given search criteria of the user on the given possible fields that is shown
     * in the ticket view
     * @param filter passes in the search word of the user as a string
     * @return returns a array list with the given search to override the database array list temporarily to just show
     * the given search criteria
     */
    public ArrayList<Ticket> filterTickets(String filter) {
        filter = filter.toLowerCase();
        ArrayList<Ticket> filterList = new ArrayList<>();
        for (Ticket ticket : database.getTickets()) {
            if (ticket.getDate() != null && ticket.getDate().toLowerCase().contains(filter)) filterList.add(ticket);
            else if (ticket.getShow() != null && ticket.getShow().getShowName().toLowerCase().contains(filter)) filterList.add(ticket);
            else if (ticket.getPhoneNumber() != null && ticket.getPhoneNumber().toLowerCase().contains(filter)) filterList.add(ticket);
            else if (ticket.getLocation() != null && ticket.getLocation().getName().toLowerCase().contains(filter)) filterList.add(ticket);
            else if (ticket.getSeat() != null && ticket.getSeat().toLowerCase().contains(filter)) filterList.add(ticket);
        }
        return filterList;
    }

    /**
     * Method that filters promotion on the given search criteria of the user on the given possible fields that is shown
     * in the promotion view
     * @param filter passes in the search word of the user as a string
     * @return returns a array list with the given search to override the database array list temporarily to just show
     * the given search criteria
     */
    public ArrayList<Promotion> filterPromotion(String filter) {
        filter = filter.toLowerCase();
        ArrayList<Promotion> filterList = new ArrayList<>();
        for (Promotion p : database.getPromotions()) {
            if (p.getShow() != null && p.getShow().getShowName().toLowerCase().contains(filter)) filterList.add(p);
            else if (p.getFrom() != null && p.getFrom().toLowerCase().contains(filter)) filterList.add(p);
            else if (p.getTo() != null && p.getTo().toLowerCase().contains(filter)) filterList.add(p);;
        }
        return filterList;
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
}
