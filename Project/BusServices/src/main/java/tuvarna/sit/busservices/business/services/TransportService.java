package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import tuvarna.sit.busservices.data.entities.Transport;
import tuvarna.sit.busservices.data.repository.TransportRepository;

import java.util.ArrayList;
import java.util.List;

public class TransportService implements Service<Transport >{
    private final TransportRepository transportRepository = TransportRepository.getInstance();
    public static TransportService getInstance() {
        return TransportService.TransportServiceHolder.INSTANCE;
    }

    private static class TransportServiceHolder {
        public static final TransportService INSTANCE = new TransportService();
    }
    @Override
    public void save(Transport object) {
        transportRepository.save(object);
    }

    @Override
    public void update(Transport object) {
        transportRepository.update(object);
    }

    @Override
    public void delete(Transport object) {
        transportRepository.delete(object);
    }

    @Override
    public Transport getById(Long id) {
        return transportRepository.getById(id);
    }

    @Override
    public List<Transport> getAll() {
        List<Transport> transports = transportRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(transports));
    }
}
