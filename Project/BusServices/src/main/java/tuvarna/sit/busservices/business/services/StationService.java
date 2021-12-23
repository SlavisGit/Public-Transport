package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.repository.StationRepository;
import tuvarna.sit.busservices.presentation.models.StationListView;

import java.util.List;
import java.util.stream.Collectors;

public class StationService {
    private final StationRepository stationRepository = StationRepository.getInstance();
    public static StationService getInstance() {
        return StationServiceHolder.INSTANCE;
    }

    private static class StationServiceHolder {
        public static final StationService INSTANCE = new StationService();
    }

    public ObservableList<StationListView> getAll(){
        List<Station> stations = stationRepository.getAll();
        return FXCollections.observableList(
                stations.stream().map(m -> new StationListView(m.getName(), m.getAddress(), m.getWorkTimeStart(), m.getWorkTimeEnd()))
                        .collect(Collectors.toList()));
    }
}
