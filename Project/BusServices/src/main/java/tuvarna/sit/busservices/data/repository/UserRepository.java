package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.entities.UserType;

import java.util.ArrayList;
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
    public void save(User object) {

    }

    @Override
    public void update(User object) {

    }

    @Override
    public void delete(User object) {

    }

    @Override
    public Optional<User> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }


}
