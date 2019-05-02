package com.hoe.model.datasaving;

import com.hoe.model.Database;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.nio.file.NoSuchFileException;


/**
 * Serializable reader class that saves the state of a given java object
 */
public class JobjSaver extends DataSaver implements Serializable {


    /**
     * Method used to save the state of the program if the user wants to
     * Method utilizes Serializable to save the state of the Database class, with it's object.
     * @param filename Passes in the filename that is given by the user.
     * @param data Passes in the given Database object, to save the entire program at the time of the given usage
     *             of the method.
     */
    @Override
    public void saveData(String filename, Database data){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename, true);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(data);
            out.close();
            fileOutputStream.close();
        }catch (NoSuchFileException e){
            System.err.println("Could not find file!");
        }catch (IOException e){
            System.err.println("Could not find given file, caused by reason: " + e.getCause());
        }
    }
}
