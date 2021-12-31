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

public class AdministratorOptionsController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button viewCashierRating;

    @FXML
    private Button createCompany;

    @FXML
    private Button createStation;

    @FXML
    private Button logOut;
    
    @FXML
    private Button createAdmin;

    @FXML
    void initialize() {
        assert createCompany != null : "fx:id=\"createCompany\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert createStation != null : "fx:id=\"createStation\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'administratorOptions.fxml'.";

        createCompany.setOnMouseClicked(this::handle);
        logOut.setOnMouseClicked(this::logOut);
        createStation.setOnMouseClicked(this::createStationBut);
        createAdmin.setOnMouseClicked(this::createAdmin);
        viewCashierRating.setOnMouseClicked(this::viewRating);
    }

    private void viewRating(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/cashierList.fxml", "Cashier Rating");
    }

    private void createAdmin(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/createAdmin.fxml", "Admin creation");
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/createCompany.fxml", "Company creation");
    }

    public void logOut(MouseEvent mouseEvent) {
        HelloApplication.setUser(null);
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/hello-view.fxml", "Bus services");
    }

    public void createStationBut(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/createStation.fxml", "Station creation");
    }

    private void newWindow(MouseEvent mouseEvent, String s, String s2) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, s2);
    }
}
