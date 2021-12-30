package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.*;
import tuvarna.sit.busservices.data.entities.*;

public class BuyTicketsController implements Initializable {
    private static ClientService clientService = ClientService.getInstance();
    private static ClientWithTicketsService clientWithTicketsService = ClientWithTicketsService.getInstance();
    private static TicketService ticketService = TicketService.getInstance();
    private static StatusService statusService = StatusService.getInstance();
    private static UserService userService = UserService.getInstance();
    private static NotificationService notificationService = NotificationService.getInstance();
    private static CashierService cashierService = CashierService.getInstance();

    @FXML
    private ComboBox<Destination> destinationComboBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button back;

    @FXML
    private Button create;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private ComboBox<Ticket> placeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert destinationComboBox != null : "fx:id=\"destinationComboBox\" was not injected: check your FXML file 'buyTickets.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'buyTickets.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'buyTickets.fxml'.";
        assert placeComboBox != null : "fx:id=\"placeComboBox\" was not injected: check your FXML file 'buyTickets.fxml'.";
        fillComboBoxDestination();
        EventHandler<ActionEvent> event =
                e -> {
                    Destination destination = destinationComboBox.getValue();
                    fillComboBoxPlaces(destination);
                };

        destinationComboBox.setOnAction(event);
        create.setOnMouseClicked(this::create);
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Cashier");
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void validationFields() {
        if(firstName.getText() == null || firstName.getText().trim().isEmpty()) {
            messageBox("Field firstName is empty");
        }

        if(lastName.getText() == null || lastName.getText().trim().isEmpty()) {
            messageBox("Field lastName is empty");
        }

        if(placeComboBox.getSelectionModel().isEmpty() || placeComboBox.getSelectionModel() == null) {
            messageBox("Field place is empty");
        }
    }

    private void create(MouseEvent mouseEvent) {
        validationFields();
        Client client = new Client(firstName.getText(), lastName.getText());
        client = getClient(client, clientService);
        if (client.getID() == null) {
            clientService.save(client);
        }
        ClientWithTickets clientWithTickets = new ClientWithTickets(client, placeComboBox.getValue());
        clientWithTicketsService.save(clientWithTickets);
        Status st = statusService.getById(2L);
        Ticket ticket = placeComboBox.getValue();
        ticket.setStatus(st);
        ticket.setCashier(HelloApplication.getUser().getCashier());
        ticketService.update(ticket);
        updateCountTicket();
        notification(ticket);
        back(mouseEvent);
    }

    private void notification(Ticket value) {
        User byIdStation = userService.getByIdCompany(value.getTravel().getCompany().getID());
        Notification notification = new Notification("Bought ticket!", byIdStation);
        notificationService.save(notification);
    }

    private void updateCountTicket() {
        Cashier cashier = HelloApplication.getUser().getCashier();
        cashier.setCountTicket(cashier.getCountTicket() + 1);
        cashierService.update(cashier);
    }


    private Client getClient(Client client, ClientService clientService) {
        List<Client> all = clientService.getAll();
        for (Client cl : all) {
            if(cl.getFirstName().equals(client.getFirstName()) && cl.getLastName().equals(client.getLastName())) {
               client = cl;
               break;
            }
        }
        return client;
    }

    private void fillComboBoxPlaces(Destination destination) {
        TicketService ticketService = TicketService.getInstance();
        List<Ticket> all = ticketService.getWhereDestination(destination);
        placeComboBox.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxDestination() {
        DestinationService destinationService = DestinationService.getInstance();
        List<Destination> all = destinationService.getAll();
        destinationComboBox.setItems(FXCollections.observableArrayList(all));
    }


}

