package com.hoe.controller;

import com.hoe.model.HoE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainViewController {

    // Main menu buttons
    @FXML
    private Button currentButton, overviewButton, showsButton, locationsButton, ticketsButton, promotionsButton,
            saveDataButton, loadDataButton, helpButton;

    // The different panes / views in this app
    @FXML
    private AnchorPane currentView, selectMenuWindow, overviewWindow, showsWindow, locationsWindow, ticketsWindow,
            promotionsWindow, saveDataWindow, loadDataWindow, helpWindow;

    @FXML
    private Label status;

    // Main app (Model in MVC)
    private HoE hoe;

    public void initialize() {
        currentView = selectMenuWindow;
        currentView.setVisible(true);
        currentButton = overviewButton;
        hoe = new HoE();
    }

    public void overviewClicked() {
        menuClicked(overviewWindow, overviewButton);
    }

    public void showsClicked() {
        menuClicked(showsWindow, showsButton);
    }

    public void locationsClicked() {
        menuClicked(locationsWindow, locationsButton);
    }

    public void ticketsClicked() {
        menuClicked(ticketsWindow, ticketsButton);
    }

    public void promotionsClicked() {
        menuClicked(promotionsWindow, promotionsButton);
    }

    public void saveDataClicked() {
        menuClicked(saveDataWindow, saveDataButton);
    }

    public void loadDataClicked() {
        menuClicked(loadDataWindow, loadDataButton);
    }

    public void helpClicked() {
        menuClicked(helpWindow, helpButton);
    }

    private void menuClicked(AnchorPane ap, Button b) {
        currentView.setVisible(false);
        currentButton.getStyleClass().remove("mainMenuButtonHighlighted");
        b.getStyleClass().add("mainMenuButtonHighlighted");
        ap.setVisible(true);
        currentView = ap;
        currentButton = b;
    }


    public void chooseSaveFile(ActionEvent event) {
        fileChooser();
    }

    public void saveData(ActionEvent event) {
        // TODO
    }

    // TODO(1) Fix exceptions,
    public void fileChooser() {
        Stage stage = new Stage();
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel file", "*.csv"),
                new FileChooser.ExtensionFilter("Serializable file", "*.ser"));
        File selected = chooser.showOpenDialog(stage);
        if (selected != null) {
            Path pathToFile = (Path) selected.toPath();
        }
    }
}
