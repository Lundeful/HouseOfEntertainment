package com.hoe.model.dataloading;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileSelecter {

    public String fileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel file", "*.csv"),
                new FileChooser.ExtensionFilter("Serializable file", "*.ser"));
        File selected = chooser.showOpenDialog(new Stage());

        return selected.toString();
    }
}
