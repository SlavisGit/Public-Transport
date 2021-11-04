package sample;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Controller {

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

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ID_DLG_LOG_IN != null : "fx:id=\"ID_DLG_LOG_IN\" was not injected: check your FXML file 'Untitled'.";
        assert ID_BTN_ADMIN != null : "fx:id=\"ID_BTN_ADMIN\" was not injected: check your FXML file 'Untitled'.";
        assert ID_BTN_CLIENT != null : "fx:id=\"ID_BTN_CLIENT\" was not injected: check your FXML file 'Untitled'.";


    }
}

