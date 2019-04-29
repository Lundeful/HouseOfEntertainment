package com.hoe.model.dataloading;

import com.hoe.model.Database;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public abstract class DataLoader {

    public abstract Database loadData(String filename);
}
