package com.hoe.model.datasaving;

import com.hoe.model.Database;
import java.io.*;


/**
 * Serializable reader class that saves the state of a given java object
 */
public class JobjSaver extends DataSaver implements Serializable {
    // TODO(1): Add comments
    // TODO(2): Testing

    @Override
    public void saveData(String filename, Database data) throws IOException {
        readObject(data, filename);
    }

    /**
     * This method saves a object of its current state
     * It's used to save the Database class as this is where the information is stored.
     * @param data Get's an object of the Database-class to store the information of the current array/arrays
     */
    public void readObject(Database data, String filename){

        try {
            // Saves the state of the given object to a "SaveFile.ser"
            FileOutputStream fileOutputStream = new FileOutputStream(filename, false);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(data);
            out.close();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
