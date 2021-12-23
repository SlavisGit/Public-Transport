package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Table(name = "ORDER_TICKETS")
@Entity
public class OrderTickets implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "ticketId", nullable = false)
    private Ticket ticket;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    private Status status;

    @Override
    public String toString() {
        return "OrderTickets{" +
                "station=" + station +
                ", ticket=" + ticket +
                ", company=" + company +
                '}';
    }

    public OrderTickets() {
    }

    public OrderTickets(Station station, Ticket ticket, Company company, Status status) {
        this.station = station;
        this.ticket = ticket;
        this.company = company;
        this.status = status;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
