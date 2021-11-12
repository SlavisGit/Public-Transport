package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Station;

import java.util.List;
import java.util.Optional;

public class StationRepository implements DAORepository<StationRepository>{

    private static final Logger log = Logger.getLogger(StationRepository.class);

    public static StationRepository getInstance() {
        return StationRepository.StationRepositoryHolder.INSTANCE;
    }

    private static class StationRepositoryHolder {

        public static final StationRepository INSTANCE = new StationRepository();
    }

    @Override
    public void save(StationRepository object) {

    }

    @Override
    public void update(StationRepository object) {

    }

    @Override
    public void delete(StationRepository object) {

    }

    @Override
    public Optional<StationRepository> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StationRepository> getAll() {
        return null;
    }


}
