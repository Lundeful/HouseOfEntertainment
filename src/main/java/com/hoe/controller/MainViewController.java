package com.hoe.controller;

import com.hoe.model.HoE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
    private VBox addShowsView;

    @FXML
    private HBox showsView;

    @FXML
    private Label status;

    // Main app (Model in MVC)
    private HoE hoe;

    public void initialize() {

        // Clear visibility on windows TODO: Remove this bit
        overviewWindow.setVisible(false);
        showsWindow.setVisible(false);
        locationsWindow.setVisible(false);
        ticketsWindow.setVisible(false);
        promotionsWindow.setVisible(false);
        saveDataWindow.setVisible(false);
        loadDataWindow.setVisible(false);
        helpWindow.setVisible(false);


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
        // TODO
    }

    public void saveData(ActionEvent event) {
        // TODO
    }

    public void addShow(ActionEvent actionEvent) {
        if(!addShowsView.isVisible()) {
            showsView.setVisible(false);
            addShowsView.setVisible(true);
        } else {
            showsView.setVisible(true);
            addShowsView.setVisible(false);
        }

        //hoe.addShow();
    }

    public void editShow(ActionEvent actionEvent) {
    }

    public void deleteShow(ActionEvent actionEvent) {
    }

    public void clearAddShowFields(ActionEvent event) {
    }

    public void closeAddShowMenu(ActionEvent event) {
    }
}
