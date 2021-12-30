package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.AdministratorService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.business.services.UserTypeService;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.User;

public class CreateAdminController {
    AdministratorService administratorService = AdministratorService.getInstance();
    UserService userService = UserService.getInstance();
    UserTypeService userTypeService = UserTypeService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private Button create;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'createAdmin.fxml'.";
        assert create != null : "fx:id=\"create\" was not injected: check your FXML file 'createAdmin.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'createAdmin.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'createAdmin.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'createAdmin.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'createAdmin.fxml'.";

        create.setOnMouseClicked(this::create);
        back.setOnMouseClicked(this::back);
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
    }
    private void create(MouseEvent mouseEvent) {
        validationFields();
        Administrator administrator = new Administrator(firstName.getText(), lastName.getText());
        administratorService.save(administrator);
        User user = new User(username.getText(), password.getText(), userTypeService.getById(1L));
        user.setAdmin(administrator);
        userService.save(user);
        back(mouseEvent);
    }

    public void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Administrator");
    }
}

