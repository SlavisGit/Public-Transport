package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Administrator;

import java.util.List;
import java.util.Optional;

public class AdministratorRepository implements DAORepository<Administrator>{
    private static final Logger log = Logger.getLogger(AdministratorRepository.class);
    
    public static AdministratorRepository getInstance() {
        return AdministratorRepository.AdministratorRepositoryHolder.INSTANCE;
    }

    private static class AdministratorRepositoryHolder {

        public static final AdministratorRepository INSTANCE = new AdministratorRepository();
    }

    @Override
    public void save(Administrator object) {

    }

    @Override
    public void update(Administrator object) {

    }

    @Override
    public void delete(Administrator object) {

    }

    @Override
    public Optional<Administrator> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Administrator> getAll() {
        return null;
    }


}
