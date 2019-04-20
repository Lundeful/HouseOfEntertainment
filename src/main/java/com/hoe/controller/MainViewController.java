package com.hoe.controller;

import com.hoe.model.HoE;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class MainViewController {

    @FXML
    private Button currentButton;

    @FXML
    private Button overviewButton;

    @FXML
    private Button showsButton;

    @FXML
    private Button locationsButton;

    @FXML
    private Button ticketsButton;

    @FXML
    private Button promotionsButton;

    @FXML
    private Button saveDataButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button helpButton;

    @FXML
    private AnchorPane currentView;

    @FXML
    private AnchorPane selectMenuWindow;

    @FXML
    private AnchorPane overviewWindow;

    @FXML
    private AnchorPane showsWindow;

    @FXML
    private AnchorPane locationsWindow;

    @FXML
    private AnchorPane ticketsWindow;

    @FXML
    private AnchorPane promotionsWindow;

    @FXML
    private AnchorPane saveDataWindow;

    @FXML
    private AnchorPane loadDataWindow;

    @FXML
    private AnchorPane helpWindow;

    private HoE hoe;

    public void initialize() {
        currentView = overviewWindow;
        currentView.setVisible(true);
        currentButton = overviewButton;
        overviewButton.getStyleClass().add("mainMenuButtonHighlighted");
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
}
