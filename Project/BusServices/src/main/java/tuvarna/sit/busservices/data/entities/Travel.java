package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Table(name = "TRAVEL")
@Entity
public class Travel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "travelTypeId", nullable = false)
    private TravelType travelType;

    @ManyToOne
    @JoinColumn(name = "destinationId", nullable = false)
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "transportTypeId", nullable = false)
    private Transport transportType;

    @Column(name = "dataTo", nullable = false)
    private LocalDate dataTo;

    @Column(name = "dataFrom", nullable = false)
    private LocalDate dataFrom;

    @Column(name = "countPlaces", nullable = false)
    private int countPlaces;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "travel", fetch = FetchType.EAGER)
    private Set<Ticket> ticketSet;

    public Set<Ticket> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Ticket> ticketSet) {
        this.ticketSet = ticketSet;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    public void setTravelType(TravelType travelType) {
        this.travelType = travelType;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Transport getTransportType() {
        return transportType;
    }

    public void setTransportType(Transport transportType) {
        this.transportType = transportType;
    }

    public LocalDate getDataTo() {
        return dataTo;
    }

    public void setDataTo(LocalDate dataTo) {
        this.dataTo = dataTo;
    }

    public LocalDate getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(LocalDate dataFrom) {
        this.dataFrom = dataFrom;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Travel(TravelType travelType, Destination destination, Transport transportType,
                  LocalDate dataTo, LocalDate dataFrom, int countPlaces, Company company) {
        this.travelType = travelType;
        this.destination = destination;
        this.transportType = transportType;
        this.dataTo = dataTo;
        this.dataFrom = dataFrom;
        this.countPlaces = countPlaces;
        this.company = company;
    }

    public Travel() {
    }

    @Override
    public String toString() {
        return " Travel to: " + destination ;
    }
}
