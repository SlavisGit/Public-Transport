package tuvarna.sit.busservices.business.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.User;

import static org.junit.jupiter.api.Assertions.*;

class TravelServiceTest {
    private static TravelService travelService;
    @BeforeEach
    void setUp() {
        travelService = TravelService.getInstance();
    }

    @Test
    void getAllTravelForCompany() {
        User user = CompanyService.getInstance().getById(1L).getUser();
        HelloApplication.setUser(user);
        Assertions.assertEquals(3, travelService.getAllTravelForCompany().size());
    }

    @Test
    void getAllTravelForCompanyWhereDataEnd() {
        User user = CompanyService.getInstance().getById(1L).getUser();
        HelloApplication.setUser(user);
        Assertions.assertEquals(0, travelService.getAllTravelForCompanyWhereDataEnd().size());
    }

    @Test
    void getAllTravelForStationWhereDataEnd() {
        User user = StationService.getInstance().getById(1L).getUser();
        HelloApplication.setUser(user);
        Assertions.assertEquals(0, travelService.getAllTravelForStationWhereDataEnd().size());
    }

    @Test
    void getAllTravelForCashier() {
        User user = CashierService.getInstance().getById(2L).getUser();
        HelloApplication.setUser(user);
        Assertions.assertEquals(2, travelService.getAllTravelForCashier().size());
    }

    @Test
    void getAllTravelForStation() {
        User user = StationService.getInstance().getById(1L).getUser();
        HelloApplication.setUser(user);
        Assertions.assertEquals(1, travelService.getAllTravelForStation().size());
    }
}