module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;

<<<<<<< Updated upstream
    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
=======

    opens com.hoe.controller to javafx.fxml;
    exports com.hoe;
>>>>>>> Stashed changes
}