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

import java.util.List;
import java.util.stream.Collectors;

public class AdministratorService {
    private final AdministratorRepository administrator = AdministratorRepository.getInstance();

    public static AdministratorService getInstance() {
        return AdministratorServiceHolder.INSTANCE;
    }

    private static class AdministratorServiceHolder {
        public static final AdministratorService INSTANCE = new AdministratorService();
    }

    public ObservableList<AdministratorListView> getAllUsers() {
        List<Administrator> users = administrator.getAll();
        return FXCollections.observableList(
                users.stream()
                        .map(m -> new AdministratorListView(m.getFirstName(), m.getLastName()))
                        .collect(Collectors.toList()));

    }

    public void addCompany(Company company) {
        CompanyRepository companyRepository = CompanyRepository.getInstance();
        companyRepository.save(company);
    }

    public void addStation(Station station) {
        StationRepository stationRepository = StationRepository.getInstance();
        stationRepository.save(station);
    }
}
