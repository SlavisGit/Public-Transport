package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;

public class AdministratorOptionsController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button createCompany;

    @FXML
    private Button createStation;

    @FXML
    void initialize() {
        assert createCompany != null : "fx:id=\"createCompany\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        assert createStation != null : "fx:id=\"createStation\" was not injected: check your FXML file 'administratorOptions.fxml'.";
        createCompany.setOnMouseClicked(this::handle);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/createCompany.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company creation");
    }
}
