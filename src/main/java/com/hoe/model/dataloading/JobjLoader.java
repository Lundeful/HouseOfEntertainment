package com.hoe.model.dataloading;

import com.hoe.model.Database;
import com.hoe.model.exceptions.CorruptFileException;

import java.io.*;

/**
 * Deserializable loader class that loads the previous state of the program
 */

public class JobjLoader extends DataLoader implements Serializable {
    Database data = null;

    /**
     * This method loads a object of the state that is was saved from @JobjSaver.java
     * It's used to load the Database class as this is where the needed information will be stored.
     * @param filename passes in a given filename that needs to be loaded to get the needed information
     *      of previously state of the program
     * @return returns the data object that was saved from the @JobjSaver.java class
     * @throws CorruptFileException
     */
    @Override
    public Database loadData(String filename) throws CorruptFileException {
        try{
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            data = (Database) inputStream.readObject();
            fileInputStream.close();
            inputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CorruptFileException("Serializable file is corrupt");
        }
    }
}
