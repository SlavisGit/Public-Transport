package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;

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
    private Label hello;

    @FXML
    private Button logOut;

    @FXML
    private Button orderByTickets;

    @FXML
    private Button viewTravel;

    @FXML
    void initialize() {
        assert addCashier != null : "fx:id=\"addCashier\" was not injected: check your FXML file 'stationOptions.fxml'.";
        assert hello != null : "fx:id=\"hello\" was not injected: check your FXML file 'stationOptions.fxml'.";
        assert orderByTickets != null : "fx:id=\"orderByTickets\" was not injected: check your FXML file 'stationOptions.fxml'.";

        addCashier.setOnMouseClicked(this::addCashier);
        logOut.setOnMouseClicked(this::logOut);
        viewTravel.setOnMouseClicked(this::viewTravel);
        viewCashier.setOnMouseClicked(this::viewCashier);
        orderByTickets.setOnMouseClicked(this::order);
    }

    private void order(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/orderTickets.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Order Tickets");
    }

    private void viewCashier(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/cashierList.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Cashier");
    }

    private void viewTravel(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/travelList.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel");
    }

    private void logOut(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel Service");
    }

    private void addCashier(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/createCashier.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Cashier creation");
    }

}
