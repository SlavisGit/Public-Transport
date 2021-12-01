package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.CompanyRepository;
import tuvarna.sit.busservices.data.repository.UserRepository;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;

public class CreateCompanyController implements EventHandler<MouseEvent> {

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


    public void createCompany(MouseEvent mouseEvent) {
        String companyName = name.getText();
        String companyAddress = address.getText();

        CompanyRepository companyRepository = CompanyRepository.getInstance();
        Company company = new Company();
        company.setName(companyName);
        company.setAddress(companyAddress);
        company.setAdministrator(HelloApplication.getUser().getAdministrator());

        companyRepository.save(company);

        UserRepository userRepository = UserRepository.getInstance();
        User user = new User();
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        UserTypeRepository userTypeRepository = UserTypeRepository.getInstance();
        user.setUserType(userTypeRepository.getById(2L).get());
        user.setCompany(company);

        userRepository.save(user);

        handle(mouseEvent);
    }

}
