package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Transport;

import java.util.List;
import java.util.Optional;

public class TransportRepository implements DAORepository<Transport>{

    private static final Logger log = Logger.getLogger(TransportRepository.class);

    public static TransportRepository getInstance() {
        return TransportRepository.TransportRepositoryHolder.INSTANCE;
    }

    private static class TransportRepositoryHolder {

        public static final TransportRepository INSTANCE = new TransportRepository();
    }

    @Override
    public void save(Transport object) {

    }

    @Override
    public void update(Transport object) {

    }

    @Override
    public void delete(Transport object) {

    }

    @Override
    public Optional<Transport> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Transport> getAll() {
        return null;
    }
}
