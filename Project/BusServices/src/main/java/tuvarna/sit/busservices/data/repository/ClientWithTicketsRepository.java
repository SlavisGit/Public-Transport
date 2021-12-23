package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Client;
import tuvarna.sit.busservices.data.entities.ClientWithTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientWithTicketsRepository implements DAORepository<ClientWithTickets> {
    private static final Logger log = Logger.getLogger(ClientWithTicketsRepository.class);

    public static ClientWithTicketsRepository getInstance() {
        return ClientWithTicketsRepository.ClientWithTicketsRepositoryHolder.INSTANCE;
    }

    @Override
    public void save(ClientWithTickets clientWithTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(clientWithTickets);
            log.info("clientWithTickets has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to clientWithTickets the client: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(ClientWithTickets clientWithTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(clientWithTickets);
            log.info("clientWithTickets has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("clientWithTickets update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(ClientWithTickets clientWithTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(clientWithTickets);
            log.info("clientWithTickets has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete clientWithTickets: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<ClientWithTickets> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        ClientWithTickets clientWithTickets = null;
        try {
            clientWithTickets = session.get(ClientWithTickets.class, id);
            log.info("Select clientWithTickets with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return Optional.ofNullable(clientWithTickets);
    }

    @Override
    public List<ClientWithTickets> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<ClientWithTickets> clientWithTickets = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM ClientWithTickets t";
            clientWithTickets.addAll(session.createQuery(jpql, ClientWithTickets.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all clientWithTickets: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return clientWithTickets;
    }

    private static class ClientWithTicketsRepositoryHolder {
        public static final ClientWithTicketsRepository INSTANCE = new ClientWithTicketsRepository();
    }

}
