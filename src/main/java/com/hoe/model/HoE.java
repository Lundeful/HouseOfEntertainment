package com.hoe.model;

import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.dataloading.FileSelecter;
import com.hoe.model.dataloading.JobjLoader;
import com.hoe.model.datasaving.CSVSaver;
import com.hoe.model.datasaving.JobjSaver;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;

import java.util.ArrayList;
import java.util.Random;

public class HoE {
    private Database database;
    IDCreator id = new IDCreator();
    Show s = new Show("","");
    Location l = new Location("","");

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

    public void generateTestObjects() {
        Location l1 = new Location(id.randomKeyGen(l), "Big Hall");
        l1.setNumberOfSeats(578);
        l1.setTypeOfLocation("Theatre");

        Location l2 = new Location(id.randomKeyGen(l), "Small stage");
        l2.setTypeOfLocation("Theatre");
        l2.setNumberOfSeats(300);

        Location l3 = new Location(id.randomKeyGen(l), "Supreme");
        l2.setNumberOfSeats(258);
        l2.setTypeOfLocation("Movie theatre");

        Location l4 = new Location(id.randomKeyGen(l), "IMAX");
        l4.setTypeOfLocation("Movie Theatre");
        l4.setNumberOfSeats(420);

        database.addLocation(l1);
        database.addLocation(l2);
        database.addLocation(l3);
        database.addLocation(l4);
        /*addShow("Harry potter", "Movie", "28-10-2019", "", l4, "120", "");
        addShow("Cats", "Stage show", "", "Midnight", l2, "260", "");
        addShow("Bohemian Rhapsody", "Movie", "Mid-night", "", l4, "190", "");
        addShow("AC/DC", "Concert", "", "", l1, "499", ""); */
        String[] randomWord = generateRandomWords(1000000);
        for(int i = 0; i < 1000; i++){
            int loc = i%4;
            addShow(randomWord[i],randomWord[i],randomWord[i],randomWord[i],database.getLocations().get(loc),"100",randomWord[i]);
        }
       /* for (int i = 0; i < 1000; i++) {
            addShow("Show " + i, "Type " + i, "Date " + i, "Time " + i, new Location("temp-id", "Location " + i%6), String.valueOf(ThreadLocalRandom.current().nextInt(100, 501)), "");
        } */
    }

    public String[] generateRandomWords(int numberOfWords){
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
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
