package tuvarna.sit.busservices.business.services;
import javafx.collections.FXCollections;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;


public class UserService implements Service<User>{
    private final UserRepository user = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }

    @Override
    public void save(User object) {
        user.save(object);
    }

    @Override
    public void update(User object) {
        user.update(object);
    }

    @Override
    public void delete(User object) {
        user.delete(object);
    }

    @Override
    public User getById(Long id) {
        return user.getById(id);
    }

    @Override
    public List<User> getAll() {
        List<User> users = user.getAll();
        return FXCollections.observableList(
                new ArrayList<>(users));
    }

    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }
    public User getUserLogin(PasswordField password, TextField username, String type) {
        String password1 = password.getText();
        String userName = username.getText();
        return user.getByUsernameAndPassword(userName, password1, type);
    }
    public User getByIdStation(Long id) {
        return user.getByIdStation(id);
    }
    public User getByIdCashier(Long id) {
        return user.getByIdCashier(id);
    }
    public User getByIdCompany(Long id) {
        return user.getByIdCompany(id);
    }
}
