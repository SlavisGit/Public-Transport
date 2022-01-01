package tuvarna.sit.busservices.business.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTicketsServiceTest {

    private static OrderTicketsService orderTicketsService;
    @BeforeEach
    void setUp() {
        orderTicketsService = OrderTicketsService.getInstance();
    }

    @Test
    void getAllFromStation() {
        Assertions.assertEquals(4, orderTicketsService.getAllFromStation(1L).size());
    }

    @Test
    void getAllFromCompany() {
        Assertions.assertEquals(4, orderTicketsService.getAllFromCompany(1L).size());
    }
}