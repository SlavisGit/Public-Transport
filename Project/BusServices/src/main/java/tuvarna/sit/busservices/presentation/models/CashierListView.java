package tuvarna.sit.busservices.presentation.models;

import tuvarna.sit.busservices.data.entities.Station;

public class CashierListView {
    private String firstName;
    private String lastName;
    private String ucn;
    private Double honorarium;
    private Station station;
    private int countTicket;

    public CashierListView(String firstName, String lastName, String ucn, Double honorarium, Station station, int countTicket) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ucn = ucn;
        this.honorarium = honorarium;
        this.station = station;
        this.countTicket = countTicket;
    }

    public int getCountTicket() {
        return countTicket;
    }

    public void setCountTicket(int countTicket) {
        this.countTicket = countTicket;
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
