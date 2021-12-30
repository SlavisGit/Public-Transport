package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Notification;
import tuvarna.sit.busservices.data.entities.OrderTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotificationRepository implements DAORepository<Notification> {
    private static final Logger log = Logger.getLogger(StationRepository.class);

    public static NotificationRepository getInstance() {
        return NotificationRepository.NotificationRepositoryHolder.INSTANCE;
    }

    private static class NotificationRepositoryHolder {

        public static final NotificationRepository INSTANCE = new NotificationRepository();
    }

    @Override
    public void save(Notification notification) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(notification);
            log.info("notification has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the notification: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Notification notification) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(notification);
            log.info("notification has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("notification update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Notification notification) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(notification);
            log.info("notification has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete notification: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Notification getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Notification notification = null;
        try {
            notification = session.get(Notification.class, id);
            log.info("Select notification with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return notification;
    }

    @Override
    public List<Notification> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Notification> notifications = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Notification t";
            notifications.addAll(session.createQuery(jpql, Notification.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all notifications: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return notifications;
    }

    public List<Notification> getByIdUser(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Notification> notifications = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Notification t WHERE t.user.id = :id";
            notifications.addAll(session.createQuery(jpql, Notification.class).setParameter("id", id).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all notifications: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return notifications;
    }
}
