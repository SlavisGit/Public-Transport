package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CompanyOptions {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addTravel;

    @FXML
    private Label labelHello;

    @FXML
    private Button viewTravels;

    @FXML
    void initialize() {
        assert addTravel != null : "fx:id=\"addTravel\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert labelHello != null : "fx:id=\"labelHello\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert viewTravels != null : "fx:id=\"viewTravels\" was not injected: check your FXML file 'companyOptions.fxml'.";

    }

}

