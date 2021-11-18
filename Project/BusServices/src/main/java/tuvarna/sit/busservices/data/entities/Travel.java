package tuvarna.sit.busservices.data.entities;

import javax.persistence.*;

import java.io.Serial;
import java.io.Serializable;
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
    private Date dataTo;

    @Column(name = "dataFrom", nullable = false)
    private Date dataFrom;

    @Column(name = "countPlaces", nullable = false)
    private int countPlaces;

    @Column(name = "limitation", nullable = false)
    private int limitation;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "stationId", nullable = false)
    private Station station;

    @OneToMany(mappedBy = "travel")
    private Set<Ticket> ticketSet;

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

    public Date getDataTo() {
        return dataTo;
    }

    public void setDataTo(Date dataTo) {
        this.dataTo = dataTo;
    }

    public Date getDataFrom() {
        return dataFrom;
    }

    public void setDataFrom(Date dataFrom) {
        this.dataFrom = dataFrom;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "ID=" + ID +
                ", travelType=" + travelType +
                ", destination=" + destination +
                ", transportType=" + transportType +
                ", dataTo=" + dataTo +
                ", dataFrom=" + dataFrom +
                ", countPlaces=" + countPlaces +
                ", limitation=" + limitation +
                ", company=" + company +
                ", station=" + station +
                '}';
    }
}
