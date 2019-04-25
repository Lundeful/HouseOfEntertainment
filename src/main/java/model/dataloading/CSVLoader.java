package model.dataloading;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
* This is the CSVLoader class. This class reads a CSV file and uses the unique ID to determine what object it is
* and then create and save the object to the DataBase.
*/
public class CSVLoader {

private final String LINE_SPLITTER = "\\|";
private int counter = 0;

/**
 * This method is the inherited method from the DataLoader class and its purpose is to read in a CSV-file and
 * then correctly convert everything back to the right object.
 * @param filename The filepath to the CSV-file.
 */
public Database readData(String filename){
    Database database = new Database();
    try {
        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = fileReader.readLine()) != null){
                String[] readIn = line.split(LINE_SPLITTER);
                if(readIn.length > 0){
                    if(!readIn[counter].equals("ID")){
                        //TODO: Use ID checker to determine what Object it is
                    }
                }
            }
    }catch (IOException e){
        e.printStackTrace();
    }
    return database;
    }

    /**
     * This method creates a Location object based on the input from the String[] with information.
     * @param counter A counter that is used to index what to extract from the array.
     * @param data The String[] with the data.
     */
    private void locationCreator(int counter, String[] data, Database database){
        if(data.length <= 4) {
            Location location = new Location(data[counter], data[++counter]);
            location.setTypeOfLocation(data[++counter]);
            location.setNumberOfSeats(Integer.parseInt(data[++counter]));
            counter = 0;
            database.addLocation(location);
        } else {
            throw new UnsupportedOperationException("This is not the correct method to use to create this object");
        }
        //TODO: add location object to Database object
    }

    /**
     * This method creates a Show object based on the input from the String[] with information.
     * @param counter A counter that is used to index what to extract from the array.
     * @param data The String[] with the data.
     */
    private void showCreator(int counter, String[] data, Database database){
        //TODO: add a method that locates a location object that already exists
        if(data.length<=7) {
            Show show = new Show(data[counter], data[++counter], Integer.parseInt(data[++counter]));
            show.setShowType(data[++counter]);
            show.setTime(data[++counter]);
            show.setProgram(data[++counter]);
            counter = 0;
            database.addShow(show);
        } else {
            throw new UnsupportedOperationException("This is not the correct method to use to create this object");
        }
    }

    /**
     * This method creates a Promotion object based on the input from the String[] with information.
     * @param counter A counter that is used to index what to extract from the array.
     * @param data The String[] with the data.
     */
    private void promotionCreator(int counter, String[] data, Database database){
        //TODO: add a method that locates a promotion object that already exists
        if(data.length<=4) {
            Promotion promotion = new Promotion(data[counter], data[++counter]);
            promotion.setFrom(data[++counter]);
            promotion.setTo(data[++counter]);
            counter = 0;
            database.addPromotion(promotion);
        } else {
            throw new UnsupportedOperationException("This is not the correct method to use to create this object");
        }
    }

    /**
     * This method creates a ContactPerson object based on the input from the String[] with information.
     * @param counter A counter that is used to index what to extract from the array.
     * @param data The String[] with the data.
     */
    private void contactPersonCreator(int counter, String[] data, Database database){
        if(data.length <= 7) {
            ContactPerson contactPerson = new ContactPerson(data[counter]);
            contactPerson.setName(data[++counter]);
            contactPerson.setPhoneNumber(data[++counter]);
            contactPerson.setEmail(data[++counter]);
            contactPerson.setWebsite(data[++counter]);
            contactPerson.setAffiliation(data[++counter]);
            contactPerson.setOther(data[++counter]);
            counter = 0;
            database.addContact(contactPerson);
        } else {
            throw new UnsupportedOperationException("This is not the correct method to use to create this object");
        }
    }
}
