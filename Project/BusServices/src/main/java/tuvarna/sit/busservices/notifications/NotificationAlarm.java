package tuvarna.sit.busservices.notifications;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.presentation.controllers.TicketViewController;

import java.io.IOException;
import java.net.URL;

public class NotificationAlarm {
    public void createNotification(String text) {
        Platform.runLater(() -> Notifications.create()
                .title("You have a new notification")
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml"));
                        try {
                            Parent root = (Parent) loader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).showInformation());
    }

    public void checkNewNotifications(User user) {
        createNotification("Hello");
        // TODO: 23.12.2021 г. check who is it and check table with notification for this user and show they
    }
}
