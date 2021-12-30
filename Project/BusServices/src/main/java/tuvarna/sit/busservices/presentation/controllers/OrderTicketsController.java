package tuvarna.sit.busservices.presentation.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.OrderTicketsService;
import tuvarna.sit.busservices.business.services.StatusService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.data.entities.*;
import tuvarna.sit.busservices.data.repository.NotificationRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderTicketsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    OrderTicketsService orderTicketsService = OrderTicketsService.getInstance();

    @FXML
    private TableColumn<OrderTickets, String> companyColumn;

    @FXML
    private Button confirm;

    @FXML
    private TableColumn<OrderTickets, String> statusColumn;

    @FXML
    private TableView<OrderTickets> tableView;

    @FXML
    private TableColumn<OrderTickets, String> ticketColumn;

    @FXML
    private Button back;

    @FXML
    private Button refusal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(HelloApplication.getUser().getStation() != null) {
            ObservableList<OrderTickets> all = orderTicketsService.getAllFromStation(HelloApplication.getUser().getStation().getID());
            display(all);
            confirm.setOnMouseClicked(this::confirmTicket);
            refusal.setOnMouseClicked(this::refusal);
            back.setOnMouseClicked(this::backStation);
        } else {
            ObservableList<OrderTickets> all = orderTicketsService.getAllFromCompany(HelloApplication.getUser().getCompany().getID());
            display(all);
            confirm.setOnMouseClicked(this::confirm);
            refusal.setOnMouseClicked(this::delete);
            back.setOnMouseClicked(this::backCompany);
        }
    }

    private void backCompany(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company");
    }

    private void backStation(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/stationOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station");
    }

    private void delete(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            orderTicketsService.delete(i);
        }
    }

    private void confirm(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            if (i.getStatus().getID().equals(3L)) {
                StatusService statusService = StatusService.getInstance();
                Status byId = statusService.getById(1L);
                i.getTicket().setStatus(byId);
                orderTicketsService.update(i);
            }

        }
        delete(mouseEvent);
    }

    private void refusal(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            StatusService statusService = StatusService.getInstance();
            Status byId = statusService.getById(4L);
            i.setStatus(byId);
            orderTicketsService.update(i);
        }
        UserService userService = UserService.getInstance();
        User user = userService.getByIdCompany(items.get(0).getCompany().getID());
        Notification notification = new Notification("Refusal tickets!", user);
        NotificationRepository notificationRepository = NotificationRepository.getInstance();
        notificationRepository.save(notification);
    }

    private void confirmTicket(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            StatusService statusService = StatusService.getInstance();
            Status byId = statusService.getById(3L);
            Status byId1 = statusService.getById(6L);
            Station station = HelloApplication.getUser().getStation();
            i.getTicket().setStation(station);
            i.getTicket().setStatus(byId1);
            i.setStatus(byId);
            orderTicketsService.update(i);
        }
        UserService userService = UserService.getInstance();
        User user = userService.getByIdCompany(items.get(0).getCompany().getID());
        Notification notification = new Notification("Confirm tickets!", user);
        NotificationRepository notificationRepository = NotificationRepository.getInstance();
        notificationRepository.save(notification);
    }

    public void display(ObservableList<OrderTickets> all) {
        if(!all.isEmpty()) {
            companyColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getCompany()));
            ticketColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTicket()));
            statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
            tableView.setItems(all);
        }
    }
}
