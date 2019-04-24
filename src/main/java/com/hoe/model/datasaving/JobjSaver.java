package com.hoe.model.datasaving;

import com.hoe.model.Database;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;

public class JobjSaver extends DataSaver implements Serializable {
    // TODO(1): Add comments

    @Override
    public void saveData(String filename, Database data) throws IOException {
        readObjecet(data);
    }

    /**
     * This method saves a object of its current state
     * It's used to save the Database class as this is where the information is stored.
     * @param data Get's an object of the Database-class to store the information of the current array/arrays
     */
    public void readObjecet(Database data){

        // COMMENTS HERE
        DirectoryChooser chooseDir = new DirectoryChooser();
        File jarDir = new File(".");
        chooseDir.setInitialDirectory(jarDir);
        File selectedDir = chooseDir.showDialog(new Stage());
        chooseDir.setTitle("Selet where to save your file");

        if (selectedDir == null){
            return;
        }

        try {
            // Saves the state of the given object to a "SaveFile.ser"
            FileOutputStream fileOutputStream = new FileOutputStream(selectedDir.getAbsolutePath() + "/SaveFile.ser", false);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(data);
            out.close();
            fileOutputStream.close();
        }catch (IOException e){
            // Alert used to give user information if the save possibly fails.
            Alert saveFailure = new Alert(Alert.AlertType.ERROR);
            saveFailure.setTitle("Failed to Save");
            saveFailure.setHeaderText("Error");
            saveFailure.setContentText("The file could not be saved");
            saveFailure.showAndWait();
            e.printStackTrace();
        }
    }
}
