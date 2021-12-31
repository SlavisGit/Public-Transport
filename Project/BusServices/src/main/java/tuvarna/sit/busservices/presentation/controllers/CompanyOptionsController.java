package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;

public class CompanyOptionsController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button viewStation;


    @FXML
    private Button checkOrderTickets;

    @FXML
    private Button addTravel;


    @FXML
    private Button viewTravels;

    @FXML
    private Button logOutCompany;

    @FXML
    private Button addTickets;

    @FXML
    void initialize() {
        assert addTravel != null : "fx:id=\"addTravel\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert viewTravels != null : "fx:id=\"viewTravels\" was not injected: check your FXML file 'companyOptions.fxml'.";
        addTravel.setOnMouseClicked(this::insertTravel);
        logOutCompany.setOnMouseClicked(this::handle);
        viewTravels.setOnMouseClicked(this::viewTravels);
        viewStation.setOnMouseClicked(this::viewStations);
        addTickets.setOnMouseClicked(this::addTickets);
        checkOrderTickets.setOnMouseClicked(this::check);
    }

    private void check(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/orderTickets.fxml", "Order Tickets");
    }

    private void newWindow(MouseEvent mouseEvent, String s, String s2) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, s2);
    }

    private void addTickets(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/addTickets.fxml", "Tickets");
    }

    private void viewStations(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/stationList.fxml", "Stations");
    }

    private void viewTravels(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/travelList.fxml", "Travel");
    }

    private void insertTravel(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/createTravel.fxml", "Insert Travel");
    }

    @Override
    public void handle(MouseEvent event) {
        newWindow(event, "/tuvarna/sit/busservices/presentation.view/hello-view.fxml", "Travel Service");
    }
}

