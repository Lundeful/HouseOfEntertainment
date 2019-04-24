module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;
    requires arquillian.container.test.api;
    requires arquillian.junit.core;
    requires junit;

    opens com.hoe.controller to javafx.fxml;
    exports com.hoe;
}