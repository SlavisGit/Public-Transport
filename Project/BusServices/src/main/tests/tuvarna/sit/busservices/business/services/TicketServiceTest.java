package tuvarna.sit.busservices.business.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tuvarna.sit.busservices.data.entities.Destination;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {
    private static TicketService ticketService;
    @BeforeEach
    void setUp() {
        ticketService = TicketService.getInstance();
    }

    @Test
    void getFromTravel() {
        Assertions.assertEquals(1, ticketService.getFromTravel(1L).size());
    }

    @Test
    void getWhereDestination() {
        Destination destination = DestinationService.getInstance().getById(1L);
        Assertions.assertEquals(2, ticketService.getWhereDestination(destination).size());
    }
}