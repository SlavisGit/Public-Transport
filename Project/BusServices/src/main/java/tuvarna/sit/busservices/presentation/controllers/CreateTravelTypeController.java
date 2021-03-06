package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.business.services.TravelTypeService;
import tuvarna.sit.busservices.data.entities.TravelType;
import tuvarna.sit.common.Constants;

public class CreateTravelTypeController {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    private static TravelTypeService travelTypeService = TravelTypeService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button create;

    @FXML
    private TextField travelType;
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean validationFields() {
        if(travelType.getText() == null || travelType.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.TRAVEL_TYPE_EMPTY);
            return false;
        }
        return true;
    }
    @FXML
    void initialize() {
        create.setOnMouseClicked(this::createType);
    }

    private void createType(MouseEvent mouseEvent) {
        if(!validationFields())
        {
            return;
        }
        TravelType travelType = new TravelType(this.travelType.getText());
        travelTypeService.save(travelType);

        Stage stage = (Stage) this.travelType.getScene().getWindow();
        stage.close();
    }

}
