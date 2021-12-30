package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportRepository implements DAORepository<Transport>{

    private static final Logger log = Logger.getLogger(TransportRepository.class);

    public static TransportRepository getInstance() {
        return TransportRepository.TransportRepositoryHolder.INSTANCE;
    }

    private static class TransportRepositoryHolder {

        public static final TransportRepository INSTANCE = new TransportRepository();
    }

    @Override
    public void save(Transport transport) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(transport);
            log.info("Transport has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the transport: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Transport transport) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(transport);
            log.info("Transport has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Transport update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Transport transport) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(transport);
            log.info("Transport has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete transport: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Transport getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Transport transport = null;
        try {
            transport = session.get(Transport.class, id);
            log.info("Select transport with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return transport;
    }

    @Override
    public List<Transport> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Transport> transports = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Transport t";
            transports.addAll(session.createQuery(jpql, Transport.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all transports: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return transports;
    }
}
