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
    public void save(UserType object) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(object);
            log.info("User has been saved");
        } catch (Exception e) {
            log.info("User type save error: " + e.getMessage());
        } finally {
            transaction.commit();
        }

    }

    @Override
    public void update(UserType object) {

    }

    @Override
    public void delete(UserType object) {

    }

    @Override
    public Optional<UserType> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserType> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<UserType> userTypes = new ArrayList<>();
        try{
            String jpql = "SELECT t FROM UserType t";
            userTypes.addAll(session.createQuery(jpql, UserType.class).getResultList());

        } catch (Exception e) {
            log.info("User type getting all error: " + e.getMessage());
        } finally {
            transaction.commit();
        }
        return userTypes;
    }


}
