package tuvarna.sit.busservices.data.repository;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tuvarna.sit.busservices.data.access.Connection;
import tuvarna.sit.busservices.data.entities.Company;

import java.util.ArrayList;
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
    public void save(Company company) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(company);
            log.info("Company has been saved.");
            transaction.commit();

        } catch (Exception exception) {
            log.info("Failed to save the company: " + exception);
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Company company) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(company);
            log.info("Company has been updated.");
            transaction.commit();
        } catch (Exception e) {
            log.info("Company update failed: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Company company) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(company);
            log.info("Company has been deleted.");
            transaction.commit();
        }  catch (Exception e) {
            log.info("Failed to delete company: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<Company> getById(Long id) {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        Company company = null;
        try {
            company = session.get(Company.class, id);
            log.info("Select company with id: " + id);
            transaction.commit();
        } catch (Exception e) {
            log.info("Failed to select row: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
        return Optional.ofNullable(company);
    }

    @Override
    public List<Company> getAll() {
        Session session = Connection.openSession();
        Transaction transaction = session.beginTransaction();
        List<Company> companies = new ArrayList<>();

        try {
            String jpql = "SELECT t FROM Company t";
            companies.addAll(session.createQuery(jpql, Company.class).getResultList());
            transaction.commit();
        } catch (Exception exception) {
            log.info("Failed to select all companies: " + exception.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return companies;
    }


}
