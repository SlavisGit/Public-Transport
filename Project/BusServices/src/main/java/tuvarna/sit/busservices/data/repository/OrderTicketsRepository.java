package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.OrderTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderTicketsRepository implements DAORepository<OrderTickets> {
    private static final Logger log = Logger.getLogger(StationRepository.class);

    public static OrderTicketsRepository getInstance() {
        return OrderTicketsRepository.OrderTicketsRepositoryHolder.INSTANCE;
    }

    private static class OrderTicketsRepositoryHolder {

        public static final OrderTicketsRepository INSTANCE = new OrderTicketsRepository();
    }

    @Override
    public void save(OrderTickets orderTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(orderTickets);
            log.info("orderTickets has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the orderTickets: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(OrderTickets orderTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(orderTickets);
            log.info("orderTickets has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("orderTickets update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(OrderTickets orderTickets) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(orderTickets);
            log.info("orderTickets has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete orderTickets: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<OrderTickets> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        OrderTickets orderTickets = null;
        try {
            orderTickets = session.get(OrderTickets.class, id);
            log.info("Select orderTickets with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(orderTickets);
    }

    @Override
    public List<OrderTickets> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<OrderTickets> orderTickets = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM OrderTickets t";
            orderTickets.addAll(session.createQuery(jpql, OrderTickets.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all orderTickets: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return orderTickets;
    }
}
