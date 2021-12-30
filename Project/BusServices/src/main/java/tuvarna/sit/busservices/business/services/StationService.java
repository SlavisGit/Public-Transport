package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

public class StationService implements Service<Station>{
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

    @Override
    public void save(Station object) {
        stationRepository.save(object);
    }

    @Override
    public void update(Station object) {
        stationRepository.update(object);
    }

    @Override
    public void delete(Station object) {
        stationRepository.delete(object);
    }

    public Station getById(Long id) {
        return stationRepository.getById(id);
    }
}
