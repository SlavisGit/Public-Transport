package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class StationService {
    private final StationRepository stationRepository = StationRepository.getInstance();
    public static StationService getInstance() {
        return StationServiceHolder.INSTANCE;
    }

    private static class StationServiceHolder {
        public static final StationService INSTANCE = new StationService();
    }

    public ObservableList<Station> getAll(){
        List<Station> stations = stationRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(stations));
    }
    public Station getById(Long id) {
        return stationRepository.getById(id);
    }
}
