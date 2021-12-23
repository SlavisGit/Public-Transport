package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tuvarna.sit.busservices.data.entities.Status;
import tuvarna.sit.busservices.data.repository.StatusRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StatusService {

    private final StatusRepository statusRepository = StatusRepository.getInstance();
    public static StatusService getInstance() {
        return StatusService.StatusServiceHolder.INSTANCE;
    }

    private static class StatusServiceHolder {
        public static final StatusService INSTANCE = new StatusService();
    }

   public Status getById(Long id) {
       return statusRepository.getById(id).get();
   }
}
