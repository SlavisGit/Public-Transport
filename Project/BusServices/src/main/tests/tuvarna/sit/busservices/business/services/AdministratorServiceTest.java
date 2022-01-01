package tuvarna.sit.busservices.business.services;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tuvarna.sit.busservices.data.entities.Administrator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorServiceTest {
    private static AdministratorService administratorService;
    @BeforeEach
    void setUp() {
        administratorService = AdministratorService.getInstance();
    }

    @Test
    void save() {
        Administrator administrator1 = new Administrator();
        administrator1.setID(1L);
        administrator1.setFirstName("Ivan");
        administrator1.setLastName("Petkov");
        Administrator administrator2 = new Administrator();
        administrator2.setID(2L);
        administrator2.setFirstName("Dima");
        administrator2.setLastName("Kirov");
        Administrator administrator3 = new Administrator();
        administrator3.setID(3L);
        administrator3.setFirstName("petkan");
        administrator3.setLastName("petkanov");
        Administrator administrator4 = new Administrator();
        administrator4.setID(4L);
        administrator4.setFirstName("helga");
        administrator4.setLastName("helkev");
        List<Administrator> administrators = new ArrayList<>();
        administrators.add(administrator2);
        administrators.add(administrator1);
        administrators.add(administrator3);
        administrators.add(administrator4);
        administratorService.save(administrator4);
        assertEquals(4, administratorService.getAll().size());

    }

    @Test
    void update() {
        BasicConfigurator.configure();
        Administrator administrator1 = new Administrator();
        administrator1.setID(1L);
        administrator1.setFirstName("Ivan");
        administrator1.setLastName("Petkon");
        administratorService.update(administrator1);
        Administrator adm = administratorService.getById(1L);

        Assertions.assertEquals(administrator1.getFirstName(), adm.getFirstName());
        Assertions.assertEquals(administrator1.getID(), adm.getID());
        Assertions.assertEquals(administrator1.getLastName(), adm.getLastName());
    }

    @Test
    void delete() {
        BasicConfigurator.configure();
        Administrator administrator1 = new Administrator();
        administrator1.setID(1L);
        administrator1.setFirstName("Ivan");
        administrator1.setLastName("Petkov");
        Administrator administrator2 = new Administrator();
        administrator2.setID(2L);
        administrator2.setFirstName("Dima");
        administrator2.setLastName("Kirov");
        Administrator administrator3 = new Administrator();
        administrator3.setID(3L);
        administrator3.setFirstName("petkan");
        administrator3.setLastName("petkanov");
        Administrator administrator4 = new Administrator();
        administrator4.setID(6L);
        administrator4.setFirstName("helga");
        administrator4.setLastName("helkev");
        List<Administrator> administrators = new ArrayList<>();
        administrators.add(administrator2);
        administrators.add(administrator1);
        administrators.add(administrator3);
        administratorService.delete(administrator4);
        assertEquals(3, administratorService.getAll().size());
    }

    @Test
    void getById() {
        BasicConfigurator.configure();
        Administrator administrator1 = new Administrator();
        administrator1.setID(1L);
        administrator1.setFirstName("Ivan");
        administrator1.setLastName("Petkon");
        Administrator byId = administratorService.getById(1L);

        Assertions.assertEquals(administrator1.getFirstName(), byId.getFirstName());
        Assertions.assertEquals(administrator1.getID(), byId.getID());
        Assertions.assertEquals(administrator1.getLastName(), byId.getLastName());
    }

    @Test
    void getAll() {
        BasicConfigurator.configure();
        Administrator administrator1 = new Administrator();
        administrator1.setID(1L);
        administrator1.setFirstName("Ivan");
        administrator1.setLastName("Petkov");
        Administrator administrator2 = new Administrator();
        administrator2.setID(2L);
        administrator2.setFirstName("Dima");
        administrator2.setLastName("Kirov");
        Administrator administrator3 = new Administrator();
        administrator3.setID(3L);
        administrator3.setFirstName("petkan");
        administrator3.setLastName("petkanov");
        List<Administrator> administrators = new ArrayList<>();
        administrators.add(administrator2);
        administrators.add(administrator1);
        administrators.add(administrator3);
        assertEquals(3, administratorService.getAll().size());
    }
}