package com.hoe.model.datasaving;

import model.*;

import java.io.FileWriter;
import java.io.IOException;

public abstract class DataSaver {

    public abstract void saveData(String filename, Database data);
}
