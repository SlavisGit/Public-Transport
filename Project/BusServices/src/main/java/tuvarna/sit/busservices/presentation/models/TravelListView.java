package tuvarna.sit.busservices.presentation.models;

import tuvarna.sit.busservices.data.entities.*;

import java.time.LocalDate;

public class TravelListView {
    private Long id;
    private TravelType travelType;
    private Destination destination;
    private Transport transportType;
    private LocalDate dataTo;
    private LocalDate dataFrom;
    private int countPlaces;
    private int limitation;
    private Station station;
    private Company company;

    public TravelListView(Long id, TravelType travelType, Destination destination, Transport transportType,
                          LocalDate dataTo, LocalDate dataFrom, int countPlaces, int limitation,
                          Station station, Company company) {
        this.id = id;
        this.travelType = travelType;
        this.destination = destination;
        this.transportType = transportType;
        this.dataTo = dataTo;
        this.dataFrom = dataFrom;
        this.countPlaces = countPlaces;
        this.limitation = limitation;
        this.station = station;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s | %d | %d | %s | %s", travelType.toString(), destination.toString(),
                transportType.toString(), dataTo.toString(), dataFrom.toString(), countPlaces, limitation, station.toString(), company.toString());
    }
}
