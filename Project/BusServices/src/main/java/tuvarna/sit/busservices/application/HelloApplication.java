package tuvarna.sit.busservices.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.notifications.NotificationAlarm;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    private static Logger logger = Logger.getLogger(HelloApplication.class);
    private static User user = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        HelloApplication.user = user;
        if(user != null) {
            NotificationAlarm notification = new NotificationAlarm();
            notification.checkNewNotifications(user);
        }
    }

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