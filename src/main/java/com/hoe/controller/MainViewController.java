package com.hoe.controller;

import com.hoe.model.*;
import com.hoe.model.exceptions.*;
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

import java.io.IOException;
import java.util.function.UnaryOperator;
import java.io.File;
import java.util.ArrayList;

/**
 * The MainViewController class is the controller for all the GUI elements, handles input and is the bridge
 * between the FXML and and model (Hoe) class in an MVC pattern.
 *
 * @author 681
 */
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
    private VBox addShowsView, editShowsView, showCard;

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
    private TableColumn<Promotion, String> tableColumnPromotionFrom, tableColumnPromotionTo, tableColumnPromotionShow,
            tableColumnPromotionDate;

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


    /**
     * Initializes the application before use
     * @auth 681
     */
    public void initialize() {
        try {
            hoe = new HoE();
        } catch (IllegalLocationException e) {
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
     * @auth 681
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

    /**
     * Helper method for addNumberFormatFilter() method
     * @param tf TextField that is to be added a filter
     * @param filter The number filter
     * @auth 681
     */
    private void addTextFormatter(TextField tf, UnaryOperator<TextFormatter.Change> filter) {
        tf.setTextFormatter(new TextFormatter<String>(filter));
    }

    /**
     * Sets the visibility of panes for program start-up
     * @auth 681
     */
    private void initializeVisibility() {
        currentView = selectMenuWindow;
        currentButton = overviewButton;
        currentView.setVisible(true);
    }

    /**
     * This method initializes the shows view, connecting the TableView columns with the database lists.
     * It also adds a listener for TableView selection used to the display show information.
     * @auth 681
     */
    private void initializeShows() {
        tableColumnShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        updateShowsList();

        // Listen for selected show in table
        showsTableView.getSelectionModel().selectedItemProperty().addListener((observableValue, s1, s2) -> {
            if (s1 != null && s2 != null && !s2.equals(s1)) {
                selectedShow = s2;
                showCard.setVisible(true);
                updateShowCardInfo();
            }
        });
    }

    /**
     * Initializes locations tableview
     * @auth 681
     */
    private void initializeLocations() {
        tableColumnLocationName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnLocationType.setCellValueFactory(new PropertyValueFactory<>("typeOfLocation"));
        tableColumnLocationSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        updateLocationsList();
    }

    /**
     * Initializes tickets tableview
     * @auth 681
     */
    private void initializeTickets() {
        tableColumnTicketShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnTicketDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnTicketTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableColumnTicketLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        tableColumnTicketPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableColumnTicketSeat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        updateTicketsList();
    }

    /**
     * Initializes locations tableview
     * @auth 681
     */
    private void initializeContacts() {
        tableColumnContactName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnContactPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tableColumnContactMail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnContactWebsite.setCellValueFactory(new PropertyValueFactory<>("website"));
        tableColumnContactAffiliation.setCellValueFactory(new PropertyValueFactory<>("affiliation"));
        tableColumnContactOther.setCellValueFactory(new PropertyValueFactory<>("other"));
        updateContactsList();
    }

    /**
     * Initializes promotions tableview
     * @auth 681
     */
    private void initializePromotions() {
        tableColumnPromotionShow.setCellValueFactory(new PropertyValueFactory<>("showName"));
        tableColumnPromotionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableColumnPromotionFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableColumnPromotionTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        updatePromotionList();
    }

    /**
     * Updates the GUI with the database shows list. It updates the tableview and drop-down boxes with the correct
     * shows
     * @auth 681
     */
    private void updateShowsList() {
        ObservableList<Show> showData = FXCollections.observableArrayList(hoe.getShows());
        showsTableView.setItems(showData);
        addTicketChoiceBoxShow.setItems(showData);
        editTicketChoiceBoxShow.setItems(showData);
        addPromotionChoiceBoxShow.setItems(showData);
        editPromotionChoiceBoxShow.setItems(showData);
        showsTableView.refresh();
    }

    /**
     * Updates the information card in the shows view with values given from user selection in the TableView
     * @auth 681
     */
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

    /**
     * Updates the observable lists with the new and updated data from the database. Also updates drop-down boxes
     * @auth 681
     */
    private void updateLocationsList() {
        ObservableList<Location> locationsData = FXCollections.observableArrayList(hoe.getLocations());
        locationTableView.setItems(locationsData);
        addShowChoiceBoxLocation.setItems(locationsData);
        editShowChoiceBoxLocation.setItems(locationsData);
        showsTableView.refresh();
    }

    /**
     * Updates the tableviews with the new and updated data from the database
     * @auth 681
     */
    private void updateTicketsList() {
        ObservableList<Ticket> ticketsData = FXCollections.observableArrayList(hoe.getTickets());
        ticketTableView.setItems(ticketsData);
        ticketTableView.refresh();
    }

    /**
     * Updates the tableviews and drop-down boxes with the new and updated data from the database
     * @auth 681
     */
    private void updateContactsList(){
        ObservableList<ContactPerson> contactsData = FXCollections.observableArrayList(hoe.getContacts());
        contactsTableView.setItems(contactsData);
        contactsTableView.refresh();
        addShowChoiceBoxContact.setItems(contactsData);
        editShowChoiceBoxContactPerson.setItems(contactsData);
    }

    /**
     * Updates the tableviews with the new and updated data from the database
     * @auth 681
     */
    private void updatePromotionList() {
        ObservableList<Promotion> promotionsData = FXCollections.observableArrayList(hoe.getPromotions());
        promotionTableView.setItems(promotionsData);
        promotionTableView.refresh();
    }

    /**
     * Called when user clicks on overview-button
     * @auth 681
     */
    public void overviewClicked() {
        menuClicked(overviewWindow, overviewButton);
    }

    /**
     * Called when user clicks on shows-button
     * @auth 681
     */
    public void showsClicked() {
        menuClicked(showsWindow, showsButton);
    }

    /**
     * Called when user clicks on locations-button
     * @auth 681
     */
    public void locationsClicked() {
        menuClicked(locationsWindow, locationsButton);
    }

    /**
     * Called when user clicks on tickets-button
     * @auth 681
     */
    public void ticketsClicked() {
        menuClicked(ticketsWindow, ticketsButton);
    }

    /**
     * Called when user clicks on contacts-button
     * @auth 681
     */
    public void contactsClicked() {
        menuClicked(contactsWindow, contactsButton);
    }

    /**
     * Called when user clicks on promotions-button
     * @auth 681
     */
    public void promotionsClicked() {
        menuClicked(promotionsWindow, promotionsButton);
    }

    /**
     * Called when user clicks on help-button
     * @auth 681
     */
    public void helpClicked() {
        menuClicked(helpWindow, helpButton);
    }

    /**
     * Method that switches views to the corresponding menu-click by the user, and adds styling of selected view button
     * @param ap The view the user wants to see
     * @param b The button the user clicked
     */
    private void menuClicked(AnchorPane ap, Button b) {
        currentView.setVisible(false);
        currentButton.getStyleClass().remove("mainMenuButtonHighlighted");
        b.getStyleClass().add("mainMenuButtonHighlighted");
        ap.setVisible(true);
        currentView = ap;
        currentButton = b;
    }

    /**
     * Method that makes it possible to select either a load file
     * Or set a directory of a wanted save file
     * Gives the user a choice of either of either a CSV file or Serializable file of either load or save
     * @return returns the chooser on the given parameter of either load or save
     */
    private FileChooser getFileChooser() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select a file");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV file", "*.csv"));
                new FileChooser.ExtensionFilter("Serializable file", "*.jobj");
        return chooser;
    }

    /**
     * Method that utilizes the load method from the HoE class
     * This method unlike the save method from the HoE class starts it's thread here
     * This is because it has to handle exceptions outside the thread
     * The class uses the file chooser to select the wanted load file
     * Starts it's thread to make sure that the program does'nt freezes the program when loading huge files
     * The method updates all the different views after the file is loaded
     */
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
            } catch (InvalidFileException | WrongCSVFormatException | IOException |
                    IllegalLocationException | CorruptFileException e){
                e.printStackTrace();
                Platform.runLater(
                        () -> displayNotification(e.getMessage(), 3)
                );
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * Method that utilizes the save method from the HoE class
     * Gives the user the possibility to choose where to save the given file
     */
    public void saveData() {
        File selectedFile = getFileChooser().showSaveDialog(new Stage());
        if (selectedFile == null) return;
        String finalPath = selectedFile.toString();
        displayNotification("Saving data...");
        hoe.save(finalPath);
        displayNotification("Saving complete", 1);
    }

    /**
     * Overloaded nofitication method
     * @param s
     * @auth 681
     */
    private void displayNotification(String s) {
        displayNotification(s, 1);
    }

    /**
     * Displays a notification for the user in the top left corner and then fades away
     * @param s The text to be displayed in the notification
     * @param d Duration in seconds of notification before fade-out
     * @auth 681
     */
    private void displayNotification(String s, double d) {
        d = d*1000;
        notification.setText(s);
        fadeTransition(notification, d);
    }

    /**
     * Overloaded fader-method
     * @auth 681
     */
    private void fadeTransition(Label label) {
        fadeTransition(label, 1);
    }

    /**
     * Method that creates a fade-out transition on a label for a set amount of time
     * @param label Label to be faded
     * @param d Amount of time for notification to be up before fade start
     * @auth 681
     */
    private void fadeTransition(Label label, double d) {
        label.setVisible(true);
        label.setOpacity(1);
        FadeTransition ft = new FadeTransition(Duration.seconds(1), label);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.setDelay(Duration.millis(d));
        ft.play();
    }

    /**
     * Method that utilizes the filterShow method from the HoE class
     * If the search state is none existing the show list reverts to it's original loaded sate
     * If the search field is valid, it updates the list by passing the arraylist over to a observable list
     * so it's able to be shown easily on the view of show
     */
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

    /**
     * Method that utilizes the filterLocation method from the HoE class
     * If the search state is none existing the show list reverts to it's original loaded sate
     * If the search field is valid, it updates the list by passing the arraylist over to a observable list
     * so it's able to be shown easily on the view of location
     */
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

    /**
     * Method that utilizes the filterContact method from the HoE class
     * If the search state is none existing the show list reverts to it's original loaded sate
     * If the search field is valid, it updates the list by passing the arraylist over to a observable list
     * so it's able to be shown easily on the view of contact
     */
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

    /**
     * Method that utilizes the filterTicket method from the HoE class
     * If the search state is none existing the show list reverts to it's original loaded sate
     * If the search field is valid, it updates the list by passing the arraylist over to a observable list
     * so it's able to be shown easily on the view of ticket
     */
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

    /**
     * Method that utilizes the filterPromotion method from the HoE class
     * If the search state is none existing the show list reverts to it's original loaded sate
     * If the search field is valid, it updates the list by passing the arraylist over to a observable list
     * so it's able to be shown easily on the view of promtion
     */
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

    /**
     * Toggles the add show view
     * @auth 681
     */
    public void toggleAddShowMenu() {
        if(!addShowsView.isVisible()) {
            addShowsView.setVisible(true);
            editShowsView.setVisible(false);
        } else {
            addShowsView.setVisible(false);
            editShowsView.setVisible(false);
        }
    }

    /**
     * Toggles the edit show view if row in tableview is selected
     * @auth 681
     */
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

    /**
     * Loads the values of the selected show into the edit show fields
     * @auth 681
     */
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

    /**
     * Takes the selection by the user and calls the delete methods in the HoE class and removes show
     * @auth 681
     */
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

    /**
     * Clears the input fields in the add show view
     * @auth 681
     */
    public void clearAddShowFields() {
        addShowTextFieldName.clear();
        addShowTextFieldType.clear();
        addShowTextFieldDate.clear();
        addShowTextFieldTime.clear();
        addShowChoiceBoxLocation.setValue(null);
        addShowTextFieldTicketPrice.clear();
        addShowFieldProgram.clear();
    }

    /**
     * Closes the add show view
     */
    public void closeAddShowMenu() {
        addShowsView.setVisible(false);
    }

    /**
     * Called when hitting submit on the add show form. Checks if user has entered the minimum amount of fields and
     * then passes the info to the addShow() method
     * in the HoE class for creating
     */
    public void submitShowForm() {
        if (addShowTextFieldName.getText().trim().equals("")) {
            fadeTransition(addShowFormNameError, 1);
        } else if (addShowChoiceBoxLocation.getValue() == null) {
            fadeTransition(addShowFormLocationError, 1);
        } else {
            try {
                if (hoe.addShow(addShowTextFieldName.getText(), addShowTextFieldType.getText(), addShowTextFieldDate.getText(),
                            addShowTextFieldTime.getText(), addShowChoiceBoxLocation.getValue(),
                            addShowTextFieldTicketPrice.getText(), addShowFieldProgram.getText())) {
                    updateShowsList();
                    toggleAddShowMenu();
                    displayNotification("Show added");
                } else {
                    displayNotification("Error: Show not added");
                }
            } catch (IllegalLocationException e) {
                displayNotification(e.getMessage(), 2);
            }
        }
    }

    /**
     * Takes the user input and passes to the HoE class for updating show object
     * @auth 681
     */
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

                ticketTableView.refresh();
                showsTableView.refresh();
                editShowsView.setVisible(false);
                displayNotification("Show edited");
            } catch (IllegalLocationException e) {
                displayNotification(e.getMessage(), 2);
            }
        }
    }

    /**
     * Closes edit show view
     * @auth 681
     */
    public void discardEditShow() {
        editShowsView.setVisible(false);
    }

    /**
     * Toggles add location view
     * @auth 681
     */
    public void toggleAddLocationMenu() {
        if(!addLocationView.isVisible()) {
            addLocationView.setVisible(true);
            editLocationView.setVisible(false);
        } else {
            addLocationView.setVisible(false);
            editLocationView.setVisible(false);
        }
    }

    /**
     * Toggles edit location view
     * @auth 681
     */
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

    /**
     * Loads values for user-selected location for updating location object
     * @auth 681
     */
    private void loadLocationValues() {
        Location location = locationTableView.getSelectionModel().getSelectedItem();
        editLocationFieldName.setText(location.getName());
        editLocationFieldType.setText(location.getTypeOfLocation());
        editLocationFieldSeats.setText(String.valueOf(location.getNumberOfSeats()));
    }

    /**
     * Takes user selection and calls HoE for deletion of location
     * @auth 681
     */
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
            } catch (IllegalLocationException e) {
                displayNotification(e.getMessage(), 2);
            }
            updateLocationsList();
        }
    }

    /**
     * Submits add location fields for creation of new location object and displays notification when done
     * @auth 681
     */
    public void submitLocationForm() {
        if (addLocationFieldName.getText().trim().equals("")) {
            fadeTransition(addLocationNameError);
        } else if (addLocationFieldSeats.getText().equals("")) {
            fadeTransition(addLocationSeatsError);
        } else {
            hoe.addLocation(addLocationFieldName.getText(), addLocationFieldType.getText(),
                    Integer.parseInt(addLocationFieldSeats.getText()));
            clearAddLocationFields();
            updateLocationsList();
            showsTableView.refresh();
            addLocationView.setVisible(false);
            displayNotification("Location added");
        }
    }

    /**
     * Clears add location text-fields
     * @auth 681
     */
    public void clearAddLocationFields() {
        addLocationFieldName.clear();
        addLocationFieldSeats.clear();
        addLocationFieldType.clear();
    }

    /**
     * Closes add location view
     * @auth 681
     */
    public void closeAddLocationMenu() {
        addLocationView.setVisible(false);
    }

    /**
     * Valides input-fields and passes vales to HoE-class for updating location object. Displays notification for
     * validation and errors
     * @auth 681
     */
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

    /**
     * Closes edit location view
     */
    public void discardEditLocation() {
        editLocationView.setVisible(false);
    }

    /**
     * Toggles the add ticket view
     * @auth 681
     */
    public void toggleAddTicket() {
        if(!addTicketView.isVisible()) {
            addTicketView.setVisible(true);
            editTicketView.setVisible(false);
        } else {
            addTicketView.setVisible(false);
            editTicketView.setVisible(false);
        }
    }

    /**
     * Toggles the edit ticket view given user has selected row in the tableview
     * @auth 681
     */
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

    /**
     * Loads values for selected ticket for editing of ticket
     * @auth 681
     */
    private void loadTicketValues() {
        Ticket t = ticketTableView.getSelectionModel().getSelectedItem();
        editTicketFieldPhoneNumber.setText(t.getPhoneNumber());
        editTicketChoiceBoxShow.setValue(t.getShow());
        editTicketFieldSeat.setText(t.getSeat());
    }

    /**
     * Takes user-selected ticket and calls for removal of ticket. Displays notification of removal success
     * @auth 681
     */
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

    /**
     * Validates and submits user input for updating ticket
     * @auth 681
     */
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

    /**
     * Clears add ticket fields
     * @auth 681
     */
    public void clearAddTicketForm() {
        addTicketChoiceBoxShow.setValue(null);
        addTicketTextFieldPhone.clear();
        addTicketTextFieldSeat.clear();
    }

    /**
     * Close add ticket view
     * @auth 681
     */
    public void closeAddTicketMenu() {
        addTicketView.setVisible(false);
    }

    /**
     * Valides input passes into Hoe for editing of ticket object
     * @auth 681
     */
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

    /**
     * Close edit ticket view
     * @auth 681
     */
    public void discardEditTicket() {
        editTicketView.setVisible(false);
    }

    /**
     * Toggles add contact view
     * @auth 681
     */
    public void toggleAddContactsMenu() {
        if(!addContactView.isVisible()) {
            addContactView.setVisible(true);
            editContactView.setVisible(false);
        } else {
            addContactView.setVisible(false);
            editContactView.setVisible(false);
        }
    }

    /**
     * Toggles edit contact view
     * @auth 681
     */
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

    /**
     * Loads selected contact values to input fields for editing of contact object
     * @auth 681
     */
    private void loadContactValues() {
        ContactPerson c = contactsTableView.getSelectionModel().getSelectedItem();
        editContactFieldName.setText(c.getName());
        editContactFieldPhone.setText(c.getPhoneNumber());
        editContactFieldMail.setText(c.getEmail());
        editContactFieldAffiliation.setText(c.getAffiliation());
        editContactFieldWebsite.setText(c.getWebsite());
        editContactFieldOther.setText(c.getOther());
    }

    /**
     * Passes contact selection for deletion from database and displays result to user
     * @auth 681
     */
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

    /**
     * Validates and passes user input for editing of contact
     * @auth 681
     */
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

    /**
     * Clears input fields on add contact view
     * @auth 681
     */
    public void clearAddContactForm() {
        addContactTextFieldName.clear();
        addContactTextFieldPhone.clear();
        addContactTextFieldMail.clear();
    }

    /**
     * Close add contact form
     * @auth 681
     */
    public void closeAddContactForm() {
        addContactView.setVisible(false);
    }

    /**
     * Validate and pass on values for editing contact. Display result to user
     * @auth 681
     */
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

    /**
     * Close edit contact view
     * @auth 681
     */
    public void discardEditContact() {
        editContactView.setVisible(false);
    }

    /**
     * Toggles att promotion view
     * @auth 681
     */
    public void toggleAddPromotion() {
        if(!addPromotionView.isVisible()) {
            addPromotionView.setVisible(true);
            editPromotionView.setVisible(false);
        } else {
            addPromotionView.setVisible(false);
            editPromotionView.setVisible(false);
        }
    }

    /**
     * Toggles edit promotion view
     * @auth 681
     */
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

    /**
     * Loads values for selected promotion to text fields for editing
     * @auth 681
     */
    private void loadPromotionValues() {
        Promotion p = promotionTableView.getSelectionModel().getSelectedItem();
        editPromotionChoiceBoxShow.setValue(p.getShow());
        editPromotionFieldFrom.setText(p.getFrom());
        editPromotionFieldTo.setText(p.getTo());
    }

    /**
     * Takes user selected promotion and calls for deletion in the database. Displays result to user
     * @auth 681
     */
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

    /**
     * Validates and submits input fields for adding promotion object. Displays notification ot user
     * @auth 681
     */
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

    /**
     * Clears add promotion fields
     * @auth 681
     */
    public void clearAddPromotionForm() {
        addPromotionChoiceBoxShow.setValue(null);
        addPromotionFieldFrom.clear();
        addPromotionFieldTo.clear();
    }

    /**
     * Closes add promotion view
     * @auth 681
     */
    public void closeAddPromotionForm() {
        addPromotionView.setVisible(false);
    }

    /**
     * Validates and submits values for editing promotion
     * @auth 681
     */
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

    /**
     * Closes edit promotion view when user clicks discard
     * @auth 681
     */
    public void discardEditPromotion() {
        editPromotionView.setVisible(false);
    }
}
