package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.common.Constants;

public class StationOptionsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button viewCashier;

    @FXML
    private URL location;

    @FXML
    private Button addCashier;

    @FXML
    private Button logOut;

    @FXML
    private Button orderByTickets;

    @FXML
    private Button viewTravel;

    @FXML
    void initialize() {
        assert addCashier != null : "fx:id=\"addCashier\" was not injected: check your FXML file 'stationOptions.fxml'.";
        assert orderByTickets != null : "fx:id=\"orderByTickets\" was not injected: check your FXML file 'stationOptions.fxml'.";

        addCashier.setOnMouseClicked(this::addCashier);
        logOut.setOnMouseClicked(this::logOut);
        viewTravel.setOnMouseClicked(this::viewTravel);
        viewCashier.setOnMouseClicked(this::viewCashier);
        orderByTickets.setOnMouseClicked(this::order);
    }

    private void order(MouseEvent mouseEvent) {
        newWindow(mouseEvent, Constants.View.WINDOW_ORDER_TICKETS, Constants.Titles.ORDER_TICKETS);
    }

    private void viewCashier(MouseEvent mouseEvent) {
        newWindow(mouseEvent,  Constants.View.WINDOW_CASHIER_LIST, Constants.Titles.CASHIER);
    }

    private void viewTravel(MouseEvent mouseEvent) {
        newWindow(mouseEvent,  Constants.View.WINDOW_TRAVEL_LIST, Constants.Titles.TRAVEL);
    }

    private void logOut(MouseEvent mouseEvent) {
        newWindow(mouseEvent,  Constants.View.WINDOW_HELLO_VIEW, Constants.Titles.BUS_SERVICE);
    }

    private void addCashier(MouseEvent mouseEvent) {
        newWindow(mouseEvent, Constants.View.WINDOW_CREATE_CASHIER, Constants.Titles.CASHIER_CREATION);
    }

    private void newWindow(MouseEvent mouseEvent, String s, String s2) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, s2);
    }

}
