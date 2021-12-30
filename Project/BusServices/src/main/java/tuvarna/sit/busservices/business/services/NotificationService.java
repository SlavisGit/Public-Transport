package tuvarna.sit.busservices.business.services;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Notification;
import tuvarna.sit.busservices.data.repository.NotificationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
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

    public void delete(Notification notification) {
        notificationRepository.delete(notification);
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
}
