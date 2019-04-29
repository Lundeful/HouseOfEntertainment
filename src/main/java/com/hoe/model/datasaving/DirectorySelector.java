package com.hoe.model.datasaving;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DirectorySelector {
    public String directoryChooser(){
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel file", "*.csv"),
                new FileChooser.ExtensionFilter("Serializable file", "*.ser"));
        chooser.setTitle("Select a file");
        File selected = chooser.showSaveDialog(new Stage());

        return selected.toString();
    }
}
