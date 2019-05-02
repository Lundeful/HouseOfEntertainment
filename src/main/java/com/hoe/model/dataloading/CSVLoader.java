package com.hoe.model.dataloading;

import com.hoe.model.*;
import com.hoe.model.exceptions.NotEnoughSeatsException;
import com.hoe.model.exceptions.WrongCSVFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the CSVLoader class. This class reads a CSV file and uses the unique ID to determine what object it is
 * and then create and save the object to the DataBase.
 */
public class CSVLoader {

    private final String LINE_SPLITTER = "\\|";
    private IDCreator id = new IDCreator();

    /**
     * This method is the inherited method from the DataLoader class and its purpose is to read in a CSV-file and
     * then correctly convert everything back to the right object.
     * @param filename The filepath to the CSV-file.
     */
    public Database readData(String filename) throws WrongCSVFormatException, NotEnoughSeatsException {
        Database database = new Database();
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = fileReader.readLine()) != null){
                String[] readIn = line.split(LINE_SPLITTER, -1);
                if(readIn.length > 0){
                    char ch = readIn[0].charAt(0);
                    if(Character.isDigit(ch)){
                        switch(id.getObject(readIn[0])) {
                            case 3:
                                ticketCreator(readIn, database);
                                break;
                            case 4:
                                locationCreator(readIn, database);
                                break;
                            case 5:
                                showCreator(readIn, database);
                                break;
                            case 6:
                                promotionCreator(readIn, database);
                                break;
                            case 7:
                                contactPersonCreator(readIn, database);
                                break;
                        }
                    }
                }
            }
            addTicketsToShow(database);
        }catch (IOException e){
            e.printStackTrace();
        }
        return database;
    }

    /**
     * This method creates a Location object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void locationCreator(String[] data, Database database) throws WrongCSVFormatException {
        if(data.length == 4) {
            Location location = new Location(data[0], data[1]);
            location.setTypeOfLocation(data[2]);
            location.setNumberOfSeats(Integer.parseInt(data[3]));
            database.addLocation(location);
        } else {
            throw new WrongCSVFormatException("Corrupt CSV-file");
        }
        //TODO: add location object to Database object
    }

    /**
     * This method creates a Show object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void showCreator(String[] data, Database database) throws WrongCSVFormatException, NotEnoughSeatsException {
        //TODO: add a method that locates a location object that already exists
        if(data.length == 10) {
            Show show = new Show(data[0], data[1]);
            show.setShowType(data[2]);
            show.setLocation(database.findLocation(data[3]));
            show.setDate(data[4]);
            show.setTime(data[5]);
            show.setTicketPrice(data[6]);
            show.setAvailableTickets(Integer.parseInt(data[7]));
            show.setProgram(data[8]);
            show.setContactPerson(database.findContactPerson(data[9]));
            database.addShow(show);
        } else {
            throw new WrongCSVFormatException("Corrupt CSV-file");
        }
    }

    /**
     * This method creates a Promotion object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void promotionCreator(String[] data, Database database) throws WrongCSVFormatException {
        //TODO: add a method that locates a promotion object that already exists
        if(data.length == 4) {
            Promotion promotion = new Promotion(data[0],database.findShow(data[1]));
            promotion.setTo(data[2]);
            promotion.setFrom(data[3]);
            database.addPromotion(promotion);
        } else {
            throw new WrongCSVFormatException("Corrupt CSV-file");
        }
    }

    /**
     * This method creates a ContactPerson object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void contactPersonCreator(String[] data, Database database) throws WrongCSVFormatException {
        if(data.length == 7) {
            ContactPerson contactPerson = new ContactPerson(data[0],data[1],data[2]);
            contactPerson.setEmail(data[3]);
            contactPerson.setWebsite(data[4]);
            contactPerson.setAffiliation(data[5]);
            contactPerson.setOther(data[6]);
            database.addContact(contactPerson);
        } else {
            throw new WrongCSVFormatException("Corrupt CSV-file");
        }
    }

    private void ticketCreator(String[] data, Database database) throws WrongCSVFormatException {
        int counter = 0;
        if(data.length == 4){
            Ticket ticket = new Ticket(data[0], database.findShow(data[1]), data[2],data[3]);
            database.addTicket(ticket);
        } else {
            throw new WrongCSVFormatException("Corrupt CSV-file");
        }
    }

    private void addTicketsToShow(Database data){
        for(Show s : data.getShows()){
            ArrayList<Ticket> ticket = new ArrayList<>();
            for(Ticket t : data.getTickets()){
                if(t.getShowID().equals(s.getShowID())){
                    ticket.add(t);
                }
            }
            s.setSoldTickets(ticket);
        }
    }
}
