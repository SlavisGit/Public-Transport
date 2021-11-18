package tuvarna.sit.busservices.business.services;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tuvarna.sit.busservices.data.entities.User;
import tuvarna.sit.busservices.data.repository.UserRepository;


public class UserService {
    private final UserRepository user = UserRepository.getInstance();

    public static UserService getInstance() {
        return UserServiceHolder.INSTANCE;
    }

    private static class UserServiceHolder {
        public static final UserService INSTANCE = new UserService();
    }
    public User getUserLogin(PasswordField password, TextField username) {
        String password1 = password.getText();
        String userName = username.getText();
        User byUsernameAndPassword = user.getByUsernameAndPassword(userName, password1);
        return byUsernameAndPassword;
    }
}
