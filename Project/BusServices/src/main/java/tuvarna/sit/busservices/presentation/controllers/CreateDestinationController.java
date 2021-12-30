package tuvarna.sit.busservices.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.business.services.DestinationService;
import tuvarna.sit.busservices.data.entities.Destination;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateDestinationController {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    private static DestinationService destinationService = DestinationService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button create;

    @FXML
    private TextField destination;

    @FXML
    void initialize() {
        create.setOnMouseClicked(this::createType);
    }

    private void createType(MouseEvent mouseEvent) {
        Destination destination1 = new Destination(this.destination.getText());
        destinationService.save(destination1);

        Stage stage = (Stage) this.destination.getScene().getWindow();
        stage.close();
    }


}
