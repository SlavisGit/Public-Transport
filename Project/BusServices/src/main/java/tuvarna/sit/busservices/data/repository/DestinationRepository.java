package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Destination;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DestinationRepository implements DAORepository<Destination>{

    private static final Logger log = Logger.getLogger(DestinationRepository.class);

    public static DestinationRepository getInstance() {
        return DestinationRepository.DestinationRepositoryHolder.INSTANCE;
    }

    private static class DestinationRepositoryHolder {

        public static final DestinationRepository INSTANCE = new DestinationRepository();
    }

    @Override
    public void save(Destination destination) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(destination);
            log.info("Destination has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the destination: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Destination destination) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(destination);
            log.info("Destination has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Destination update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Destination destination) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(destination);
            log.info("Destination has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete destination: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Destination> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Destination destination = null;
        try {
            destination = session.get(Destination.class, id);
            log.info("Select destination with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(destination);
    }

    @Override
    public List<Destination> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Destination> destinations = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Destination t";
            destinations.addAll(session.createQuery(jpql, Destination.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all destinations: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return destinations;
    }

}
