package tuvarna.sit.busservices.presentation.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.*;
import tuvarna.sit.busservices.data.entities.*;
import tuvarna.sit.common.Constants;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderTicketsController implements Initializable {

    private static TicketService ticketService = TicketService.getInstance();
    private static StatusService statusService = StatusService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static NotificationService notificationService = NotificationService.getInstance();

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
        newWindow(mouseEvent, Constants.View.WINDOW_COMPANY_OPTION, Constants.Titles.COMPANY);
    }

    private void backStation(MouseEvent mouseEvent) {
        newWindow(mouseEvent, Constants.View.WINDOW_STATION_OPTION, Constants.Titles.STATION);
    }

    private void newWindow(MouseEvent mouseEvent, String s, String station) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, station);
    }

    private void delete(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            if(i.getStatus().getStatus().equals(Constants.Status.DISAGREE)){
                ticketService.delete(i.getTicket());
            } else if(i.getStatus().getStatus().equals(Constants.Status.NOT_SEEN)){
                continue;
            }
            orderTicketsService.delete(i);
        }
        backCompany(mouseEvent);
    }

    private void confirm(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            if (i.getStatus().getID().equals(3L)) {
                Status byId = statusService.getById(1L);
                i.getTicket().setStatus(byId);
                ticketService.update(i.getTicket());
            }
        }
        delete(mouseEvent);
    }

    private void refusal(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {
            Status byId = statusService.getById(4L);
            i.setStatus(byId);
            orderTicketsService.update(i);
        }
        notification(items, Constants.Notification.REFUSAL_TICKETS);
        backStation(mouseEvent);
    }


    private void confirmTicket(MouseEvent mouseEvent) {
        ObservableList<OrderTickets> items = tableView.getItems();
        for (OrderTickets i : items) {

            Status byId = statusService.getById(3L);
            Status byId1 = statusService.getById(6L);
            Station station = HelloApplication.getUser().getStation();
            i.getTicket().setStation(station);
            i.getTicket().setStatus(byId1);
            i.setStatus(byId);
            orderTicketsService.update(i);
            ticketService.update(i.getTicket());
        }
        notification(items, Constants.Notification.CONFIRM_TICKETS);
        backStation(mouseEvent);
    }

    private void notification(ObservableList<OrderTickets> items, String s) {
        User user = userService.getByIdCompany(items.get(0).getCompany().getID());
        Notification notification = new Notification(s, user);
        notificationService.save(notification);
    }

    private void display(ObservableList<OrderTickets> all) {
        if(!all.isEmpty()) {
            companyColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getCompany()));
            ticketColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTicket()));
            statusColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getStatus()));
            tableView.setItems(all);
        }
    }
}
