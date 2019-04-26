module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hoe.controller to javafx.fxml;
    exports com.hoe;
}