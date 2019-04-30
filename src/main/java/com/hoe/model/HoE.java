package com.hoe.model;

import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.dataloading.FileSelecter;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.CSVSaver;
import com.hoe.model.datasaving.DirectorySelector;
import com.hoe.model.datasaving.JobjSaver;
import javafx.scene.control.skin.TableHeaderRow;

import java.util.ArrayList;

public class HoE {
    private Database database;
    IDCreator id = new IDCreator();
    Show s = new Show("","");

    public HoE() {
        database = new Database();
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
     */
    public boolean save() {
        try {
            DirectorySelector f = new DirectorySelector();
            JobjSaver jobj = new JobjSaver();
            CSVSaver csv = new CSVSaver();
            String path = f.directoryChooser();
            System.out.println("Starting Thread");
            new Thread(() -> {
                if (path.endsWith(".csv")) {
                    csv.saveData(path, database);
                } else if (path.endsWith(".ser")) {
                    jobj.saveData(path, database);
                }
                System.out.println("Save Complete");
                Thread.currentThread().interrupt();
                System.out.println("Thread shutdown");
            }).start();
            return true;
        } catch (Exception e){
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public Boolean load() {
        try {
            FileSelecter f = new FileSelecter();
            JobjLoader jobj = new JobjLoader();
            CSVLoader csv = new CSVLoader();
            String path = f.fileChooser();
            System.out.println("Starting Thread");
            new Thread(() -> {
                if(path.endsWith(".csv")){
                    database = csv.readData(path);
                } else if(path.endsWith(".ser")){
                    database = jobj.loadData(path);
                }
                System.out.println("Loading Complete");
                Thread.currentThread().interrupt();
                System.out.println("Thread shutdown");
            }).start();
            return true;
        } catch (Exception e){
            Thread.currentThread().interrupt();
            return false;
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
