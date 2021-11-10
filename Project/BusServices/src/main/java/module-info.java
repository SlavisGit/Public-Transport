module tuvarna.sit.busservices {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires log4j;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;

    exports tuvarna.sit.busservices.application;
    opens tuvarna.sit.busservices.application to javafx.fxml;


    exports tuvarna.sit.busservices.presentation.controllers;
    opens tuvarna.sit.busservices.presentation.controllers to javafx.fxml;
}