package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.entities.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StatusRepository implements DAORepository<Status>{

    private static final Logger log = Logger.getLogger(StatusRepository.class);

    public static StatusRepository getInstance() {
        return StatusRepository.StatusRepositoryHolder.INSTANCE;
    }

    private static class StatusRepositoryHolder {

        public static final StatusRepository INSTANCE = new StatusRepository();
    }

    @Override
    public void save(Status status) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(status);
            log.info("Status has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the status: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Status status) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(status);
            log.info("Status has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Status update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Status status) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(status);
            log.info("Status has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to status admin: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Status getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Status status = null;
        try {
            status = session.get(Status.class, id);
            log.info("Select status with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return status;
    }

    @Override
    public List<Status> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Status> statuses = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Status t";
            statuses.addAll(session.createQuery(jpql, Status.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all statuses: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return statuses;
    }


}
