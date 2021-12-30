package tuvarna.sit.busservices.presentation.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tuvarna.sit.busservices.business.services.TicketService;
import tuvarna.sit.busservices.data.entities.Ticket;
import tuvarna.sit.busservices.data.entities.Travel;
import tuvarna.sit.busservices.presentation.models.TicketListView;
import tuvarna.sit.busservices.presentation.models.TravelListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketViewController implements Initializable {

    private static TicketService ticketService = TicketService.getInstance();

    @FXML
    private TableColumn<Ticket, String> cashierColumn;

    @FXML
    private TableColumn<Ticket, String> clientColumn;

    @FXML
    private TableColumn<Ticket, String> priceColumn;

    @FXML
    private TableColumn<Ticket, String> statusColumn;

    @FXML
    private TableView<Ticket> table;

    @FXML
    private TableColumn<Ticket, String> travelColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        display(ticketService.getAll());
    }

    private void display(ObservableList<Ticket> all) {
        travelColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTravel()));
        cashierColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getCashier()));
        priceColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getPrice()));
        clientColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getClientWithTickets().getClient()));
        statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
        table.setItems(all);
    }

    public  void findTicket(Travel travel) {
        display(ticketService.getFromTravel(travel.getID()));
    }
}
