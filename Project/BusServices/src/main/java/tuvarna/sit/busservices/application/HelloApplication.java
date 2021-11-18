package tuvarna.sit.busservices.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;
import tuvarna.sit.busservices.presentation.controllers.HelloController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    private static Logger logger = Logger.getLogger(HelloApplication.class);

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml"));


        PropertyConfigurator.configure(HelloApplication.class.getResource("/tuvarna/sit/busservices/configuration/log4j.properties"));
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/hello-view.fxml");
        if(path != null) {


            Parent root = FXMLLoader.load(path);
            Scene scene  = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setTitle("Bus services");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaxWidth(700);
            stage.setMaxHeight(600);
            stage.setHeight(400);
            stage.setWidth(270);
            stage.show();
        } else {
            logger.error("Sorry cant load fxml main");
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        launch();
    }


}