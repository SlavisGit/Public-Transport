package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.UserType;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;
import tuvarna.sit.busservices.presentation.models.UserTypeListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserTypeService implements Service<UserType>{
    private final UserTypeRepository userType = UserTypeRepository.getInstance();

    public static UserTypeService getInstance()
    {
        return UserTypeServiceHolder.INSTANCE;
    }

    private static class UserTypeServiceHolder {
        public static final UserTypeService INSTANCE = new UserTypeService();
    }

    @Override
    public void save(UserType object) {
        userType.save(object);
    }

    @Override
    public void update(UserType object) {
        userType.update(object);
    }

    @Override
    public void delete(UserType object) {
        userType.delete(object);
    }

    @Override
    public UserType getById(Long id) {
        return userType.getById(id);
    }

    @Override
    public ObservableList<UserType> getAll() {
        List<UserType> users = userType.getAll();
        return FXCollections.observableList(
                new ArrayList<>(users));

    }
}
