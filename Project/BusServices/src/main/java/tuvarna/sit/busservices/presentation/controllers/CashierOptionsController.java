package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.common.Constants;

public class CashierOptionsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button buyTicket;

    @FXML
    private Button logOut;

    @FXML
    private Button viewTravel;

    @FXML
    void initialize() {
        assert viewTravel != null : "fx:id=\"viewTravel\" was not injected: check your FXML file 'cashierOptions.fxml'.";
        viewTravel.setOnMouseClicked(this::viewTravels);
        logOut.setOnMouseClicked(this::logOut);
        buyTicket.setOnMouseClicked(this::buyTicket);
    }

    private void buyTicket(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(Constants.View.WINDOW_BUY_TICKET);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.SOLD);
    }

    private void logOut(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(Constants.View.WINDOW_HELLO_VIEW);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.BUS_SERVICE);
    }

    private void viewTravels(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(Constants.View.WINDOW_TRAVEL_LIST);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.TRAVEL);
    }
}
