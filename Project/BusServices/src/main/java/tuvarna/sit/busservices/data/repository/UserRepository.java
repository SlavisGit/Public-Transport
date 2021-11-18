package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.User;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class UserRepository implements DAORepository<User>{
    private static final Logger log = Logger.getLogger(UserRepository.class);

    private static class UserRepositoryHolder {
        public static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return UserRepository.UserRepositoryHolder.INSTANCE;
    }

    @Override
    public void save(User user) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);
            log.info("User has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the user: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);
            log.info("User has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("User update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(user);
            log.info("User has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            user = session.load(User.class, id);
            log.info("Select user with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM User t";
            users.addAll(session.createQuery(jpql, User.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all users: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return users;
    }
    public User getByUsernameAndPassword(String username, String password) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;

        try {
            String jpql = " from User where username= :username and password= :password";
            Query query = session.createQuery(jpql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            List list = query.list();

            user = (User) list.get(0);

                transaction.commit();
            } catch (Exception exception) {
            log.info("Failed to select all users: 25654654 " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return user;
    }

}
