package com.hoe.model.dataloading;

import com.hoe.model.*;
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
    public Database readData(String filename) throws WrongCSVFormatException {
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
            int counter = 0;
            Location location = new Location(data[counter], data[++counter]);
            location.setTypeOfLocation(data[++counter]);
            location.setNumberOfSeats(Integer.parseInt(data[++counter]));
            database.addLocation(location);
        } else {
            throw new WrongCSVFormatException("This is not the correct method to use to create this object");
        }
        //TODO: add location object to Database object
    }

    /**
     * This method creates a Show object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void showCreator(String[] data, Database database) throws WrongCSVFormatException {
        //TODO: add a method that locates a location object that already exists
        if(data.length == 10) {
            int counter = 0;
            Show show = new Show(data[counter], data[++counter]);
            show.setShowType(data[++counter]);
            show.setLocationID(data[++counter]);
            show.setDate(data[++counter]);
            show.setTime(data[++counter]);
            show.setTicketPrice(data[++counter]);
            show.setAvailableTickets(Integer.parseInt(data[++counter]));
            show.setProgram(data[++counter]);
            show.setLocation(database.findLocation(show.getLocationID()));
            database.addShow(show);
        } else {
            throw new WrongCSVFormatException("This is not the correct method to use to create this object");
        }
    }

    /**
     * This method creates a Promotion object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void promotionCreator(String[] data, Database database) throws WrongCSVFormatException {
        //TODO: add a method that locates a promotion object that already exists
        int counter = 0;
        if(data.length == 4) {
            Promotion promotion = new Promotion(data[counter], data[++counter]);
            promotion.setFrom(data[++counter]);
            promotion.setTo(data[++counter]);
            database.addPromotion(promotion);
        } else {
            throw new WrongCSVFormatException("This is not the correct method to use to create this object");
        }
    }

    /**
     * This method creates a ContactPerson object based on the input from the String[] with information.
     * @param data The String[] with the data.
     */
    private void contactPersonCreator(String[] data, Database database) throws WrongCSVFormatException {
        int counter = 0;
        if(data.length == 7) {
            ContactPerson contactPerson = new ContactPerson(data[counter]);
            contactPerson.setName(data[++counter]);
            contactPerson.setPhoneNumber(data[++counter]);
            contactPerson.setEmail(data[++counter]);
            contactPerson.setWebsite(data[++counter]);
            contactPerson.setAffiliation(data[++counter]);
            contactPerson.setOther(data[++counter]);
            database.addContact(contactPerson);
        } else {
            throw new WrongCSVFormatException("This is not the correct method to use to create this object");
        }
    }

    private void ticketCreator(String[] data, Database database) throws WrongCSVFormatException {
        int counter = 0;
        if(data.length == 7){
            Ticket ticket = new Ticket(data[counter], data[++counter], Integer.parseInt(data[++counter]));
            ticket.setDate(data[++counter]);
            ticket.setPhoneNumber(data[++counter]);
            ticket.setSeatNumber(Integer.parseInt(data[++counter]));
            database.addTicket(ticket);
        } else {
            throw new WrongCSVFormatException("This is not the correct method to use to create this object");
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
