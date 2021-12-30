package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.data.entities.*;
import tuvarna.sit.busservices.data.repository.*;

public class BuyTicketsController implements Initializable {

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

    private void create(MouseEvent mouseEvent) {
        Client client = new Client(firstName.getText(), lastName.getText());

        ClientRepository clientRepository = ClientRepository.getInstance();
        client = getClient(client, clientRepository);
        if (client.getID() == null) {
            clientRepository.save(client);
        }

        ClientWithTickets clientWithTickets = new ClientWithTickets(client, placeComboBox.getValue());
        ClientWithTicketsRepository clientWithTicketsRepository = ClientWithTicketsRepository.getInstance();
        clientWithTicketsRepository.save(clientWithTickets);

        TicketRepository ticketRepository = TicketRepository.getInstance();
        StatusRepository statusRepository = StatusRepository.getInstance();

        Status st = statusRepository.getById(2L);
        Ticket value = placeComboBox.getValue();
        value.setStatus(st);
        ticketRepository.update(value);

        extracted();

        UserService userService = UserService.getInstance();
        User byIdStation = userService.getByIdCompany(value.getTravel().getCompany().getID());
        Notification notification = new Notification("Bought ticket!", byIdStation);
        NotificationRepository notificationRepository = NotificationRepository.getInstance();
        notificationRepository.save(notification);
    }

    private void extracted() {
        Cashier cashier = HelloApplication.getUser().getCashier();
        cashier.setCountTicket(cashier.getCountTicket() + 1);
        CashierRepository cashierRepository = CashierRepository.getInstance();
        cashierRepository.update(cashier);


    }


    private Client getClient(Client client, ClientRepository clientRepository) {
        List<Client> all = clientRepository.getAll();
        for (Client cl : all) {
            if(cl.getFirstName().equals(client.getFirstName()) && cl.getLastName().equals(client.getLastName())) {
               client = cl;
               break;
            }
        }
        return client;
    }

    private void fillComboBoxPlaces(Destination destination) {
        TicketRepository ticketRepository = TicketRepository.getInstance();
        List<Ticket> all = ticketRepository.getWhereDestination(destination);
        placeComboBox.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxDestination() {
        DestinationRepository destinationRepository = DestinationRepository.getInstance();
        List<Destination> all = destinationRepository.getAll();
        destinationComboBox.setItems(FXCollections.observableArrayList(all));
    }


}

