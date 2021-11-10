package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.w3c.dom.events.MouseEvent;
import tuvarna.sit.busservices.business.services.UserTypeService;
import tuvarna.sit.busservices.data.entities.UserType;
import tuvarna.sit.busservices.presentation.models.UserTypeListView;

public class HelloController {

    private final UserTypeService service = UserTypeService.getInstance();



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ID_DLG_LOG_IN"
    private AnchorPane ID_DLG_LOG_IN; // Value injected by FXMLLoader

    @FXML // fx:id="ID_BTN_ADMIN"
    private Button ID_BTN_ADMIN; // Value injected by FXMLLoader

    @FXML // fx:id="ID_BTN_CLIENT"
    private Button ID_BTN_CLIENT; // Value injected by FXMLLoader

    //@FXML
    //private ListView<UserTypeListView> listView;

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ID_DLG_LOG_IN != null : "fx:id=\"ID_DLG_LOG_IN\" was not injected: check your FXML file 'Untitled'.";
        assert ID_BTN_ADMIN != null : "fx:id=\"ID_BTN_ADMIN\" was not injected: check your FXML file 'Untitled'.";
        assert ID_BTN_CLIENT != null : "fx:id=\"ID_BTN_CLIENT\" was not injected: check your FXML file 'Untitled'.";
    }

    //@Override
   // public void handle(MouseEvent mouseEvent) {
     //   ObservableList<UserTypeListView> userTypes =  service.getAllUsers();
  //      listView.setItems(userTypes);
//
    //}

}

