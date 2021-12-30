package tuvarna.sit.busservices.business.services;


import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import tuvarna.sit.busservices.data.entities.Notification;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.NotificationRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService implements Service<Notification>{
    private final NotificationRepository notificationRepository = NotificationRepository.getInstance();
    public static NotificationService getInstance() {
        return NotificationService.NotificationServiceHolder.INSTANCE;
    }

    private static class NotificationServiceHolder {
        public static final NotificationService INSTANCE = new NotificationService();
    }

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void update(Notification object) {
        notificationRepository.update(object);
    }

    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.getById(id);
    }

    public ObservableList<Notification> getAll(){
        List<Notification> stations = notificationRepository.getAll();
        return FXCollections.observableList(
                stations.stream().map(m -> new Notification(m.getComment(), m.getUser()))
                        .collect(Collectors.toList()));
    }

    public List<Notification> getByIdUser(Long id) {
        return notificationRepository.getByIdUser(id);
    }
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
