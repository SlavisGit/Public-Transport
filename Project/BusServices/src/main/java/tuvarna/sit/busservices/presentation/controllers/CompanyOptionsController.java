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
    private URL location;

    @FXML
    private Button addTravel;

    @FXML
    private Label labelHello;

    @FXML
    private Button viewTravels;

    @FXML
    private Button logOutCompany;


    @FXML
    void initialize() {
        labelHello.setText(labelHello.getText() + HelloApplication.getUser().getUsername());
        assert addTravel != null : "fx:id=\"addTravel\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert labelHello != null : "fx:id=\"labelHello\" was not injected: check your FXML file 'companyOptions.fxml'.";
        assert viewTravels != null : "fx:id=\"viewTravels\" was not injected: check your FXML file 'companyOptions.fxml'.";
        addTravel.setOnMouseClicked(this::insertTravel);
        logOutCompany.setOnMouseClicked(this::handle);
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
        logInApplication.logInUser(resources, event, path, "Company");
    }
}

