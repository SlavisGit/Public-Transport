package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.TravelType;

import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TravelTypeRepository implements DAORepository<TravelType>{

    private static final Logger log = Logger.getLogger(TravelTypeRepository.class);

    public static TravelTypeRepository getInstance() {
        return TravelTypeRepository.TravelTypeRepositoryHolder.INSTANCE;
    }

    private static class TravelTypeRepositoryHolder {

        public static final TravelTypeRepository INSTANCE = new TravelTypeRepository();
    }

    @Override
    public void save(TravelType travelType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(travelType);
            log.info("Travel Type has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the travel Type : " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(TravelType travelType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(travelType);
            log.info("Travel Type has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Travel Type update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(TravelType travelType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(travelType);
            log.info("Travel Type has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete travel type: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<TravelType> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        TravelType travelType = null;
        try {
            travelType = session.get(TravelType.class, id);
            log.info("Select travel Type with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(travelType);
    }

    @Override
    public List<TravelType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<TravelType> travelTypes = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM TravelType t";
            travelTypes.addAll(session.createQuery(jpql, TravelType.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all travelTypes: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return travelTypes;
    }


}
