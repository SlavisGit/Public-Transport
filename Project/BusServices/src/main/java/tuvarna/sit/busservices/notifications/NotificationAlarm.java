package tuvarna.sit.busservices.notifications;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tuvarna.sit.busservices.business.services.NotificationService;
import tuvarna.sit.busservices.business.services.TravelService;
import tuvarna.sit.busservices.data.entities.Notification;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.data.entities.User;

import java.io.IOException;
import java.util.List;

public class NotificationAlarm {
    public void createNotificationOrderTickets(String text, User user) {
        Platform.runLater(() -> Notifications.create()
                .title("You have a new notification")
                .text(text)
                .graphic(null)
                .hideAfter(Duration.seconds(6))
                .position(Pos.TOP_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (user.getUserType().getUserType().equals("Station") || user.getUserType().getUserType().equals("Company")) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tuvarna/sit/busservices/presentation.view/orderTickets.fxml"));
                            try {
                                Parent root = (Parent) loader.load();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root));
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).showInformation());
    }

    public void checkNewNotifications(User user) {
        NotificationService service = NotificationService.getInstance();
        List<Notification> byIdUser = service.getByIdUser(user.getID());
        if (!byIdUser.isEmpty()) {
            for (Notification not : byIdUser) {
                createNotificationOrderTickets(not.getComment(), user);
                service.delete(not);
            }
        }
        travelWithUnsoldTickets(user);
    }

    private void travelWithUnsoldTickets(User user) {
        TravelService travelService = TravelService.getInstance();
        if (user.getUserType().getUserType().equals("Company")) {
            ObservableList<Travel> allTravelForCompany = travelService.getAllTravelForCompanyWhereDataEnd();
            if(!allTravelForCompany.isEmpty()) {
                createNotificationOrderTickets("There is travel with unsold tickets", user);
            }
        } else if (user.getUserType().getUserType().equals("Station")) {
            ObservableList<Travel> allTravel = travelService.getAllTravelForStationWhereDataEnd();
            if(!allTravel.isEmpty()) {
                createNotificationOrderTickets("There is travel with unsold tickets", user);
            }
        }
    }
}
