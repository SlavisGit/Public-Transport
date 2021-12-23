package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Destination;
import tuvarna.sit.busservices.data.entities.Ticket;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.presentation.models.TravelListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRepository implements DAORepository<Ticket>{

    private static final Logger log = Logger.getLogger(TicketRepository.class);

    public static TicketRepository getInstance() {
        return TicketRepository.TicketRepositoryHolder.INSTANCE;
    }

    private static class TicketRepositoryHolder {

        public static final TicketRepository INSTANCE = new TicketRepository();
    }

    @Override
    public void save(Ticket ticket) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(ticket);
            log.info("Ticket has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the ticket: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Ticket ticket) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(ticket);
            log.info("Ticket has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Ticket update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Ticket ticket) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(ticket);
            log.info("Ticket has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete ticket: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = null;
        try {
            ticket = session.get(Ticket.class, id);
            log.info("Select ticket with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(ticket);
    }

    public List<Ticket> getWhereDestination(Destination destination) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Ticket t WHERE t.travel.destination= :id and t.status.id= 1";
            tickets.addAll(session.createQuery(jpql, Ticket.class).setParameter("id", destination).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all tickets: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return tickets;
    }
    public List<Ticket> getWhereTravel(Long travelID) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Ticket t WHERE t.travel.id= :id and t.status.id= 2";
            tickets.addAll(session.createQuery(jpql, Ticket.class).setParameter("id", travelID).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all tickets: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return tickets;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Ticket> tickets = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Ticket t";
            tickets.addAll(session.createQuery(jpql, Ticket.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all tickets: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return tickets;
    }


}
