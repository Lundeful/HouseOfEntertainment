package com.hoe.model.dataloading;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileSelecter {

    public String chooseFile(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serializable file", "*.ser"),
                new FileChooser.ExtensionFilter("CSV file", "*.csv"));
        File selected = chooser.showOpenDialog(new Stage());

        if(selected == null) return "";
        return selected.toString();
    }
}
