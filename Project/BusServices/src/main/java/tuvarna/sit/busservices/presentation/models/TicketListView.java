package tuvarna.sit.busservices.presentation.models;

import tuvarna.sit.busservices.data.entities.*;

public class TicketListView {
    private Travel travel;
    private Cashier cashier;
    private Double price;
    private Status status;
    private Client client;


    public TicketListView(Travel travel, Cashier cashier, Double price, Status status, Client client) {
        this.travel = travel;
        this.cashier = cashier;
        this.price = price;
        this.status = status;
        this.client = client;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s", travel, cashier, price, status, client);

    }
}
