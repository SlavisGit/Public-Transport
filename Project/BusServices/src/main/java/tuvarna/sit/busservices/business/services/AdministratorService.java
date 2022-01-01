package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.repository.AdministratorRepository;
import tuvarna.sit.busservices.data.repository.CompanyRepository;
import tuvarna.sit.busservices.data.repository.StationRepository;
import tuvarna.sit.busservices.presentation.models.AdministratorListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdministratorService implements Service<Administrator>{
    private final AdministratorRepository administrator = AdministratorRepository.getInstance();

    public static AdministratorService getInstance() {
        return AdministratorServiceHolder.INSTANCE;
    }

    @Override
    public void save(Administrator object) {
        administrator.save(object);
    }

    @Override
    public void update(Administrator object) {
        administrator.update(object);
    }

    @Override
    public void delete(Administrator object) {
        administrator.delete(object);
    }

    @Override
    public Administrator getById(Long id) {
        return administrator.getById(id);
    }

    private static class AdministratorServiceHolder {
        public static final AdministratorService INSTANCE = new AdministratorService();
    }

    @Override
    public ObservableList<Administrator> getAll() {
        List<Administrator> users = administrator.getAll();
        return (ObservableList<Administrator>) FXCollections.observableList(
                new ArrayList<>(users));

    }

}
