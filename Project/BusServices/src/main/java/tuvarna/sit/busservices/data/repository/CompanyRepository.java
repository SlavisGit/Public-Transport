package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import tuvarna.sit.busservices.data.entities.Company;

import java.util.List;
import java.util.Optional;

public class CompanyRepository implements DAORepository<Company>{

    private static final Logger log = Logger.getLogger(CompanyRepository.class);

    public static CompanyRepository getInstance() {
        return CompanyRepository.CompanyRepositoryHolder.INSTANCE;
    }

    private static class CompanyRepositoryHolder {

        public static final CompanyRepository INSTANCE = new CompanyRepository();
    }

    @Override
    public void save(Company object) {

    }

    @Override
    public void update(Company object) {

    }

    @Override
    public void delete(Company object) {

    }

    @Override
    public Optional<Company> getByIg(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Company> getAll() {
        return null;
    }


}
