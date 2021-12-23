package tuvarna.sit.busservices.data.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

public class ClientRepository implements DAORepository<Client> {
    private static final Logger log = Logger.getLogger(ClientRepository.class);

    public static ClientRepository getInstance() {
        return ClientRepository.ClientRepositoryHolder.INSTANCE;
    }

    private static class ClientRepositoryHolder {
        public static final ClientRepository INSTANCE = new ClientRepository();
    }

    @Override
    public void save(Client client) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(client);
            log.info("client has been saved.");

            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to save the client: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Client client) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(client);
            log.info("client has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("client update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Client client) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(client);
            log.info("client has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete client: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Client> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Client client = null;
        try {
            client = session.get(Client.class, id);
            log.info("Select client with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return Optional.ofNullable(client);
    }


    @Override
    public List<Client> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Client> clients = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Client t";
            clients.addAll(session.createQuery(jpql, Client.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all clients: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return clients;
    }


}
