package com.hoe.model.datasaving;

import com.hoe.model.Database;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.nio.file.NoSuchFileException;


/**
 * Serializable reader class that saves the state of a given java object
 */
public class JobjSaver extends DataSaver implements Serializable {

    // TODO: Make the seri class make a fixed *.ser file.

    @Override
    public void saveData(String filename, Database data, String fileChooser){
        try {
            DirectoryChooser dirChooser = new DirectoryChooser();
            File givenDir = new File(fileChooser);
            dirChooser.setInitialDirectory(givenDir);
            FileOutputStream fileOutputStream = new FileOutputStream(filename, false);
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
