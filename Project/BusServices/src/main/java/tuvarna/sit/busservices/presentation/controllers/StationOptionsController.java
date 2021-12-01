package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StationOptionsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addCashier;

    @FXML
    private Label hello;

    @FXML
    private Button orderByTickets;

    @FXML
    void initialize() {
        assert addCashier != null : "fx:id=\"addCashier\" was not injected: check your FXML file 'stationOptions.fxml'.";
        assert hello != null : "fx:id=\"hello\" was not injected: check your FXML file 'stationOptions.fxml'.";
        assert orderByTickets != null : "fx:id=\"orderByTickets\" was not injected: check your FXML file 'stationOptions.fxml'.";

    }

}
