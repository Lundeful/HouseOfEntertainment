package com.hoe;

import com.hoe.model.*;
import com.hoe.model.dataloading.CSVLoader;
import com.hoe.model.datasaving.CSVSaver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


public class Main extends Application {

    private static Location location;
    private static Show show;
    private static ContactPerson contactPerson;
    private static Promotion promotion;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/hoe/fxml/MainView.fxml"));


        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/hoe/css/stylesheet.css").toExternalForm());

        stage.setTitle("House of Entertainment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IDCreator id = new IDCreator();

        Location l = new Location(id.randomKeyGen(location),"ODEON");
        l.setNumberOfSeats(200);
        l.setTypeOfLocation("Cinema");

        ContactPerson c = new ContactPerson(id.randomKeyGen(contactPerson));
        c.setName("Magnus");
        c.setEmail("Magnus.123@heihallo.no");
        c.setPhoneNumber("99122091");
        c.setWebsite("Chasedaddy.com");
        c.setAffiliation("Manager");
        c.setOther("Other");

        Show s = new Show(id.randomKeyGen(show),l.getLocationID(),699);
        s.setProgram("Program");
        s.setShowType("Movie");
        s.setTime("12:00");

        Promotion p = new Promotion(id.randomKeyGen(promotion),s.getShowID());
        p.setTo("09:00");
        p.setFrom("12:00");

        Database d = new Database();
        d.addContact(c);
        d.addPromotion(p);
        d.addLocation(l);
        d.addShow(s);

        CSVSaver save = new CSVSaver();
        CSVLoader load = new CSVLoader();

        save.saveData("C:\\Users\\magnu\\Documents\\OsloMet\\Programutvikling\\semesteroppgave19\\CSVFile.csv",d);
        System.out.println("Alright");

        Database data = load.readData("CSVFile.csv");

        System.out.println(data.getContacts().get(0));
        System.out.println(data.getLocations().get(0));
        System.out.println(data.getPromotions().get(0));
        System.out.println(data.getShows().get(0));

        launch(args);
    }

}
