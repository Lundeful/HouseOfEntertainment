package com.hoe.model;

import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.CSVSaver;
import com.hoe.model.datasaving.JobjSaver;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.InvalidFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;

import java.util.ArrayList;
import java.util.Random;


public class HoE {
    private Database database;
    private IDCreator id = new IDCreator();
    private Show s = new Show("","");


    public HoE() {
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
        show.setLocation(location);
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

    public boolean removeShow(Show s) {
        return database.removeShow(s);
    }
    public ArrayList<Show> getShows() {
        return database.getShows();
    }


    /**
     * Method that
     * @return returns true and saves the given file-type if it's successful,
     * returns false if there is any exceptions, leading the save to not being applied.
     * @param path
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
        } catch (Exception e){
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void load(String path) throws WrongCSVFormatException, InvalidFileException, CorruptFileException {
        JobjLoader jobj = new JobjLoader();
        CSVLoader csv = new CSVLoader();
        if (path.endsWith(".csv")) {
            database = csv.readData(path);
        } else if (path.endsWith(".ser")) {
            database = jobj.loadData(path);
        } else{
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
    public boolean addLocation(String id, String name, String typeOfLocation, int numberOfSeats) {
        Location l = new Location(id, name);
        l.setTypeOfLocation(formatInput(typeOfLocation));
        l.setNumberOfSeats(numberOfSeats);
        return database.addLocation(l);
    }
}
