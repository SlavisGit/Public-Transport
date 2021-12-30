package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.data.repository.TravelRepository;
import tuvarna.sit.busservices.presentation.models.TravelListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TravelService implements Service<Travel>{
    private final TravelRepository travelRepository = TravelRepository.getInstance();
    public static TravelService getInstance () {return TravelServiceHolder.INSTANCE;}


    private static class TravelServiceHolder {
        public static final TravelService INSTANCE = new TravelService();
    }

    public ObservableList<Travel> getAllTravelForCompany() {
        List<Travel> travels = travelRepository.getAllForCompany();
        return FXCollections.observableList(new ArrayList<>(travels));
    }

    public ObservableList<Travel> getAllTravelForCompanyWhereDataEnd() {
        List<Travel> travels = travelRepository.getAllTravelForCompanyWhereDataEnd();
        return FXCollections.observableList(new ArrayList<>(travels));
    }
    public ObservableList<Travel> getAllTravelForStationWhereDataEnd() {
        List<Travel> travels = travelRepository.getAllTravelForStationWhereDataEnd();
        return FXCollections.observableList(new ArrayList<>(travels));
    }

    public ObservableList<Travel> getAllTravelForCashier() {
        List<Travel> travels = travelRepository.getAllForCashier();
        return FXCollections.observableList(new ArrayList<>(travels));
    }

    public ObservableList<Travel> getAllTravelForStation() {
        List<Travel> travels = travelRepository.getAllForStation();
        return FXCollections.observableList(new ArrayList<>(travels));
    }

    @Override
    public void save(Travel object) {
        travelRepository.save(object);
    }

    @Override
    public void update(Travel object) {
        travelRepository.update(object);
    }

    public void delete(Travel travel) {
        travelRepository.delete(travel);
    }
    public Travel getById(Long id) {
       return travelRepository.getById(id);
    }

    @Override
    public List<Travel> getAll() {
        List<Travel> travels = travelRepository.getAll();
        return FXCollections.observableList(new ArrayList<>(travels));
    }
}
