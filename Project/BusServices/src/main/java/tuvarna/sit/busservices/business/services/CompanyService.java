package tuvarna.sit.busservices.business.services;

import javafx.collections.FXCollections;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.repository.CompanyRepository;

import java.util.ArrayList;
import java.util.List;

public class CompanyService implements Service<Company>{

    private final CompanyRepository companyRepository = CompanyRepository.getInstance();
    public static CompanyService getInstance() {
        return CompanyService.CompanyServiceHolder.INSTANCE;
    }

    private static class CompanyServiceHolder {
        public static final CompanyService INSTANCE = new CompanyService();
    }

    @Override
    public void save(Company object) {
        companyRepository.save(object);
    }

    @Override
    public void update(Company object) {
        companyRepository.update(object);
    }

    @Override
    public void delete(Company object) {
        companyRepository.delete(object);
    }

    @Override
    public Company getById(Long id) {
        return  companyRepository.getById(id);
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = companyRepository.getAll();
        return FXCollections.observableList(
                new ArrayList<>(companies));
    }
}
