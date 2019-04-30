package com.hoe.controller;

import com.hoe.model.*;
import com.hoe.model.exceptions.NotEnoughSeatsException;
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

import java.util.function.UnaryOperator;


public class MainViewController {

    // Main menu buttons
    @FXML
    private Button currentButton, overviewButton, showsButton, locationsButton, ticketsButton, contactsButton,
            promotionsButton,
            saveDataButton, loadDataButton, helpButton;

    // The different panes / views in this app
    @FXML
    private AnchorPane currentView, selectMenuWindow, overviewWindow, showsWindow, locationsWindow, ticketsWindow,
            contactsWindow, promotionsWindow, saveDataWindow, loadDataWindow, helpWindow;

    /*
    ==============================
    Add shows menu
    ==============================
     */
    @FXML
    private VBox addShowsView;

    // Add show input form view
    @FXML
    private TextField addShowTextFieldName, addShowTextFieldType, addShowTextFieldDate, addShowTextFieldTime,
            addShowTextFieldTicketPrice;
    @FXML
    private TextArea addShowFieldProgram;
    @FXML
    private ComboBox<Location> addShowChoiceBoxLocation;

    // View with list of shows
    @FXML
    private HBox showsView;

    @FXML
    private TableView<Show> showsTableView;
    @FXML
    private TableColumn<Show, String> tableColumnShow, tableColumnDate;

    @FXML
    private TableColumn<Location, Location> tableColumnLocation; //
    // TODO: Byttet fra TableColumn<Location, String> Sjekk at det fungerer

    @FXML
    private Label addShowFormNameError, addShowFormLocationerror;

    /*
    ==============================
    Edit shows menu
    ==============================
     */

    @FXML
    private VBox editShowsView;

    @FXML
    private TextField editShowTextFieldName, editShowTextFieldType, editShowTextFieldDate, editShowTextFieldTime,
            editShowTextFieldTicketPrice;

    @FXML
    private TextArea editShowFieldProgram;

    @FXML
    private ComboBox<Location> editShowChoiceBoxLocation;

    @FXML
    private ComboBox<ContactPerson> editShowChoiceBoxContactPerson;


    /*
    ==============================
    Add locations menu
    ==============================
     */

    @FXML
    private VBox addLocationView;

    @FXML
    private TextField addLocationFieldName, addLocationFieldType, addLocationFieldSeats;

    @FXML
    private HBox locationsView;

    @FXML
    private TableView<Location> locationTableView;

    @FXML
    private TableColumn<Location, String> tableColumnLocationName, tableColumnLocationType, tableColumnLocationSeats;

    @FXML
    private Label addLocationNameError;

    /*
    ==============================
    Edit locations menu
    ==============================
     */

    @FXML
    private VBox editLocationView;


    /*
    ==============================
    Add tickets menu
    ==============================
     */

    @FXML
    private VBox addTicketView;

    @FXML
    private TableColumn<Ticket, String> tableColumnTicketShow, tableColumnTicketPhone,
            tableColumnTicketDate;

    @FXML
    private TableColumn<Location, String> tableColumnTicketLocation;

    @FXML
    private TableView<Ticket> ticketTableView;

    @FXML
    private ComboBox<Show> addTicketChoiceBoxShow;

    /*
    ==============================
    Edit tickets menu
    ==============================
     */
    @FXML
    private VBox editTicketView;


        /*
    ==============================
    Add contacts menu
    ==============================
     */

    @FXML
    private TableView<ContactPerson> contactsTableView;

    @FXML
    private VBox addContactView;

    /*
    ==============================
    Edit contacts menu
    ==============================
     */
    @FXML
    private VBox editContactView;



    /*
    ==============================
     */
    // Statusbar and notifications
    @FXML
    private Label status, notification;

    // Main app object
    private HoE hoe;
    private Show selectedShow;
    private Location selectedLocation;


    public void initialize() {
        hoe = new HoE();
        // hoe.loadPreviousState(); // TODO: Enable after continuous save/load is up and running

        initializeVisibility();
        addTextFormattingFilters();
        initializeLocations();
        initializeShows();
        initializeTickets();
    }

