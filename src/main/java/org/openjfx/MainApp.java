package org.openjfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

<<<<<<< Updated upstream:src/main/java/org/openjfx/MainApp.java

public class MainApp extends Application {
=======
import java.util.Date;

public class Main extends Application {
>>>>>>> Stashed changes:src/main/java/com/hoe/Main.java

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX and Maven");
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
        System.out.println("|");
        launch(args);

      /*  Date date = new Date();
        date.setTime(12);
        Date date2 = new Date();
        date2.setTime(13);
        ContactPerson person = new ContactPerson("123456");
        person.setName("Ola");
        person.setEmail("ola@nets.no");
        person.setWebsite("oslomet.no");
        person.setAffiliation("Some");
        person.setOther("Hehe");
        person.setPhoneNumber("12343223");

        Location location = new Location("324132", "DDE");
        location.setNumberOfSeats(9000);
        location.setTypeOfLocation("Consert");

        Show show = new Show("329485", location, 299);

        Promotion promotion = new Promotion("894753", show);
        promotion.setFrom(date);
        promotion.setTo(date2);

        Database data = new Database();
        data.addContact(person);
        data.addLocation(location);
        data.addPromotion(promotion);
        data.addShow(show);

        CSVSaver saver = new CSVSaver();
        saver.saveData("CSVSave.csv", data); */
    }

}
