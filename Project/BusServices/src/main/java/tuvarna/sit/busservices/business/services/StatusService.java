package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Status;
import tuvarna.sit.busservices.data.repository.StatusRepository;

import java.util.ArrayList;
import java.util.List;

public class StatusService implements Service<Status>{

    private final StatusRepository statusRepository = StatusRepository.getInstance();
    public static StatusService getInstance() {
        return StatusService.StatusServiceHolder.INSTANCE;
    }

    private static class StatusServiceHolder {
        public static final StatusService INSTANCE = new StatusService();
    }

    @Override
    public void save(Status object) {
        statusRepository.save(object);
    }

    @Override
    public void update(Status object) {
        statusRepository.update(object);
    }

    @Override
    public void delete(Status object) {
        statusRepository.delete(object);
    }

    public Status getById(Long id) {
       return statusRepository.getById(id);
   }

    @Override
    public ObservableList<Status> getAll() {
        List<Status> users = statusRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(users));
    }
}