    private void addTextFormattingFilters() {
        UnaryOperator<TextFormatter.Change> intFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };
        addShowTextFieldTicketPrice.setTextFormatter(new TextFormatter<String>(intFilter));
        addLocationFieldSeats.setTextFormatter(new TextFormatter<String>(intFilter));
        // TODO: Format phone number input
    }

    private void initializeVisibility() {
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
        notification.setText("");
        notification.setVisible(false);
    }

    private void initializeShows() {
        tableColumnShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        updateShowsList();

        // Listen for selected show in table
        showsTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, s1, s2) -> {
            if (s1 != null && s2 != null && !s2.equals(s1)) {
                selectedShow = s2;
            }
        });
    }

    private void updateShowsList() {
        ObservableList<Show> showData = FXCollections.observableArrayList(hoe.getShows());
        showsTableView.setItems(showData);
        addTicketChoiceBoxShow.setItems(showData);
    }

    private void initializeLocations() {
        tableColumnLocationName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnLocationType.setCellValueFactory(new PropertyValueFactory<>("typeOfLocation"));
        tableColumnLocationSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        updateLocationsList();

        locationTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, s1, s2) -> {
            if (s1 != null && s2 != null && !s2.equals(s1)) {
                selectedLocation = s2;
            }
        });
    }

    private void updateLocationsList() {
        ObservableList<Location> locationsData = FXCollections.observableArrayList(hoe.getLocations());
        locationTableView.setItems(locationsData);
        addShowChoiceBoxLocation.setItems(locationsData);
    }

    private void initializeTickets() {
        tableColumnTicketShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnTicketDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnTicketLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColumnTicketPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        updateTicketsList();
    }

    private void updateTicketsList() {
        hoe.getTickets();
        try {
            ObservableList<Ticket> ticketsData = FXCollections.observableArrayList(hoe.getTickets());
            ticketTableView.setItems(ticketsData);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
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

    public void contactsClicked() {
        menuClicked(contactsWindow, contactsButton);
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

    public void toggleAddShowMenu(ActionEvent actionEvent) {
        if(!addShowsView.isVisible()) {
            addShowsView.setVisible(true);
            editShowsView.setVisible(false);
        } else {
            addShowsView.setVisible(false);
            editShowsView.setVisible(false);
        }
    }

    public void toggleEditShow(ActionEvent actionEvent) {
        if(!editShowsView.isVisible() && showsTableView.getSelectionModel().getSelectedItem() != null) {
            loadShowValues();
            editShowsView.setVisible(true);loadShowValues();
            addShowsView.setVisible(false);
        } else {
            editShowsView.setVisible(false);
            addShowsView.setVisible(false);
            // selectedShow = null;
            discardEditShow();
        }
    }

    private void loadShowValues() {
        Show selectedItem = showsTableView.getSelectionModel().getSelectedItem();
        editShowTextFieldName.setText(selectedItem.getShowName());
        editShowTextFieldType.setText(selectedItem.getShowType());
        editShowTextFieldDate.setText(selectedItem.getDate());
        editShowTextFieldTime.setText(selectedItem.getTime());
        editShowTextFieldTicketPrice.setText(selectedItem.getTicketPrice());
        editShowChoiceBoxLocation.setValue(selectedItem.getLocation());
        editShowChoiceBoxContactPerson.setValue(selectedItem.getContact());
        editShowFieldProgram.setText(selectedItem.getProgram());
    }

    public void deleteShow(ActionEvent actionEvent) {
        if (addShowsView.isVisible() || editShowsView.isVisible()) {
            addShowsView.setVisible(false);
            editShowsView.setVisible(false);
            return;
        }
        if (showsTableView.getSelectionModel().isEmpty()){
            displayNotification("No show selected");
            return;
        }
        Show s = showsTableView.getSelectionModel().getSelectedItem();
        if (hoe.removeShow(s)) {
            displayNotification("Show removed");
        } else {
            displayNotification("Error: Show not removed");
        }
        updateShowsList();
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
            fadeTransition(addShowFormNameError);
        } else if (addShowChoiceBoxLocation.getValue() == null) {
            fadeTransition(addShowFormLocationerror);
        } else {
        hoe.addShow(addShowTextFieldName.getText(), addShowTextFieldType.getText(), addShowTextFieldDate.getText(),
                    addShowTextFieldTime.getText(), addShowChoiceBoxLocation.getValue(),
                    addShowTextFieldTicketPrice.getText(), addShowFieldProgram.getText());
        updateShowsList();
        toggleAddShowMenu(actionEvent);
        displayNotification("Show added");
        }
    }

    private void displayNotification(String s) {
        notification.setText(s);
        fadeTransition(notification);
    }

    public void fadeTransition(Label label) {
        label.setVisible(true);
        label.setOpacity(1);
        FadeTransition ft = new FadeTransition(Duration.seconds(0.5), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setDelay(Duration.millis(1000));
        ft.play();
    }

    public void confirmEditShow(ActionEvent actionEvent) {
        if (addShowTextFieldName.getText().trim().equals("")) { // If name field is empty
            fadeTransition(addShowFormNameError);
        } else {
            try {
                selectedShow.setShowName(editShowTextFieldName.getText());
                selectedShow.setShowType(editShowTextFieldType.getText());
                selectedShow.setDate(editShowTextFieldDate.getText());
                selectedShow.setTime(editShowTextFieldTime.getText());
                selectedShow.setTicketPrice(editShowTextFieldTicketPrice.getText());
                selectedShow.setLocation(editShowChoiceBoxLocation.getValue());
                selectedShow.setContactPerson(editShowChoiceBoxContactPerson.getValue());
                selectedShow.setProgram(editShowFieldProgram.getText());

                updateShowsList();
                editShowsView.setVisible(false);
                displayNotification("Show edited");
            } catch (NotEnoughSeatsException e) {
                displayNotification("Error: " + e.getMessage());
                System.err.println(e.getMessage());
                // TODO Fyll ut exception-class
            }
        }
    }

    public void discardEditShow() {
        editShowsView.setVisible(false);
    }

    public void toggleAddLocationMenu(ActionEvent event) {
        if(!addLocationView.isVisible()) {
            addLocationView.setVisible(true);
            editLocationView.setVisible(false);
        } else {
            addLocationView.setVisible(false);
            editLocationView.setVisible(false);
        }
    }

    public void toggleEditLocation(ActionEvent event) {
        if(!editLocationView.isVisible()) {
            editLocationView.setVisible(true);
            addLocationView.setVisible(false);
        } else {
            editLocationView.setVisible(false);
            addLocationView.setVisible(false);
        }
    }

    public void deleteLocation(ActionEvent event) {
        if (locationTableView.getSelectionModel().isEmpty()){
            displayNotification("No location selected");
            return;
        }
        Location l = locationTableView.getSelectionModel().getSelectedItem();
        if (hoe.removeLocation(l)) {
            displayNotification("Location removed");
        } else {
            displayNotification("Error: Location not removed");
        }
        updateLocationsList();
    }

    public void submitLocationForm(ActionEvent event) {
        if (addLocationFieldName.getText().trim().equals("")) { // If name field is empty
            fadeTransition(addLocationNameError);
        } {
            hoe.addLocation(addLocationFieldName.getText(), addLocationFieldType.getText(),
                    Integer.parseInt(addLocationFieldSeats.getText()));
            updateLocationsList();
            toggleAddLocationMenu(event);
            displayNotification("Location added");
        }
    }

    public void clearAddLocationFields(ActionEvent event) {
    }

    public void closeAddLocationMenu(ActionEvent event) {
        addLocationView.setVisible(false);
    }

    public void confirmEditLocation(ActionEvent event) {
    }

    public void discardEditLocation(ActionEvent event) {
    }

    public void toggleAddTicket(ActionEvent event) {
        if(!addTicketView.isVisible()) {
            addTicketView.setVisible(true);
            editTicketView.setVisible(false);
        } else {
            addTicketView.setVisible(false);
            editTicketView.setVisible(false);
        }
    }

    public void toggleEditTicket(ActionEvent event) {
        if(!editTicketView.isVisible()) {
            editTicketView.setVisible(true);
            addTicketView.setVisible(false);
        } else {
            editTicketView.setVisible(false);
            addTicketView.setVisible(false);
        }
    }

    public void deleteTicket(ActionEvent event) {
        if (ticketTableView.getSelectionModel().isEmpty()){
            displayNotification("No ticket selected");
            return;
        }
        Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
        if (hoe.removeTicket(t)) {
            displayNotification("Ticket removed");
        } else {
            displayNotification("Error: Ticket not removed");
        }
        updateShowsList();
    }

    public void submitTicketForm(ActionEvent event) {
    }

    public void clearAddTicketForm(ActionEvent event) {
    }

    public void closeAddTicketMenu(ActionEvent event) {
        addTicketView.setVisible(false);
    }

    public void confirmEditTicket(ActionEvent event) {
    }

    public void discardEditTicket(ActionEvent event) {

    }

    public void toggleAddContactsMenu(ActionEvent event) {
        if(!addContactView.isVisible()) {
            addContactView.setVisible(true);
            editContactView.setVisible(false);
        } else {
            addContactView.setVisible(false);
            editContactView.setVisible(false);
        }
    }

    public void toggleEditContact(ActionEvent event) {
        if(!editContactView.isVisible()) {
            editContactView.setVisible(true);
            addContactView.setVisible(false);
        } else {
            editContactView.setVisible(false);
            addContactView.setVisible(false);
        }
    }

    public void deleteContact(ActionEvent event) {
        if (contactsTableView.getSelectionModel().isEmpty()){
            displayNotification("No contact selected");
            return;
        }
        Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
        if (hoe.removeTicket(t)) {
            displayNotification("Contact removed");
        } else {
            displayNotification("Error: Contact not removed");
        }
        updateTicketsList();
    }

    public void submitContactForm(ActionEvent event) {
    }

    public void clearAddContactForm(ActionEvent event) {
    }

    public void closeAddContactForm(ActionEvent event) {
        addContactView.setVisible(false);
    }

    public void confirmEditContact(ActionEvent event) {
    }

    public void discardEditContact(ActionEvent event) {
    }
}
