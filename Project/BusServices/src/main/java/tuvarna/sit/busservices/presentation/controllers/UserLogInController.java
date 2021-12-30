package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tuvarna.sit.busservices.application.HelloApplication;
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
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/hello-view.fxml", "Bus services");
    }


    public void logIn(MouseEvent mouseEvent){
        validationFields();
        UserService service = UserService.getInstance();
        User userLogin = service.getUserLogin(password, username, HelloController.getTypeUser());
        if(userLogin == null) {
            messageBox();
            return;
        }

        HelloApplication.setUser(userLogin);

        switch(userLogin.getUserType().getUserType()){
            case "Admin": {
                newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml", "Administrator");
                break;
            }
            case "Company": {
                newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/companyOptions.fxml", "Company");
                break;
            }
            case "Station": {
                newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/stationOptions.fxml", "Station");
                break;
            }
            case "Cashier": {
                newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml", "Cashier");
                break;
            }
        }

    }
    private void validationFields() {
        if(password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox("Field Count Tickets is empty");
        }
        if(username.getText() == null || username.getText().trim().isEmpty() ) {
            messageBox("Field Price is empty");
        }
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void newWindow(MouseEvent mouseEvent, String s, String cashier) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, cashier);
    }

    private void messageBox() {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText("Invalid username or password. Please, try again.");
        alert.showAndWait();
    }

}
