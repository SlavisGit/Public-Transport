package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Administrator;
import tuvarna.sit.busservices.data.entities.TravelType;
import tuvarna.sit.busservices.data.repository.CashierRepository;
import tuvarna.sit.busservices.data.repository.TravelTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class TravelTypeService implements Service<TravelType>{
    private final TravelTypeRepository travelTypeRepository = TravelTypeRepository.getInstance();
    public static TravelTypeService getInstance() {
        return TravelTypeService.TravelTypeServiceHolder.INSTANCE;
    }

    private static class TravelTypeServiceHolder {
        public static final TravelTypeService INSTANCE = new TravelTypeService();
    }
    @Override
    public void save(TravelType object) {
        travelTypeRepository.save(object);
    }

    @Override
    public void update(TravelType object) {
        travelTypeRepository.update(object);
    }

    @Override
    public void delete(TravelType object) {
        travelTypeRepository.delete(object);
    }

    @Override
    public TravelType getById(Long id) {
        return  travelTypeRepository.getById(id);
    }

    @Override
    public List<TravelType> getAll() {
        List<TravelType> users = travelTypeRepository.getAll();
        return (ObservableList<TravelType>) FXCollections.observableList(
                new ArrayList<>(users));

    }
}
