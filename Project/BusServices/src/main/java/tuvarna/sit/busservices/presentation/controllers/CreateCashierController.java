package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.security.PrivateKey;
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
import tuvarna.sit.common.Constants;

public class CreateCashierController {
    private static CashierService cashierService = CashierService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static UserTypeService userTypeService = UserTypeService.getInstance();


    @FXML
    private ResourceBundle resources;

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
        URL path = getClass().getResource(Constants.View.WINDOW_STATION_OPTION);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.STATION);
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean validationFields() {
        if(firstName.getText() == null || firstName.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.FIRSTNAME_EMPTY);
            return false;
        }

        if(lastName.getText() == null || lastName.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.LASTNAME_EMPTY);
            return false;
        }
        if(username.getText() == null || username.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.USERNAME_EMPTY);
            return false;
        }

        if(password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.PASSWORD_EMPTY);
            return false;
        }

        if(ucn.getText() == null || ucn.getText().trim().isEmpty()|| !ucn.getText().matches(Constants.Regex.FOR_UCN)) {
            messageBox(Constants.MessageError.UCN_EMPTY);
            return false;
        } 

        if(honorarium.getText() == null || honorarium.getText().trim().isEmpty()|| !honorarium.getText().matches(Constants.Regex.FOR_DOUBLE_DIGIT)) {
            messageBox(Constants.MessageError.HONORARIUM_EMPTY);
            return false;
        }
        return true;

    }
    private void createCashier(MouseEvent mouseEvent) {
        if(!validationFields())
        {
            return;
        }
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

