package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Status;

import java.util.List;
import java.util.Optional;

public class StatusRepository implements DAORepository<Status>{

    private static final Logger log = Logger.getLogger(StatusRepository.class);

    public static StatusRepository getInstance() {
        return StatusRepository.StatusRepositoryHolder.INSTANCE;
    }

    private static class StatusRepositoryHolder {

        public static final StatusRepository INSTANCE = new StatusRepository();
    }

    @Override
    public void save(Status object) {

    }

    @Override
    public void update(Status object) {

    }

    @Override
    public void delete(Status object) {

    }

    @Override
    public Optional<Status> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Status> getAll() {
        return null;
    }


}
