package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
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
import tuvarna.sit.busservices.business.services.CompanyService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.business.services.UserTypeService;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.User;

public class CreateCompanyController implements EventHandler<MouseEvent> {
    CompanyService companyService = CompanyService.getInstance();
    UserService userService = UserService.getInstance();
    UserTypeService userTypeService = UserTypeService.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button cancel;

    @FXML
    private Button create;

    @FXML
    private TextField address;

    @FXML
    private TextField name;

    @FXML
    void initialize() {
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'createCompany.fxml'.";
        assert create != null : "fx:id=\"create\" was not injected: check your FXML file 'createCompany.fxml'.";

        cancel.setOnMouseClicked(this::handle);
        create.setOnMouseClicked(this::createCompany);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Administrator");
    }

    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void validationFields() {
        if (name.getText() == null || name.getText().trim().isEmpty()) {
            messageBox("Field name is empty");
        }

        if (address.getText() == null || address.getText().trim().isEmpty()) {
            messageBox("Field address is empty");
        }
        if (username.getText() == null || username.getText().trim().isEmpty()) {
            messageBox("Field username is empty");
        }

        if (password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox("Field password is empty");
        }
    }

    public void createCompany(MouseEvent mouseEvent) {
        validationFields();
        Company company = new Company();
        company.setName(name.getText());
        company.setAddress(address.getText());
        company.setAdministrator(HelloApplication.getUser().getAdministrator());

        companyService.save(company);

        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        user.setUserType(userTypeService.getById(3L));
        user.setCompany(company);

        userService.save(user);

        handle(mouseEvent);
    }

}
