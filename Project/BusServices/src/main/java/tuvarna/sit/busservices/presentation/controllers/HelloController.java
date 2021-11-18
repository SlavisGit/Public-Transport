package tuvarna.sit.busservices.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.UserTypeService;

public class HelloController implements EventHandler<MouseEvent> {

    private final UserTypeService service = UserTypeService.getInstance();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private AnchorPane ID_DLG_LOG_IN; // Value injected by FXMLLoader

    @FXML
    private Button admin; // Value injected by FXMLLoader

    @FXML
    private Button company; // Value injected by FXMLLoader

    @FXML
    private Button station; // Value injected by FXMLLoader

    @FXML
    private Button cashier; // Value injected by FXMLLoader

    @FXML
    private void initialize() {
        admin.setOnMouseClicked(this::handle);
        company.setOnMouseClicked(this::companyLogIn);
        station.setOnMouseClicked(this::stationLogIn);
        cashier.setOnMouseClicked(this::cashierLogIn);
    }

    private void cashierLogIn(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Cashier login");
    }

    private void stationLogIn(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station login");
    }

    private void companyLogIn(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company login");
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Admin login");
    }

}

