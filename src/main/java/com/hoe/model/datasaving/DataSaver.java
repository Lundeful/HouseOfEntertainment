package com.hoe.model.datasaving;

import com.hoe.controller.MainViewController;
import com.hoe.model.Database;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class DataSaver {

     public abstract void saveData(String filename, Database data) throws IOException;
}
