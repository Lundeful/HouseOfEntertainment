module HouseOfEntertainment {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires arquillian.junit.core;
    requires arquillian.container.test.api;
    requires shrinkwrap.api;

    opens com.hoe.controller to javafx.fxml;
    exports com.hoe;
}