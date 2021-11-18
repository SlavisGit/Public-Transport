package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTypeRepository implements DAORepository<UserType>{

    private static final Logger log = Logger.getLogger(UserTypeRepository.class);

    public static UserTypeRepository getInstance()
    {
        return UserTypeRepository.UserTypeRepositoryHolder.INSTANCE;
    }

    private static class UserTypeRepositoryHolder {
        public static final UserTypeRepository INSTANCE = new UserTypeRepository();
    }

    @Override
    public void save(UserType userType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(userType);
            log.info("User Type has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the user Type: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void update(UserType userType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(userType);
            log.info("User Type has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("User Type update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(UserType userType) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(userType);
            log.info("User Type has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete user Type: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<UserType> getById(Long id) {

        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        UserType userType = null;
        try {
            userType = session.get(UserType.class, id);
            log.info("Select user Type with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(userType);
    }

    @Override
    public List<UserType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserType> userTypes = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM UserType t";
            userTypes.addAll(session.createQuery(jpql, UserType.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all userTypes: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return userTypes;
    }


}
