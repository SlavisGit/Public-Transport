package tuvarna.sit.busservices.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.OrderTicketsService;
import tuvarna.sit.busservices.business.services.StatusService;
import tuvarna.sit.busservices.business.services.TicketService;
import tuvarna.sit.busservices.business.services.UserService;
import tuvarna.sit.busservices.data.entities.*;
import tuvarna.sit.busservices.data.repository.NotificationRepository;
import tuvarna.sit.busservices.data.repository.StationRepository;
import tuvarna.sit.busservices.data.repository.StatusRepository;
import tuvarna.sit.busservices.data.repository.TravelRepository;
import tuvarna.sit.busservices.presentation.models.TicketListView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddTicketsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button back;

    @FXML
    private Button create;

    @FXML
    private TextField price;

    @FXML
    private ComboBox<Station> stationBox;

    @FXML
    private TextField countTickets;

    @FXML
    private ComboBox<Travel> travelBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillComboBoxes();
        back.setOnMouseClicked(this::back);
        create.setOnMouseClicked(this::create);
    }

    private void create(MouseEvent mouseEvent) {
        TicketService ticketService = TicketService.getInstance();
        StatusService statusService = StatusService.getInstance();
        OrderTicketsService orderTicketsService = OrderTicketsService.getInstance();
        StatusRepository statusRepository = StatusRepository.getInstance();

        Status st = statusRepository.getById(5L);
        for (int i = 0; i < Integer.parseInt(countTickets.getText()); i++) {
            Ticket ticket = new Ticket(travelBox.getValue(), Double.parseDouble(price.getText()), statusService.getById(6L));
            ticketService.save(ticket);
            OrderTickets orderTickets = new OrderTickets(stationBox.getValue(), ticket, HelloApplication.getUser().getCompany(), st);
            orderTicketsService.save(orderTickets);
        }
        UserService userService = UserService.getInstance();
        User byIdStation = userService.getByIdStation(stationBox.getValue().getID());
        Notification notification = new Notification("Added new ticket!", byIdStation);
        NotificationRepository notificationRepository = NotificationRepository.getInstance();
        notificationRepository.save(notification);

    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company");
    }

    private void fillComboBoxes() {
        StationRepository stationRepository = StationRepository.getInstance();
        List<Station> all = stationRepository.getAll();
        stationBox.setItems(FXCollections.observableArrayList(all));

        TravelRepository travelRepository = TravelRepository.getInstance();
        List<Travel> tr = travelRepository.getAll();
        travelBox.setItems(FXCollections.observableArrayList(tr));
    }


}
