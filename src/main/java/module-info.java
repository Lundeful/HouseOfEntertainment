module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}