package tuvarna.sit.busservices.business.services;


import tuvarna.sit.busservices.data.entities.Notification;
import tuvarna.sit.busservices.data.repository.NotificationRepository;

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
}
