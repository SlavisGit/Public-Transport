package tuvarna.sit.busservices.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.business.services.TransportService;
import tuvarna.sit.busservices.data.entities.Transport;
import tuvarna.sit.common.Constants;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTransportController {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    private static TransportService transportService = TransportService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button create;

    @FXML
    private TextField transport;

    @FXML
    void initialize() {
        create.setOnMouseClicked(this::createType);
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean validationFields() {
        if(transport.getText() == null || transport.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.TRANSPORT_EMPTY);
            return false;
        }
        return true;
    }
    private void createType(MouseEvent mouseEvent) {
        if(!validationFields())
        {
            return;
        }
        Transport transport = new Transport(this.transport.getText());
        transportService.save(transport);

        Stage stage = (Stage) this.transport.getScene().getWindow();
        stage.close();
    }

}
