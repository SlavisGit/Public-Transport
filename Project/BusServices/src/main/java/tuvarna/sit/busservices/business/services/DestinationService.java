package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import tuvarna.sit.busservices.data.entities.Destination;
import tuvarna.sit.busservices.data.repository.DestinationRepository;

import java.util.ArrayList;
import java.util.List;

public class DestinationService implements Service<Destination>{
    private final DestinationRepository destinationRepository = DestinationRepository.getInstance();
    public static DestinationService getInstance() {
        return DestinationService.DestinationServiceHolder.INSTANCE;
    }

    private static class DestinationServiceHolder {
        public static final DestinationService INSTANCE = new DestinationService();
    }
    @Override
    public void save(Destination object) {
        destinationRepository.save(object);
    }

    @Override
    public void update(Destination object) {
        destinationRepository.update(object);
    }

    @Override
    public void delete(Destination object) {
        destinationRepository.delete(object);
    }

    @Override
    public Destination getById(Long id) {
        return destinationRepository.getById(id);
    }

    @Override
    public List<Destination> getAll() {
        List<Destination> destinations = destinationRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(destinations));
    }
}
