package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.CashierService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.business.services.UserTypeService;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.busservices.data.entities.User;

public class CreateCashierController {
    CashierService cashierService = CashierService.getInstance();
    UserService userService = UserService.getInstance();
    UserTypeService userTypeService = UserTypeService.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstName;

    @FXML
    private TextField honorarium;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField ucn;

    @FXML
    private Button back;

    @FXML
    private Button create;

    @FXML
    private TextField username;

    @FXML
    void initialize() {
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'createCashier.fxml'.";
        assert honorarium != null : "fx:id=\"honorarium\" was not injected: check your FXML file 'createCashier.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'createCashier.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'createCashier.fxml'.";
        assert ucn != null : "fx:id=\"ucn\" was not injected: check your FXML file 'createCashier.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'createCashier.fxml'.";

        create.setOnMouseClicked(this::createCashier);
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/stationOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station");
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void validationFields() {
        if(firstName.getText() == null || firstName.getText().trim().isEmpty()) {
            messageBox("Field firstName is empty");
        }

        if(lastName.getText() == null || lastName.getText().trim().isEmpty()) {
            messageBox("Field lastName is empty");
        }
        if(username.getText() == null || username.getText().trim().isEmpty()) {
            messageBox("Field username is empty");
        }

        if(password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox("Field password is empty");
        }

        if(ucn.getText() == null || ucn.getText().trim().isEmpty()|| ucn.getText().matches("\"[0-9]{10}\"")) {
            messageBox("Field ucn is empty");
        }

        if(honorarium.getText() == null || honorarium.getText().trim().isEmpty()|| honorarium.getText().matches("\"[0-9]*[\\\\.]?[0-9]*\"")) {
            messageBox("Field honorarium is empty");
        }

    }
    private void createCashier(MouseEvent mouseEvent) {
        validationFields();
        Cashier cashier = new Cashier(firstName.getText(), lastName.getText(), ucn.getText(),
                Double.parseDouble(honorarium.getText()), HelloApplication.getUser().getStation());

        cashierService.save(cashier);

        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        user.setUserType(userTypeService.getById(2L));
        user.setCashier(cashier);
        userService.save(user);

        back(mouseEvent);
    }

}

