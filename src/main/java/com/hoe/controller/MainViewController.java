package com.hoe.controller;

import com.hoe.model.HoE;
import com.hoe.model.Location;
import com.hoe.model.Show;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;


public class MainViewController {

    // Main menu buttons
    @FXML
    private Button currentButton, overviewButton, showsButton, locationsButton, ticketsButton, promotionsButton,
            saveDataButton, loadDataButton, helpButton;

    // The different panes / views in this app
    @FXML
    private AnchorPane currentView, selectMenuWindow, overviewWindow, showsWindow, locationsWindow, ticketsWindow,
            promotionsWindow, saveDataWindow, loadDataWindow, helpWindow;

    // Add shows menu
    @FXML
    private VBox addShowsView;

    // Add show input form view
    @FXML
    private TextField addShowTextFieldName, addShowTextFieldType, addShowTextFieldDate, addShowTextFieldTime,
            addShowTextFieldTicketPrice;
    @FXML
    private TextArea addShowFieldProgram;
    @FXML
    private ChoiceBox<Location> addShowChoiceBoxLocation;

    // View with list of shows
    @FXML
    private HBox showsView;

    @FXML
    private TableView<Show> showsTableView;
    @FXML
    private TableColumn<Show, String> tableColumnShow, tableColumnDate;

    @FXML
    private TableColumn<Location, String> tableColumnLocation;

    @FXML
    private Label addShowFormNameError;

    // Statusbar at the bottom of screen
    @FXML
    private Label status;

    // Main app object
    private HoE hoe;

    public void initialize() {

        // Clear visibility on windows TODO: Remove these or extract to method
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
        // hoe.loadPreviousState(); // TODO: Enable after continuous save/load is up and running

        hoe.addShow("Harry potter", "Movie", "28-10-2019", "", new Location("temp-ID", "Big hall"), "", "");
        hoe.addShow("Cats", "Stage show", "", "Midnight", new Location("temp-ID", "Small hall"), "", "");
        hoe.addShow("Bohemian Rhapsody", "Movie", "", "", new Location("temp-ID", "Outside"), "", "");
        hoe.addShow("AC/DC", "Concert", "", "", new Location("Temp-id", "Your mom"), "", "");

        for (int i = 0; i < 100; i++) {
            hoe.addShow("Show " + i, "Type " + i, "Date " + i, "Time " + i, new Location("temp-id", "Location " + i%6), "", "");
        }
        initializeShows();
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

    public void showAddShowMenu(ActionEvent actionEvent) {
        if(!addShowsView.isVisible()) {
            addShowsView.setVisible(true);
        } else {
            addShowsView.setVisible(false);
        }
    }

    public void showEditShow(ActionEvent actionEvent) {
    }

    public void deleteShow(ActionEvent actionEvent) {
    }

    public void clearAddShowFields(ActionEvent event) {
        addShowTextFieldName.clear();
        addShowTextFieldType.clear();
        addShowTextFieldDate.clear();
        addShowTextFieldTime.clear();
        addShowChoiceBoxLocation.setValue(null);
        addShowTextFieldTicketPrice.clear();
        addShowFieldProgram.clear();
    }

    public void closeAddShowMenu(ActionEvent event) {
        addShowsView.setVisible(false);
    }

    public void submitShowForm(ActionEvent actionEvent) {
        if (addShowTextFieldName.getText().trim().equals("")) { // If name field is empty
            addShowFormNameError.setVisible(true);
            addShowFormNameError.setOpacity(1);
            FadeTransition ft = new FadeTransition(Duration.seconds(1), addShowFormNameError);
            ft.setFromValue(1);
            ft.setToValue(0);
            ft.setDelay(Duration.seconds(3));
            ft.play();
        } else {
        hoe.addShow(addShowTextFieldName.getText(), addShowTextFieldType.getText(), addShowTextFieldDate.getText(),
                    addShowTextFieldTime.getText(), addShowChoiceBoxLocation.getValue(),
                    addShowTextFieldTicketPrice.getText(), addShowFieldProgram.getText());

        updateShowsList();
        }
    }

    private void updateShowsList() {
        ObservableList<Show> showData = FXCollections.observableArrayList(hoe.getShows());
        showData.setAll(hoe.getShows());
        showsTableView.setItems(showData);
    }

    private void initializeShows() {
        tableColumnShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        updateShowsList();
    }
}
