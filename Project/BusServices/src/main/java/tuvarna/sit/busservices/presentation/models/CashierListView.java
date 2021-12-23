package tuvarna.sit.busservices.presentation.models;

import tuvarna.sit.busservices.data.entities.Station;

public class CashierListView {
    private String firstName;
    private String lastName;
    private String ucn;
    private Double honorarium;
    private Station station;

    public CashierListView(String firstName, String lastName, String ucn, Double honorarium, Station station) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ucn = ucn;
        this.honorarium = honorarium;
        this.station = station;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUcn() {
        return ucn;
    }

    public Double getHonorarium() {
        return honorarium;
    }

    public Station getStation() {
        return station;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s", firstName, lastName, ucn, honorarium, station);

    }
}
