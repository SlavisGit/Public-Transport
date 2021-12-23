package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Administrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdministratorRepository implements DAORepository<Administrator>{
    private static final Logger log = Logger.getLogger(AdministratorRepository.class);

    public static AdministratorRepository getInstance() {
        return AdministratorRepository.AdministratorRepositoryHolder.INSTANCE;
    }

    private static class AdministratorRepositoryHolder {
        public static final AdministratorRepository INSTANCE = new AdministratorRepository();
    }

    @Override
    public void save(Administrator administrator) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(administrator);
            log.info("Administrator has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the administrator: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Administrator administrator) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(administrator);
            log.info("Administrator has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Admin update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Administrator administrator) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(administrator);
            log.info("Administrator has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete admin: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Administrator> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Administrator administrator = null;
        try {
            administrator = session.get(Administrator.class, id);
            log.info("Select administrator with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        }
        finally {
            session.close();
        }
        return Optional.ofNullable(administrator);
    }

    @Override
    public List<Administrator> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Administrator> administrators = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Administrator t";
            administrators.addAll(session.createQuery(jpql, Administrator.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all administrators: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return administrators;
    }

}
