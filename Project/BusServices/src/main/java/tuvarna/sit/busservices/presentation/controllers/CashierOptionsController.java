package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;

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
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/buyTickets.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel Service");
    }

    private void logOut(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel Service");
    }

    private void viewTravels(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/travelList.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel");
    }
}
