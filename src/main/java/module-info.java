module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens com.hoe.controller to javafx.fxml;
    exports com.hoe;
}