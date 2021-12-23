package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.Travel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelRepository implements DAORepository<Travel>{
    private static final Logger log = Logger.getLogger(TravelRepository.class);

    public static TravelRepository getInstance() {
        return TravelRepository.TravelHolder.INSTANCE;
    }

    public List<Travel> getAllForCompany() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Travel> travels = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Travel as t join t.company company WHERE company.id= :idCompany";
            Company company = HelloApplication.getUser().getCompany();

            travels.addAll(session.createQuery(jpql, Travel.class).setParameter("idCompany", company.getID()).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all travels: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return travels;
    }

    public List<Travel> getAllForCashier() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Travel> travels = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Travel as t join t.station st WHERE st.id= :idSt";
            Cashier cashier = HelloApplication.getUser().getCashier();

            travels.addAll(session.createQuery(jpql, Travel.class).setParameter("idSt", cashier.getStation().getID()).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all travels: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return travels;
    }

    public List<Travel> getAllForStation() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Travel> travels = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Travel t WHERE t.dataTo > :data";
            LocalDate now = LocalDate.now();
            travels.addAll(session.createQuery(jpql, Travel.class).setParameter("data", LocalDate.now()).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all travels: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return travels;
    }

    private static class TravelHolder {

        public static final TravelRepository INSTANCE = new TravelRepository();
    }

    @Override
    public void save(Travel travel) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(travel);
            log.info("Travel has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the travel: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Travel travel) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(travel);
            log.info("Travel has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Travel update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Travel travel) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(travel);
            log.info("Travel has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete travel: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Travel> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Travel travel = null;
        try {
            travel = session.get(Travel.class, id);
            log.info("Select travel with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(travel);
    }

    @Override
    public List<Travel> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Travel> travels = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Travel t";
            travels.addAll(session.createQuery(jpql, Travel.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all travels: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return travels;
    }


}
