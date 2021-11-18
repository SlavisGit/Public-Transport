package tuvarna.sit.busservices.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.repository.CompanyRepository;

public class CreateCompanyController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
        

        companyRepository.save(company);
    }

}
