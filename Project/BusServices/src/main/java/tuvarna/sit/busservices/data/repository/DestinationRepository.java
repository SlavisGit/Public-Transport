package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Destination;

import java.util.List;
import java.util.Optional;

public class DestinationRepository implements DAORepository<Destination>{

    private static final Logger log = Logger.getLogger(DestinationRepository.class);

    public static DestinationRepository getInstance() {
        return DestinationRepository.DestinationRepositoryHolder.INSTANCE;
    }

    private static class DestinationRepositoryHolder {

        public static final DestinationRepository INSTANCE = new DestinationRepository();
    }

    @Override
    public void save(Destination object) {

    }

    @Override
    public void update(Destination object) {

    }

    @Override
    public void delete(Destination object) {

    }

    @Override
    public Optional<Destination> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Destination> getAll() {
        return null;
    }

}
