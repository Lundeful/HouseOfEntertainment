package com.hoe.model.dataloading;

import com.hoe.model.Database;
import java.io.*;

/**
 * Deserializable loader class that loads the previous state of the program
 */

public class JobjLoader extends DataLoader implements Serializable {
    Database data = null;

    /**
     * This method loads a object of the state that is was saved from @JobjSaver.java
     * It's used to load the Database class as this is where the needed information will be stored.
     * @param filename Passes in a given filename that needs to be loaded to get the needed information
     *                 of previously state of the program
     */
    @Override
    public void loadData(String filename) {
        try{

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            data = (Database) inputStream.readObject();
            fileInputStream.close();
            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException c){
            System.out.println("Object not found!");
            c.printStackTrace();
        }
    }
}
