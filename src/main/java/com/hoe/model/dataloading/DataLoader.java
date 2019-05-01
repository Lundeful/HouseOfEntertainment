package com.hoe.model.dataloading;

import com.hoe.model.Database;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public abstract class DataLoader {

    public abstract Database loadData(String filename) throws WrongCSVFormatException, CorruptFileException;
}
