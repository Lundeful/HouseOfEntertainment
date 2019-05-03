package com.hoe.controller;

import com.hoe.model.*;
import com.hoe.model.exceptions.NotEnoughSeatsException;
import com.hoe.model.exceptions.CorruptFileException;
import com.hoe.model.exceptions.InvalidFileException;
import com.hoe.model.exceptions.WrongCSVFormatException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.function.UnaryOperator;
import java.io.File;
import java.util.ArrayList;


public class MainViewController {

    /*
    ==============================
    Main menu and panes for different views
    ==============================
     */
    @FXML
    private Button currentButton, overviewButton, showsButton, locationsButton, ticketsButton, contactsButton,
            promotionsButton, helpButton;

    @FXML
    private AnchorPane currentView, selectMenuWindow, overviewWindow, showsWindow, locationsWindow, ticketsWindow,
            contactsWindow, promotionsWindow, helpWindow;

    /*
    ==============================
    Shows view
    ==============================
     */
    @FXML
    private VBox addShowsView, editShowsView;

    @FXML
    private TableView<Show> showsTableView;

    @FXML
    private TableColumn<Show, String> tableColumnShow, tableColumnDate;

    @FXML
    private TableColumn<Location, Location> tableColumnLocation;

    @FXML
    private TextField showFilterInput, addShowTextFieldName, addShowTextFieldType, addShowTextFieldDate,
            addShowTextFieldTime, addShowTextFieldTicketPrice, editShowTextFieldName, editShowTextFieldType,
            editShowTextFieldDate, editShowTextFieldTime, editShowTextFieldTicketPrice, addShowFieldProgram,
            editShowFieldProgram;

    @FXML
    private ComboBox<Location> addShowChoiceBoxLocation, editShowChoiceBoxLocation;

    @FXML
    private ComboBox<ContactPerson> addShowChoiceBoxContact, editShowChoiceBoxContactPerson;

    @FXML
    private Label addShowFormNameError, addShowFormLocationError, showCardTitle,  showCardTime, showCardDate,
            showCardType, showCardLocation, showCardPrice, showCardAvailableTickets, showCardProgram, showCardContact,
            editShowFormNameError, editShowFormLocationError;

    /*
    ==============================
    Locations view
    ==============================
     */

    @FXML
    private VBox addLocationView, editLocationView;

    @FXML
    private TextField locationFilterInput, addLocationFieldName, addLocationFieldType, addLocationFieldSeats,
            editLocationFieldName, editLocationFieldType, editLocationFieldSeats;

    @FXML
    private TableView<Location> locationTableView;

    @FXML
    private TableColumn<Location, String> tableColumnLocationName, tableColumnLocationType, tableColumnLocationSeats;

    @FXML
    private Label addLocationNameError, addLocationSeatsError, editLocationNameError, editLocationSeatsError;

    /*
    ==============================
    Tickets view
    ==============================
     */

    @FXML
    private VBox addTicketView, editTicketView;

    @FXML
    private TableView<Ticket> ticketTableView;

    @FXML
    private TableColumn<Ticket, String> tableColumnTicketShow, tableColumnTicketPhone,
            tableColumnTicketDate, tableColumnTicketTime, tableColumnTicketSeat;

    @FXML
    private TableColumn<Location, String> tableColumnTicketLocation;

    @FXML
    private TextField ticketFilterInput, editTicketFieldSeat, editTicketFieldPhoneNumber, addTicketTextFieldPhone,
            addTicketTextFieldSeat;

    @FXML
    private ComboBox<Show> addTicketChoiceBoxShow, editTicketChoiceBoxShow;

    @FXML
    private Label addTicketErrorShow, addTicketErrorPhone, editTicketErrorShow, editTicketErrorPhoneNumber;

    /*
    ==============================
    Contacts view
    ==============================
     */

    @FXML
    private VBox addContactView, editContactView;

    @FXML
    private TableView<ContactPerson> contactsTableView;

    @FXML
    private TableColumn<ContactPerson, String> tableColumnContactName, tableColumnContactPhone, tableColumnContactMail,
            tableColumnContactWebsite, tableColumnContactAffiliation, tableColumnContactOther;


    @FXML
    private TextField addContactTextFieldName, addContactTextFieldPhone, addContactTextFieldMail,
            addContactTextFieldWebsite, addContactTextFieldAffiliation, addContactTextFieldOther, contactFilterInput,
            editContactFieldName, editContactFieldPhone, editContactFieldMail, editContactFieldWebsite,
            editContactFieldAffiliation, editContactFieldOther;

