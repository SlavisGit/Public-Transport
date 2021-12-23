package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.AdministratorRepository;
import tuvarna.sit.busservices.data.repository.UserRepository;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;

public class CreateAdminController {

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

    private void create(MouseEvent mouseEvent) {
        Administrator administrator = new Administrator(firstName.getText(), lastName.getText());
        AdministratorRepository administratorRepository = AdministratorRepository.getInstance();
        administratorRepository.save(administrator);

        UserRepository userRepository = UserRepository.getInstance();
        UserTypeRepository userTypeRepository = UserTypeRepository.getInstance();
        User user = new User(username.getText(), password.getText(), userTypeRepository.getById(1L).get());
        user.setAdmin(administrator);
        userRepository.save(user);
        back(mouseEvent);
    }

    public void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Administrator");
    }
}

