package com.hoe.model.datasaving;

import com.hoe.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVSaver extends DataSaver {
    /**
     * The CSVSaver class si the class that takes every object from the Database and saves it in raw CSV format.
     * This class extends the DataSaver class.
     */

    private final String NEW_LINE = "\n";

    /**
     * This method is the inherited method from the DataSaver class and its purpose is to take the DataBase object
     * and write to the FileWriter and save it to a file.
     * @param filename The filename for the savefile.
     * @param data The DataBase object that includes everything that is to be saved
     */
    @Override
    public void saveData(String filename, Database data) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            fileWriter.write(databaseToString(data));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method converts everything to a single long string with correct CSV-format. This is to be used in
     * the saveData method and gives it the string FileWriter needs.
     * @param data The DataBase object that is converted to a single String
     * @return Returns the single String that is in the correct CSV-format.
     */
    private String databaseToString(Database data) {
        ArrayList<Location> locations = data.getLocations();
        ArrayList<ContactPerson> contactPerson = data.getContacts();
        ArrayList<Show> shows = data.getShows();
        ArrayList<Promotion> promotions = data.getPromotions();
        ArrayList<Ticket> tickets = data.getTickets();

        StringBuilder line = new StringBuilder();

        //Making header for Locations first and then saving all the objects in the Arraylist
        String header_Location = "ID|Name|Type of Location|Number of Seats";
        line.append(header_Location);
        line.append(NEW_LINE);

        for(Location location : locations){
            line.append(location.toCSVString());
            line.append(NEW_LINE);
        }

        //Adding ContactPerson to the string
        String header_ContactPerson = "ID|Name|Phone Number|Email|Website|Affiliation|Other";
        line.append(header_ContactPerson);
        line.append(NEW_LINE);

        for(ContactPerson contact : contactPerson){
            line.append(contact.toCSVString());
            line.append(NEW_LINE);
        }

        //Adding shows to the string
        String header_Show = "ID|Show Type|Program|Location ID|Time|Price|Available Tickets|Sold Tickets";
        line.append(header_Show);
        line.append(NEW_LINE);

        for(Show show : shows){
            line.append(show.toCSVString());
            line.append(NEW_LINE);
        }

        //Adding promotions to the string
        String header_promotion = "ID|Show ID|Time From|Time To";
        line.append(header_Show);
        line.append(NEW_LINE);

        for(Promotion promotion : promotions){
            line.append(promotion.toCSVString());
            line.append(NEW_LINE);
        }

        //Getting all the tickets from every show
        for(Show show : shows){
            tickets.addAll(show.getSoldTickets());
        }

        //Adding tickets to the string
        String header_ticket = "ID|Show ID|Price|Date|Phone Number|Seat Number";
        line.append(header_ticket);
        line.append(NEW_LINE);
        for(Ticket t : tickets){
            line.append(t.toCSVString());
            line.append(NEW_LINE);
        }

        return line.toString();
    }
}
