package tuvarna.sit.busservices.presentation.controllers;


import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.StationRepository;
import tuvarna.sit.busservices.data.repository.UserRepository;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;

public class CreateStationController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    }

    @Override
    public void handle(MouseEvent event) {
        String stationName1 = stationName.getText();
        String address = stationAddress.getText();
        Time timeToStart = Time.valueOf(startTime.getText() + ":00");
        Time timeToEnd = Time.valueOf(endTime.getText()+ ":00");

        Station station = new Station();
        station.setName(stationName1);
        station.setAddress(address);
        station.setWorkTimeStart(timeToStart);
        station.setWorkTimeEnd(timeToEnd);
        station.setAdmin(HelloApplication.getUser().getAdmin());

        StationRepository stationRepository = StationRepository.getInstance();
        stationRepository.save(station);

        UserRepository userRepository = UserRepository.getInstance();
        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        UserTypeRepository userTypeRepository = UserTypeRepository.getInstance();
        user.setUserType(userTypeRepository.getById(4L).get());
        user.setStation(station);

        userRepository.save(user);

        back(event);
    }

    public void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Administrator");
    }
}

