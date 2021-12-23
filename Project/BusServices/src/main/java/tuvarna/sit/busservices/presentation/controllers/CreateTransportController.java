package tuvarna.sit.busservices.presentation.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.data.entities.Transport;
import tuvarna.sit.busservices.data.entities.TravelType;
import tuvarna.sit.busservices.data.repository.TransportRepository;
import tuvarna.sit.busservices.data.repository.TravelTypeRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTransportController {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    TransportRepository transportRepository = TransportRepository.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button create;

    @FXML
    private TextField transport;

    @FXML
    void initialize() {
        create.setOnMouseClicked(this::createType);
    }

    private void createType(MouseEvent mouseEvent) {
        Transport transport = new Transport(this.transport.getText());
        transportRepository.save(transport);

        Stage stage = (Stage) this.transport.getScene().getWindow();
        stage.close();
    }

}