    @FXML
    private Label addContactErrorName, addContactErrorPhone, editContactErrorName, editContactErrorPhone;

    /*
    ==============================
    Promotions view
    ==============================
     */

    @FXML
    private TableView<Promotion> promotionTableView;

    @FXML
    private VBox addPromotionView, editPromotionView;

    @FXML
    private TableColumn<Promotion, String> tableColumnPromotionFrom, tableColumnPromotionTo;

    @FXML
    private TableColumn<Promotion, Show>  tableColumnPromotionShow;

    @FXML
    private TextField addPromotionFieldFrom, addPromotionFieldTo, editPromotionFieldFrom, editPromotionFieldTo,
            promotionFilterInput;

    @FXML
    private ComboBox<Show> addPromotionChoiceBoxShow, editPromotionChoiceBoxShow;

    @FXML
    private Label addPromotionErrorShow, addPromotionErrorFrom, addPromotionErrorTo, editPromotionErrorFrom,
            editPromotionErrorShow, editPromotionErrorTo;

    @FXML
    private Label notification;


    private HoE hoe;
    private Show selectedShow;

    public void initialize() {
        try {
            hoe = new HoE();
        } catch (NotEnoughSeatsException e) {
            e.printStackTrace(); //TODO FIX THIS, remove try/catch
        }

        initializeVisibility();
        addNumberFormatFilter();
        initializeLocations();
        initializeContacts();
        initializeShows();
        initializeTickets();
        initializePromotions();
    }

    /**
     * This method adds filters to text fields so that only numeric input are allowed
     */
    private void addNumberFormatFilter() {
        UnaryOperator<TextFormatter.Change> intFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        addTextFormatter(addShowTextFieldTicketPrice, intFilter);
        addTextFormatter(editShowTextFieldTicketPrice, intFilter);
        addTextFormatter(addLocationFieldSeats, intFilter);
        addTextFormatter(editLocationFieldSeats, intFilter);
        addTextFormatter(addTicketTextFieldPhone, intFilter);
        addTextFormatter(editTicketFieldPhoneNumber, intFilter);
        addTextFormatter(addContactTextFieldPhone, intFilter);
        addTextFormatter(editContactFieldPhone, intFilter);
    }

    private void addTextFormatter(TextField tf, UnaryOperator<TextFormatter.Change> filter) {
        tf.setTextFormatter(new TextFormatter<String>(filter));
    }

