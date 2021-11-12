package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class TravelRepository implements DAORepository<TravelRepository>{
    private static final Logger logger = Logger.getLogger(TravelRepository.class);

    public static TravelRepository getInstance() {
        return TravelRepository.TravelHolder.INSTANCE;
    }

    private static class TravelHolder {

        public static final TravelRepository INSTANCE = new TravelRepository();
    }

    @Override
    public void save(TravelRepository object) {

    }

    @Override
    public void update(TravelRepository object) {

    }

    @Override
    public void delete(TravelRepository object) {

    }

    @Override
    public Optional<TravelRepository> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TravelRepository> getAll() {
        return null;
    }


}
