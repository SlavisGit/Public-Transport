package tuvarna.sit.busservices.presentation.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tuvarna.sit.busservices.business.services.TicketService;
import tuvarna.sit.busservices.presentation.models.TicketListView;
import tuvarna.sit.busservices.presentation.models.TravelListView;

import java.net.URL;
import java.util.ResourceBundle;

public class TicketViewController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private TableColumn<TicketListView, String> cashierColumn;

    @FXML
    private TableColumn<TicketListView, String> clientColumn;

    @FXML
    private TableColumn<TicketListView, String> priceColumn;

    @FXML
    private TableColumn<TicketListView, String> statusColumn;

    @FXML
    private TableView<TicketListView> table;

    @FXML
    private TableColumn<TicketListView, String> travelColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        display(ticketService.getAll());
    }

    private void display(ObservableList<TicketListView> all) {
        travelColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTravel()));
        cashierColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getCashier()));
        priceColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getPrice()));
        clientColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getClient()));
        statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
        table.setItems(all);
    }

    TicketService ticketService = TicketService.getInstance();

    public  void findTicket(TravelListView travelListView) {
        display(ticketService.getFromTravel(travelListView.getId()));
    }
}
