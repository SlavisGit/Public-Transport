package tuvarna.sit.busservices.presentation.models;

import tuvarna.sit.busservices.data.entities.Company;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.entities.Status;
import tuvarna.sit.busservices.data.entities.Ticket;

public class OrderTicketsView {
    private Long id;
    private Station station;
    private Ticket ticket;
    private Company company;
    private Status status;

    public OrderTicketsView(Long id, Station station, Ticket ticket, Company company, Status status) {
        this.id = id;
        this.station = station;
        this.ticket = ticket;
        this.company = company;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Company getCompany() {
        return company;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s", station , ticket , company ,status );
    }
}
