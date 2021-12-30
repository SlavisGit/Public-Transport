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
    private URL location;

    @FXML
    private Button checkOrderTickets;

    @FXML
    private Button addTravel;

    @FXML
    private Label labelHello;

    @FXML
    private Button viewTravels;

    @FXML
    private Button logOutCompany;

    @FXML
    private Button addTickets;

    @FXML
    void initialize() {
        labelHello.setText(labelHello.getText() + HelloApplication.getUser().getUsername());
        assert addTravel != null : "fx:id=\"addTravel\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert labelHello != null : "fx:id=\"labelHello\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert viewTravels != null : "fx:id=\"viewTravels\" was not injected: check your FXML file 'companyOptions.fxml'.";
        addTravel.setOnMouseClicked(this::insertTravel);
        logOutCompany.setOnMouseClicked(this::handle);
        viewTravels.setOnMouseClicked(this::viewTravels);
        viewStation.setOnMouseClicked(this::viewStations);
        addTickets.setOnMouseClicked(this::addTickets);
        checkOrderTickets.setOnMouseClicked(this::check);
    }

    private void check(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/orderTickets.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Order Tickets");
    }

    private void addTickets(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/addTickets.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Tickets");
    }

    private void viewStations(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/stationList.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Stations");
    }

    private void viewTravels(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/travelList.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Travel");
    }

    private void insertTravel(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/createTravel.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Insert Travel");
    }

    @Override
    public void handle(MouseEvent event) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        logInApplication.logInUser(resources, event, path, "Travel Service");
    }
}

