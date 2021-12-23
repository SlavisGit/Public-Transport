package tuvarna.sit.busservices.presentation.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tuvarna.sit.busservices.business.services.OrderTicketsService;
import tuvarna.sit.busservices.business.services.StatusService;
import tuvarna.sit.busservices.data.entities.Status;
import tuvarna.sit.busservices.presentation.models.OrderTicketsView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderTicketsController implements Initializable {

    @FXML
    private TableColumn<OrderTicketsView, String> companyColumn;

    @FXML
    private Button confirm;

    @FXML
    private TableColumn<OrderTicketsView, Status> statusColumn;

    @FXML
    private TableView<OrderTicketsView> tableView;

    @FXML
    private TableColumn<OrderTicketsView, String> ticketColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OrderTicketsService orderTicketsService = OrderTicketsService.getInstance();
        ObservableList<OrderTicketsView> all = orderTicketsService.getAll();
        display(all);
    }
    public void display(ObservableList<OrderTicketsView> all) {

        companyColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getCompany()));
        ticketColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTicket()));
        statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
        statusColumn.setCellFactory(param -> new TableCell<>() {
            @Override
            protected void updateItem(Status item, boolean empty) {
                StatusService statusService = StatusService.getInstance();
                ObservableList<Status> allForOrder = statusService.getAllForOrder();
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
                    ComboBox<Status> cb = new ComboBox<>(allForOrder);
                  //  cb.getSelectionModel().select("not seen");
                }
            }
        });
        tableView.setItems(all);
    }
}
