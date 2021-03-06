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
import tuvarna.sit.common.Constants;

public class CreateCompanyController implements EventHandler<MouseEvent> {
    private static CompanyService companyService = CompanyService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static UserTypeService userTypeService = UserTypeService.getInstance();


    @FXML
    private ResourceBundle resources;

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
        URL path = getClass().getResource(Constants.View.WINDOW_ADMIN_OPTION);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.ADMIN);
    }

    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean validationFields() {
        if (name.getText() == null || name.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.NAME_EMPTY);
            return false;
        }

        if (address.getText() == null || address.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.ADDRESS_EMPTY);
            return false;
        }
        if (username.getText() == null || username.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.USERNAME_EMPTY);
            return false;
        }

        if (password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.PASSWORD_EMPTY);
            return false;
        }
        return true;
    }

    public void createCompany(MouseEvent mouseEvent) {
        if(!validationFields())
        {
            return;
        }
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
