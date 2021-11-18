package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.UserType;
import tuvarna.sit.busservices.data.repository.UserTypeRepository;
import tuvarna.sit.busservices.presentation.models.UserTypeListView;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserTypeService {
    private final UserTypeRepository userType = UserTypeRepository.getInstance();

    public static UserTypeService getInstance()
    {
        return UserTypeServiceHolder.INSTANCE;
    }

    private static class UserTypeServiceHolder {

        public static final UserTypeService INSTANCE = new UserTypeService();

    }

    public ObservableList<UserTypeListView> getAllUsers() {
        List<UserType> users = userType.getAll();
        return FXCollections.observableList(
                users.stream()
                        .map(m -> new UserTypeListView(m.getUserType()))
                        .collect(Collectors.toList()));

    }
}
