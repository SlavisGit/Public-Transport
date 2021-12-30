package tuvarna.sit.busservices.business.services;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.entities.UserType;
import tuvarna.sit.busservices.data.repository.UserRepository;


public class UserService {
    private final UserRepository user = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
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
