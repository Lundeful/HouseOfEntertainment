package com.hoe.model.datasaving;

import com.hoe.controller.MainViewController;
import com.hoe.model.Database;

import java.io.FileWriter;
import java.io.IOException;

public abstract class DataSaver {

     public abstract void saveData(String filename, Database data, String fileChooser) throws IOException;


}
