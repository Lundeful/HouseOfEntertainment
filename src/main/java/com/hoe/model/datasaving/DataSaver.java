package com.hoe.model.datasaving;

import com.hoe.model.Database;

import java.io.FileWriter;
import java.io.IOException;

public abstract class DataSaver {

    public void saveData(String filename, Database data) throws IOException {

        FileWriter fileWriter = new FileWriter(filename);

        fileWriter.write(databaseToString(data));
        fileWriter.flush();
        fileWriter.close();
    }

    protected abstract String databaseToString(Database data);
}
