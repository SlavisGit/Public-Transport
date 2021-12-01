package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.data.entities.TravelType;
import tuvarna.sit.busservices.data.repository.TravelTypeRepository;

public class CreateTravelTypeController {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    TravelTypeRepository travelTypeRepository = TravelTypeRepository.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button create;

    @FXML
    private TextField travelType;

    @FXML
    void initialize() {
        create.setOnMouseClicked(this::createType);
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {

    }

    private void createType(MouseEvent mouseEvent) {
        travelTypeRepository.save(new TravelType(travelType.getText()));
    }

}
