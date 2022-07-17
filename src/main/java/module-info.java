module me.kano.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens me.kano.app to javafx.fxml;
    exports me.kano.app;
    exports me.kano.app.controller;
    opens me.kano.app.controller to javafx.fxml;
}