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
    private Label hello;

    @FXML
    private Button createCompany;

    @FXML
    private Button createStation;

    @FXML
    private Button logOut;

    @FXML
    void initialize() {
        hello.setText(hello.getText() + HelloApplication.getUser().getUsername());
        assert createCompany != null : "fx:id=\"createCompany\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert createStation != null : "fx:id=\"createStation\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert hello != null : "fx:id=\"hello\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert logOut != null : "fx:id=\"logOut\" was not injected: check your FXML file 'administratorOptions.fxml'.";

        createCompany.setOnMouseClicked(this::handle);
        logOut.setOnMouseClicked(this::logOut);
        createStation.setOnMouseClicked(this::createStationBut);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/createCompany.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company creation");
    }

    public void logOut(MouseEvent mouseEvent) {
        HelloApplication.setUser(null);
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Bus services");
    }

    public void createStationBut(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/createStation.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station creation");
    }
}
