package tuvarna.sit.busservices.presentation.controllers;


import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.StationService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.business.services.UserTypeService;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.common.Constants;

public class CreateStationController implements EventHandler<MouseEvent> {

    private static StationService stationService = StationService.getInstance();
    private static UserService userService = UserService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button createStationButton;

    @FXML
    private TextField endTime;

    @FXML
    private Button goBackFromStation;

    @FXML
    private TextField startTime;

    @FXML
    private TextField stationAddress;

    @FXML
    private TextField stationName;

    @FXML
    void initialize() {
        assert createStationButton != null : "fx:id=\"createStationButton\" was not injected: check your FXML file 'createStation.fxml'.";
        assert goBackFromStation != null : "fx:id=\"goBackFromStation\" was not injected: check your FXML file 'createStation.fxml'.";

        createStationButton.setOnMouseClicked(this::handle);
        goBackFromStation.setOnMouseClicked(this::back);
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean validationFields() {
        if(username.getText() == null || username.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.USERNAME_EMPTY);
            return false;
        }

        if(password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.PASSWORD_EMPTY);
            return false;
        }
        if(stationName.getText() == null || stationName.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.STATION_EMPTY);
            return false;
        }

        if(stationAddress.getText() == null || stationAddress.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.ADDRESS_EMPTY);
            return false;
        }
        if(startTime.getText() == null || startTime.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.START_TIME_EMPTY);
            return false;
        }

        if(endTime.getText() == null || endTime.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.END_TIME_EMPTY);
            return false;
        }
        return true;
    }
    @Override
    public void handle(MouseEvent event) {
        if(!validationFields())
        {
            return;
        }
        Station station = new Station();
        station.setName(stationName.getText());
        station.setAddress(stationAddress.getText());
        station.setWorkTimeStart(Time.valueOf(startTime.getText() + ":00"));
        station.setWorkTimeEnd(Time.valueOf(endTime.getText()+ ":00"));
        station.setAdmin(HelloApplication.getUser().getAdmin());

        stationService.save(station);

        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        UserTypeService userTypeService = UserTypeService.getInstance();
        user.setUserType(userTypeService.getById(4L));
        user.setStation(station);

        userService.save(user);

        back(event);
    }

    public void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(Constants.View.WINDOW_ADMIN_OPTION);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.ADMIN);
    }
}

