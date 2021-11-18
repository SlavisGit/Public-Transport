package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.busservices.data.entities.Destination;
import tuvarna.sit.busservices.data.entities.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StationRepository implements DAORepository<Station>{

    private static final Logger log = Logger.getLogger(StationRepository.class);

    public static StationRepository getInstance() {
        return StationRepository.StationRepositoryHolder.INSTANCE;
    }

    private static class StationRepositoryHolder {

        public static final StationRepository INSTANCE = new StationRepository();
    }

    @Override
    public void save(Station station) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(station);
            log.info("Station has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the station: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Station station) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(station);
            log.info("Station has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Station update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Station station) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(station);
            log.info("Station has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete station: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Station> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Station station = null;
        try {
            station = session.get(Station.class, id);
            log.info("Select station with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(station);
    }

    @Override
    public List<Station> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Station> stations = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Station t";
            stations.addAll(session.createQuery(jpql, Station.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all stations: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return stations;
    }

    public void saveCashier(Cashier cashier) {
        CashierRepository cashierRepository = CashierRepository.getInstance();
        cashierRepository.save(cashier);
    }

    public void saveDestination(Destination destination) {
        DestinationRepository destinationRepository = DestinationRepository.getInstance();
        destinationRepository.save(destination);
    }
}
