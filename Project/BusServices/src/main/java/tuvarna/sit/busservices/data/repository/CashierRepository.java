package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Cashier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CashierRepository implements DAORepository<Cashier>{
    private static final Logger log = Logger.getLogger(CashierRepository.class);

    public static CashierRepository getInstance() {
        return CashierRepository.CashierRepositoryHolder.INSTANCE;
    }

    private static class CashierRepositoryHolder {
        public static final CashierRepository INSTANCE = new CashierRepository();
    }


    @Override
    public void save(Cashier cashier) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(cashier);
            log.info("Cashier has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the cashier: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Cashier cashier) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(cashier);
            log.info("Cashier has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Cashier update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Cashier cashier) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(cashier);
            log.info("Cashier has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete cashier: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Cashier> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Cashier cashier = null;
        try {
            cashier = session.get(Cashier.class, id);
            log.info("Select cashier with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(cashier);
    }

    @Override
    public List<Cashier> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Cashier> cashiers = new ArrayList<>();
        try {
            String jpql = "SELECT t FROM Cashier t";
            cashiers.addAll(session.createQuery(jpql, Cashier.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all cashiers: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return cashiers;
    }


}
