package com.hoe.model.datasaving;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DirectorySelector {
    public String directoryChooser(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select a file");
        File selected = chooser.showDialog(new Stage());

        return selected.toString();
    }
}
