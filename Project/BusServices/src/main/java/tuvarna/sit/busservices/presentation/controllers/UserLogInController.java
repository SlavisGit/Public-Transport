package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.data.entities.User;

public class UserLogInController implements EventHandler<MouseEvent> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button go_back;

    @FXML
    private Button logIn;

    @FXML
    private AnchorPane logInView;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void initialize() {
        assert go_back != null : "fx:id=\"go_back\" was not injected: check your FXML file 'Untitled'.";
        assert logIn != null : "fx:id=\"logIn\" was not injected: check your FXML file 'Untitled'.";
        assert logInView != null : "fx:id=\"logInView\" was not injected: check your FXML file 'Untitled'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Untitled'.";

        go_back.setOnMouseClicked(this::handle);
        logIn.setOnMouseClicked(this::logIn);
    }

    @Override
    public void handle(MouseEvent mouseEvent){
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Bus services");
    }


    public void logIn(MouseEvent mouseEvent){
        UserService service = UserService.getInstance();
        User userLogin = service.getUserLogin(password, username);
        if(userLogin == null) {
            Alert alert = new Alert(Alert.AlertType.
                    ERROR);
            alert.setTitle("Incorrect data");
            alert.setContentText("Invalid username or password. Please, try again.");
            alert.showAndWait();
            return;
        }
        switch(userLogin.getUserType().getUserType()){
            case "Admin": {
                NewWindowApplication logInApplication = new NewWindowApplication();
                URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml");
                logInApplication.logInUser(resources, mouseEvent, path, "Administrator");
                break;
            }
            case "Company":
                NewWindowApplication logInApplication = new NewWindowApplication();
                URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
                logInApplication.logInUser(resources, mouseEvent, path, "Company");
                break;
            case "Station":
                break;
            case "Cashier":
                break;
        }









    }

}
