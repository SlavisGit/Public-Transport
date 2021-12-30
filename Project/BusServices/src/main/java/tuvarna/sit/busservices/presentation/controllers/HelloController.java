package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.UserTypeService;

public class HelloController implements EventHandler<MouseEvent> {

    private final UserTypeService service = UserTypeService.getInstance();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private AnchorPane ID_DLG_LOG_IN; // Value injected by FXMLLoader

    @FXML
    private Button admin; // Value injected by FXMLLoader

    @FXML
    private Button company; // Value injected by FXMLLoader

    @FXML
    private Button station; // Value injected by FXMLLoader

    @FXML
    private Button cashier; // Value injected by FXMLLoader

    private static String typeUser;

    public static String getTypeUser() {
        return typeUser;
    }

    public static void setTypeUser(String typeUser) {
        HelloController.typeUser = typeUser;
    }

    @FXML
    private void initialize() {
        admin.setOnMouseClicked(this::handle) ;
        company.setOnMouseClicked(this::companyLogIn);
        station.setOnMouseClicked(this::stationLogIn);
        cashier.setOnMouseClicked(this::cashierLogIn);
    }

    private void cashierLogIn(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "Cashier", "Cashier login");
    }

    private void newWindow(MouseEvent mouseEvent, String cashier, String s) {
        typeUser = cashier;
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, s);
    }

    private void stationLogIn(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "Station", "Station login");
    }

    private void companyLogIn(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "Company", "Company login");
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "Admin", "Admin login");
    }

}