    private void initializeVisibility() {
        currentView = selectMenuWindow;
        currentButton = overviewButton;
        currentView.setVisible(true);

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
                updateShowCardInfo();
            }
        });
    }

    private void initializeLocations() {
        tableColumnLocationName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnLocationType.setCellValueFactory(new PropertyValueFactory<>("typeOfLocation"));
        tableColumnLocationSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        updateLocationsList();
    }

    private void initializeTickets() {
        tableColumnTicketShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnTicketDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnTicketTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableColumnTicketLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColumnTicketPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableColumnTicketSeat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        updateTicketsList();
    }

    private void initializeContacts() {
        tableColumnContactName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnContactPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableColumnContactMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnContactWebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
        tableColumnContactAffiliation.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        tableColumnContactOther.setCellValueFactory(new PropertyValueFactory<>("other"));
        updateContactsList();
    }

    private void initializePromotions() {
        tableColumnPromotionShow.setCellValueFactory(new PropertyValueFactory<>("show"));
        tableColumnPromotionFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnPromotionTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        updatePromotionList();
    }

    private void updateShowsList() {
        ObservableList<Show> showData = FXCollections.observableArrayList(hoe.getShows());
        showsTableView.setItems(showData);
        addTicketChoiceBoxShow.setItems(showData);
        editTicketChoiceBoxShow.setItems(showData);
        addPromotionChoiceBoxShow.setItems(showData);
        editPromotionChoiceBoxShow.setItems(showData);
        showsTableView.refresh();
    }

    private void updateShowCardInfo() {
        showCardTitle.setText(selectedShow.getShowName());
        showCardType.setText(selectedShow.getShowType());
        showCardDate.setText(selectedShow.getDate());
        showCardTime.setText(selectedShow.getTime());
        if (selectedShow.getContact() != null) showCardContact.setText(selectedShow.getContact().toString());
        else showCardContact.setText("");
        showCardProgram.setText(selectedShow.getProgram());
        showCardPrice.setText(selectedShow.getTicketPrice());
        if (selectedShow.getLocation() != null) showCardLocation.setText(selectedShow.getLocation().getName());
        else showCardLocation.setText("");
        showCardAvailableTickets.setText(String.valueOf(selectedShow.getAvailableTickets()));
    }

    private void updateLocationsList() {
        ObservableList<Location> locationsData = FXCollections.observableArrayList(hoe.getLocations());
        locationTableView.setItems(locationsData);
        addShowChoiceBoxLocation.setItems(locationsData);
        editShowChoiceBoxLocation.setItems(locationsData);
        showsTableView.refresh();
    }

    private void updateTicketsList() {
        ObservableList<Ticket> ticketsData = FXCollections.observableArrayList(hoe.getTickets());
        ticketTableView.setItems(ticketsData);
        ticketTableView.refresh();
    }


    private void updateContactsList(){
        ObservableList<ContactPerson> contactsData = FXCollections.observableArrayList(hoe.getContacts());
        contactsTableView.setItems(contactsData);
        contactsTableView.refresh();
        addShowChoiceBoxContact.setItems(contactsData);
        editShowChoiceBoxContactPerson.setItems(contactsData);
    }

    private void updatePromotionList() {
        ObservableList<Promotion> promotionsData = FXCollections.observableArrayList(hoe.getPromotions());
        promotionTableView.setItems(promotionsData);
        promotionTableView.refresh();
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

    private FileChooser getFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV file", "*.csv"));
                new FileChooser.ExtensionFilter("Serializable file", "*.jobj");
        return chooser;
    }

    public void loadData() {
        File selectedFile = getFileChooser().showOpenDialog(new Stage());
        if (selectedFile == null) return;
        String finalPath = selectedFile.toString();
        displayNotification("Loading data...", 2);
        new Thread(() -> {
            try {
                hoe.load(finalPath);
                updateShowsList();
                updateContactsList();
                updateLocationsList();
                updatePromotionList();
                updateTicketsList();
                Platform.runLater(
                        () -> displayNotification("Loading complete")
                );
                Thread.currentThread().interrupt();
            } catch (InvalidFileException | WrongCSVFormatException | NotEnoughSeatsException | CorruptFileException e){
                e.printStackTrace();
                Platform.runLater(
                        () -> displayNotification(e.getMessage(), 3)
                );
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void saveData() {
        File selectedFile = getFileChooser().showSaveDialog(new Stage());
        if (selectedFile == null) return;
        String finalPath = selectedFile.toString();
        displayNotification("Saving data...");
        hoe.save(finalPath);
        displayNotification("Saving complete", 1);
    }

    private void displayNotification(String s) {
        displayNotification(s, 0.5);
    }

    private void displayNotification(String s, double d) {
        notification.setText(s);
        fadeTransition(notification, d);
    }

    private void fadeTransition(Label label) {
        fadeTransition(label, 0.5);
    }

    private void fadeTransition(Label label, double d) {
        label.setVisible(true);
        label.setOpacity(1);
        FadeTransition ft = new FadeTransition(Duration.seconds(d), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setDelay(Duration.millis(1000));
        ft.play();
    }

    public void filterShow() {
        String filter = showFilterInput.getText();
        if (filter.equals("")) {
            updateShowsList();
        } else {
            ArrayList<Show> filteredShows = hoe.filterShow(filter);
            ObservableList<Show> filteredShowData = FXCollections.observableArrayList(filteredShows);
            showsTableView.setItems(filteredShowData);
        }
    }

    public void filterLocation(){
        String filter = locationFilterInput.getText();
        if (filter.equals("")){
            updateLocationsList();
        } else {
            ArrayList<Location> filteredLocation = hoe.filterLocation(filter);
            ObservableList<Location> filteredLocationData = FXCollections.observableArrayList(filteredLocation);
            locationTableView.setItems(filteredLocationData);
        }
    }

    public void filterContact(){
        String filter = contactFilterInput.getText();
        if (filter.equals("")){
            updateContactsList();
        } else {
            ArrayList<ContactPerson> filteredContacts = hoe.filterContactPerson(filter);
            ObservableList<ContactPerson> filteredContactsData = FXCollections.observableArrayList(filteredContacts);
            contactsTableView.setItems(filteredContactsData);
        }
    }

    public void filterTicket(){
        String filter = ticketFilterInput.getText();
        if (filter.equals("")){
            updateTicketsList();
        } else {
            ArrayList<Ticket> filteredTicket = hoe.filterTickets(filter);
            ObservableList<Ticket> filteredTicketData = FXCollections.observableArrayList(filteredTicket);
            ticketTableView.setItems(filteredTicketData);
        }
    }

    public void filterPromotion(){
        String filter = promotionFilterInput.getText();
        if (filter.equals("")){
            updatePromotionList();
        } else {
            ArrayList<Promotion> filteredPromotion = hoe.filterPromotion(filter);
            ObservableList<Promotion> filteredPromotionData = FXCollections.observableArrayList(filteredPromotion);
            promotionTableView.setItems(filteredPromotionData);
        }
    }

    public void toggleAddShowMenu() {
        if(!addShowsView.isVisible()) {
            addShowsView.setVisible(true);
            editShowsView.setVisible(false);
        } else {
            addShowsView.setVisible(false);
            editShowsView.setVisible(false);
        }
    }

    public void toggleEditShow() {
        if(!editShowsView.isVisible() && showsTableView.getSelectionModel().getSelectedItem() != null) {
            loadShowValues();
            editShowsView.setVisible(true);
            addShowsView.setVisible(false);
        } else {
            editShowsView.setVisible(false);
            addShowsView.setVisible(false);
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

    // Delete show and tickets for that show
    public void deleteShow() {
        if (addShowsView.isVisible() || editShowsView.isVisible()) {
            addShowsView.setVisible(false);
            editShowsView.setVisible(false);
            displayNotification("Select show to remove");
        } else {
            if (showsTableView.getSelectionModel().isEmpty()) {
                displayNotification("No show selected");
                return;
            }
            Show s = showsTableView.getSelectionModel().getSelectedItem();
            if (hoe.removeShow(s)) {
                displayNotification("Show removed");
                updateTicketsList();
                updateShowsList();
                updatePromotionList();
            } else {
                displayNotification("Error: Show not removed");
            }

        }
    }

    public void clearAddShowFields() {
        addShowTextFieldName.clear();
        addShowTextFieldType.clear();
        addShowTextFieldDate.clear();
        addShowTextFieldTime.clear();
        addShowChoiceBoxLocation.setValue(null);
        addShowTextFieldTicketPrice.clear();
        addShowFieldProgram.clear();
    }

    public void closeAddShowMenu() {
        addShowsView.setVisible(false);
    }

    public void submitShowForm() {
        if (addShowTextFieldName.getText().trim().equals("")) { // If name field is empty
            fadeTransition(addShowFormNameError, 1);
        } else if (addShowChoiceBoxLocation.getValue() == null) {
            fadeTransition(addShowFormLocationError, 1);
        } else {
        hoe.addShow(addShowTextFieldName.getText(), addShowTextFieldType.getText(), addShowTextFieldDate.getText(),
                    addShowTextFieldTime.getText(), addShowChoiceBoxLocation.getValue(),
                    addShowTextFieldTicketPrice.getText(), addShowFieldProgram.getText());
        updateShowsList();
        toggleAddShowMenu();
        displayNotification("Show added");
        }
    }

    public void confirmEditShow() {
        Show show = showsTableView.getSelectionModel().getSelectedItem();
        if (editShowTextFieldName.getText().trim().equals("")) { // If name field is empty
            fadeTransition(editShowFormNameError);
        } else if(editShowChoiceBoxLocation.getValue() == null){
            fadeTransition(editShowFormLocationError);

        } else {
            try {
                hoe.updateShow(show, editShowTextFieldName.getText(), editShowTextFieldType.getText(),
                        editShowTextFieldDate.getText(), editShowTextFieldTime.getText(),
                        editShowTextFieldTicketPrice.getText(), editShowChoiceBoxLocation.getValue(),
                        editShowChoiceBoxContactPerson.getValue(), editShowFieldProgram.getText());

                ticketTableView.refresh(); // TODO Update or refresh?
                showsTableView.refresh();
                editShowsView.setVisible(false);
                displayNotification("Show edited");
            } catch (NotEnoughSeatsException e) {
                displayNotification("Error: " + e.getMessage());
                System.err.println(e.getMessage());
            }
        }
    }

    public void discardEditShow() {
        editShowsView.setVisible(false);
    }

    public void toggleAddLocationMenu() {
        if(!addLocationView.isVisible()) {
            addLocationView.setVisible(true);
            editLocationView.setVisible(false);
        } else {
            addLocationView.setVisible(false);
            editLocationView.setVisible(false);
        }
    }

    public void toggleEditLocation() {
        if(!editLocationView.isVisible() && locationTableView.getSelectionModel().getSelectedItem() != null) {
            loadLocationValues();
            editLocationView.setVisible(true);
            addLocationView.setVisible(false);
        } else {
            editLocationView.setVisible(false);
            addLocationView.setVisible(false);
        }
    }

    private void loadLocationValues() {
        Location location = locationTableView.getSelectionModel().getSelectedItem();
        editLocationFieldName.setText(location.getName());
        editLocationFieldType.setText(location.getTypeOfLocation());
        editLocationFieldSeats.setText(String.valueOf(location.getNumberOfSeats()));
    }

    public void deleteLocation() {
        if (addLocationView.isVisible() || editLocationView.isVisible()) {
            addLocationView.setVisible(false);
            editLocationView.setVisible(false);
            displayNotification("Select location to remove");
        } else {
            if (locationTableView.getSelectionModel().isEmpty()) {
                displayNotification("No location selected");
                return;
            }
            Location l = locationTableView.getSelectionModel().getSelectedItem();
            try {
                if (hoe.removeLocation(l)) {
                    displayNotification("Location removed");
                } else {
                    displayNotification("Error: Location not removed");
                }
            } catch (NotEnoughSeatsException e) {
                e.printStackTrace();
            }
            updateLocationsList();
        }
    }

    public void submitLocationForm() {
        if (addLocationFieldName.getText().trim().equals("")) {
            fadeTransition(addLocationNameError);
        } else if (addLocationFieldSeats.getText().equals("")) {
            fadeTransition(addLocationSeatsError);
        } else {
            hoe.addLocation(addLocationFieldName.getText(), addLocationFieldType.getText(),
                    Integer.parseInt(addLocationFieldSeats.getText()));
            updateLocationsList();
            showsTableView.refresh();
            addLocationView.setVisible(false);
            displayNotification("Location added");
        }
    }

    public void clearAddLocationFields() {
        addLocationFieldName.clear();
        addLocationFieldSeats.clear();
        addLocationFieldType.clear();
    }

    public void closeAddLocationMenu() {
        addLocationView.setVisible(false);
    }

    public void confirmEditLocation() {
        Location location = locationTableView.getSelectionModel().getSelectedItem();
        if (editLocationFieldName.getText().trim().equals("")) {
            fadeTransition(editLocationNameError);
        } else if (editLocationFieldSeats.getText().equals("")) {
            fadeTransition(editLocationSeatsError);
        } else {
            try {
                hoe.updateLocation(location, editLocationFieldName.getText(), editLocationFieldType.getText(),
                        Integer.parseInt(editLocationFieldSeats.getText()));

                locationTableView.refresh();
                ticketTableView.refresh();
                showsTableView.refresh();
                editLocationView.setVisible(false);
                displayNotification("Location edited");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                displayNotification("Error: Enter a numeric value");
            }
        }
    }

    public void discardEditLocation() {
        editLocationView.setVisible(false);
    }

    public void toggleAddTicket() {
        if(!addTicketView.isVisible()) {
            addTicketView.setVisible(true);
            editTicketView.setVisible(false);
        } else {
            addTicketView.setVisible(false);
            editTicketView.setVisible(false);
        }
    }

    public void toggleEditTicket() {
        if(!editTicketView.isVisible() && ticketTableView.getSelectionModel().getSelectedItem() != null) {
            loadTicketValues();
            editTicketView.setVisible(true);
            addTicketView.setVisible(false);
        } else {
            editTicketView.setVisible(false);
            addTicketView.setVisible(false);
        }
    }

    private void loadTicketValues() {
        Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
        editTicketFieldPhoneNumber.setText(t.getPhoneNumber());
        editTicketChoiceBoxShow.setValue(t.getShow());
        editTicketFieldSeat.setText(t.getSeat());
    }

    public void deleteTicket() {
        if (editTicketView.isVisible() || addTicketView.isVisible()){
            editTicketView.setVisible(false);
            addTicketView.setVisible(false);
            displayNotification("Choose ticket to remove");
        } else if (ticketTableView.getSelectionModel().getSelectedItem() == null) {
            displayNotification("No ticket selected");
        } else {
            Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
            if (hoe.removeTicket(t)) {
                updateTicketsList();
                displayNotification("Ticket removed");
            } else {
                displayNotification("Error: Ticket not removed");
            }
            updateShowsList();
        }
    }

    public void submitTicketForm() {
        if (addTicketChoiceBoxShow.getValue() == null) {
            fadeTransition(addTicketErrorShow);
        } else if (addTicketTextFieldPhone.getText().equals("")) {
            fadeTransition(addTicketErrorPhone);
        } else {
            hoe.addTicket(addTicketChoiceBoxShow.getValue(), addTicketTextFieldPhone.getText(),
                    addTicketTextFieldSeat.getText());
            updateTicketsList();
            clearAddTicketForm();
            addTicketView.setVisible(false);
            displayNotification("Ticket added");
        }
    }

    public void clearAddTicketForm() {
        addTicketChoiceBoxShow.setValue(null);
        addTicketTextFieldPhone.clear();
        addTicketTextFieldSeat.clear();
    }

    public void closeAddTicketMenu() {
        addTicketView.setVisible(false);
    }

    public void confirmEditTicket() {
        Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
        if (editTicketChoiceBoxShow.getValue() == null) {
            fadeTransition(editTicketErrorShow);
        } else if (editTicketFieldPhoneNumber.getText().equals("")) {
            fadeTransition(editTicketErrorPhoneNumber);
        } else {
            hoe.updateTicket(t, editTicketChoiceBoxShow.getValue(), editTicketFieldPhoneNumber.getText(),
                    editTicketFieldSeat.getText());

            ticketTableView.refresh();
            editTicketView.setVisible(false);
            displayNotification("Ticket edited");
        }
    }

    public void discardEditTicket() {
        editTicketView.setVisible(false);
    }

    public void toggleAddContactsMenu() {
        if(!addContactView.isVisible()) {
            addContactView.setVisible(true);
            editContactView.setVisible(false);
        } else {
            addContactView.setVisible(false);
            editContactView.setVisible(false);
        }
    }

    public void toggleEditContact() {
        if(!editContactView.isVisible() && contactsTableView.getSelectionModel().getSelectedItem() != null) {
            loadContactValues();
            editContactView.setVisible(true);
            addContactView.setVisible(false);
        } else {
            editContactView.setVisible(false);
            addContactView.setVisible(false);
            discardEditContact();
        }
    }

    private void loadContactValues() {
        ContactPerson c = contactsTableView.getSelectionModel().getSelectedItem();
        editContactFieldName.setText(c.getName());
        editContactFieldPhone.setText(c.getPhoneNumber());
        editContactFieldMail.setText(c.getEmail());
        editContactFieldAffiliation.setText(c.getAffiliation());
        editContactFieldWebsite.setText(c.getWebsite());
        editContactFieldOther.setText(c.getOther());
    }

    public void deleteContact() {
        if (editContactView.isVisible() || addContactView.isVisible()) {
            addContactView.setVisible(false);
            editContactView.setVisible(false);
            displayNotification("Choose contact to remove");
        } else if (contactsTableView.getSelectionModel().isEmpty()){
            displayNotification("No contact selected");
        } else {
            ContactPerson c = contactsTableView.getSelectionModel().getSelectedItem();
            if (hoe.removeContact(c)) {
                updateContactsList();
                updateShowsList(); // Update shows also because they have contacts associated with them
                displayNotification("Contact removed");
            } else {
                displayNotification("Error: Contact not removed");
            }
            updateTicketsList();
        }
    }

    public void submitContactForm() {
        if (addContactTextFieldName.getText().equals("")) {
            fadeTransition(addContactErrorName);
        } else if (addContactTextFieldPhone.getText().equals("")) {
            fadeTransition(addContactErrorPhone);
        } else {
            if (hoe.addContact(addContactTextFieldName.getText(), addContactTextFieldPhone.getText(),
                    addContactTextFieldMail.getText(), addContactTextFieldWebsite.getText(),
                    addContactTextFieldAffiliation.getText(), addContactTextFieldOther.getText())) {
                displayNotification("Contact added");
                clearAddContactForm();
                updateContactsList();
                addContactView.setVisible(false);
            } else {
                displayNotification("Error: Contact not added");
            }
        }
    }

    public void clearAddContactForm() {
        addContactTextFieldName.clear();
        addContactTextFieldPhone.clear();
        addContactTextFieldMail.clear();
    }

    public void closeAddContactForm() {
        addContactView.setVisible(false);
    }

    public void confirmEditContact() {
        ContactPerson c = contactsTableView.getSelectionModel().getSelectedItem();

        if (editContactFieldName.getText().equals("")) {
            fadeTransition(editContactErrorName);
        } else if (editContactFieldPhone.getText().equals("")) {
            fadeTransition(editContactErrorPhone);
        } else {
            hoe.updateContact(c, editContactFieldName.getText(), editContactFieldPhone.getText(),
                    editContactFieldMail.getText(), editContactFieldWebsite.getText(),
                    editContactFieldAffiliation.getText(), editContactFieldOther.getText());

            contactsTableView.refresh();
            showsTableView.refresh();
            editContactView.setVisible(false);
            displayNotification("Contact edited");
        }
    }

    public void discardEditContact() {
        editContactView.setVisible(false);
    }

    public void toggleAddPromotion() {
        if(!addPromotionView.isVisible()) {
            addPromotionView.setVisible(true);
            editPromotionView.setVisible(false);
        } else {
            addPromotionView.setVisible(false);
            editPromotionView.setVisible(false);
        }
    }

    public void toggleEditPromotion() {
        if(!editPromotionView.isVisible() && promotionTableView.getSelectionModel().getSelectedItem() != null) {
            loadPromotionValues();
            editPromotionView.setVisible(true);
            addPromotionView.setVisible(false);
        } else {
            addPromotionView.setVisible(false);
            editPromotionView.setVisible(false);
        }
    }

    private void loadPromotionValues() {
        Promotion p = promotionTableView.getSelectionModel().getSelectedItem();
        editPromotionChoiceBoxShow.setValue(p.getShow());
        editPromotionFieldFrom.setText(p.getFrom());
        editPromotionFieldTo.setText(p.getTo());
    }

    public void deletePromotion() {
        Promotion p = promotionTableView.getSelectionModel().getSelectedItem();
        if (addPromotionView.isVisible() || editPromotionView.isVisible()) {
            addPromotionView.setVisible(false);
            editPromotionView.setVisible(false);
            displayNotification("Choose promotion to remove");
        } else if (promotionTableView.getSelectionModel().getSelectedItem() == null) {
            displayNotification("No promotion selected");
        } else if (hoe.removePromotion(p)) {
            displayNotification("Promotion deleted");
            updatePromotionList();
        } else {
            displayNotification("Error: Promotion not removed");
        }
    }

    public void submitPromotionForm() {
        if (addPromotionChoiceBoxShow.getValue() == null) {
            fadeTransition(addPromotionErrorShow);
        } else if (addPromotionFieldFrom.getText().equals("")) {
            fadeTransition(addPromotionErrorFrom);
        } else if (addPromotionFieldTo.getText().equals("")) {
            fadeTransition(addPromotionErrorTo);
        } else {
            if (hoe.addPromotion(addPromotionChoiceBoxShow.getValue(), addPromotionFieldFrom.getText(),
                    addPromotionFieldTo.getText())) {
                updatePromotionList();
                displayNotification("Promotion added");
                clearAddPromotionForm();
                addPromotionView.setVisible(false);
            }
        }
    }

    public void clearAddPromotionForm() {
        addPromotionChoiceBoxShow.setValue(null);
        addPromotionFieldFrom.clear();
        addPromotionFieldTo.clear();
    }

    public void closeAddPromotionForm() {
        addPromotionView.setVisible(false);
    }

    public void confirmEditPromotion() {
        Promotion p = promotionTableView.getSelectionModel().getSelectedItem();
        if (editPromotionChoiceBoxShow.getValue() == null) {
            fadeTransition(editPromotionErrorShow);
        } else if (editPromotionFieldFrom.equals("")) {
            fadeTransition(editPromotionErrorFrom);
        } else if (editPromotionFieldTo.equals("")) {
            fadeTransition(editPromotionErrorTo);
        } else {
            hoe.updatePromotion(p, editPromotionChoiceBoxShow.getValue(), editPromotionFieldFrom.getText(),
                    editPromotionFieldTo.getText());

            promotionTableView.refresh();
            editPromotionView.setVisible(false);
            displayNotification("Promotion edited");
        }
    }

    public void discardEditPromotion() {
        editPromotionView.setVisible(false);
    }
}
