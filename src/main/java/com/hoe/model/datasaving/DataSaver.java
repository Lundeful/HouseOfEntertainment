package com.hoe.model.datasaving;

import com.hoe.model.Database;

public abstract class DataSaver {

    public abstract void saveData(String filename, Database data);
}
