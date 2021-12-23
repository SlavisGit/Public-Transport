package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.data.repository.TravelRepository;
import tuvarna.sit.busservices.presentation.models.TravelListView;

import java.util.List;
import java.util.stream.Collectors;

public class TravelService {
    private final TravelRepository travelRepository = TravelRepository.getInstance();
    public static TravelService getInstance () {return TravelServiceHolder.INSTANCE;}


    private static class TravelServiceHolder {
        public static final TravelService INSTANCE = new TravelService();
    }

    public ObservableList<TravelListView> getAllTravelForCompany() {
        List<Travel> travels = travelRepository.getAllForCompany();
        return FXCollections.observableList(travels.stream().map(m -> new TravelListView(m.getID(), m.getTravelType(), m.getDestination()
        , m.getTransportType(), m.getDataTo(), m.getDataFrom() , m.getCountPlaces(), m.getLimitation(), m.getStation(), m.getCompany())).collect(Collectors.toList()));
    }

    public ObservableList<TravelListView> getAllTravelForCashier() {
        List<Travel> travels = travelRepository.getAllForCashier();
        return FXCollections.observableList(travels.stream().map(m -> new TravelListView(m.getID(),m.getTravelType(), m.getDestination()
                , m.getTransportType(), m.getDataTo(), m.getDataFrom() , m.getCountPlaces(), m.getLimitation(), m.getStation(), m.getCompany())).collect(Collectors.toList()));
    }

    public ObservableList<TravelListView> getAllTravelForStation() {
        List<Travel> travels = travelRepository.getAllForStation();
        return FXCollections.observableList(travels.stream().map(m -> new TravelListView(m.getID(),m.getTravelType(), m.getDestination()
                , m.getTransportType(), m.getDataTo(), m.getDataFrom() , m.getCountPlaces(), m.getLimitation(), m.getStation(), m.getCompany())).collect(Collectors.toList()));
    }
}
