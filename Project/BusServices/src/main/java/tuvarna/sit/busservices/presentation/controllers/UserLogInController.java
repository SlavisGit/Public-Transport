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
import tuvarna.sit.common.Constants;

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
        newWindow(mouseEvent, Constants.View.WINDOW_HELLO_VIEW, Constants.Titles.BUS_SERVICE);
    }


    private void logIn(MouseEvent mouseEvent){
        if(!validationFields())
        {
            return;
        }
        UserService service = UserService.getInstance();
        User userLogin = service.getUserLogin(password.getText(), username.getText(), HelloController.getTypeUser());
        if(userLogin == null) {
            messageBox();
            return;
        }

        HelloApplication.setUser(userLogin);

        switch(userLogin.getUserType().getUserType()){
            case "Admin": {
                newWindow(mouseEvent, Constants.View.WINDOW_ADMIN_OPTION, Constants.Titles.ADMIN);
                break;
            }
            case "Company": {
                newWindow(mouseEvent, Constants.View.WINDOW_COMPANY_OPTION, Constants.Titles.COMPANY);
                break;
            }
            case "Station": {
                newWindow(mouseEvent, Constants.View.WINDOW_STATION_OPTION, Constants.Titles.STATION);
                break;
            }
            case "Cashier": {
                newWindow(mouseEvent, Constants.View.WINDOW_CASHIER_OPTION, Constants.Titles.CASHIER);
                break;
            }
        }

    }
    private boolean validationFields() {
        if(password.getText() == null || password.getText().trim().isEmpty()) {
            messageBox(Constants.MessageError.PASSWORD_EMPTY);
            return false;
        }
        if(username.getText() == null || username.getText().trim().isEmpty() ) {
            messageBox(Constants.MessageError.USERNAME_EMPTY);
            return false;
        }
        return true;
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
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
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(Constants.MessageError.INVALID);
        alert.showAndWait();
    }

}
