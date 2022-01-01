package tuvarna.sit.busservices.presentation.controllers;

import javafx.collections.FXCollections;
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
import tuvarna.sit.common.Constants;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddTicketsController implements Initializable {
    private static TicketService ticketService = TicketService.getInstance();
    private static StatusService statusService = StatusService.getInstance();
    private static OrderTicketsService orderTicketsService = OrderTicketsService.getInstance();

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
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void create(MouseEvent mouseEvent) {
        if(!validationFields()) {
            return;
        }
        Status st = statusService.getById(5L);
        for (int i = 0; i < Integer.parseInt(countTickets.getText()); i++) {
            Ticket ticket = new Ticket(travelBox.getValue(), Double.parseDouble(price.getText()), statusService.getById(6L));
            ticketService.save(ticket);
            OrderTickets orderTickets = new OrderTickets(stationBox.getValue(), ticket, HelloApplication.getUser().getCompany(), st);
            orderTicketsService.save(orderTickets);
        }
        notification();
        back(mouseEvent);
    }

    private boolean validationFields() {
        String regex = Constants.Regex.FOR_INT_DIGIT;
        Pattern pattern = Pattern.compile(regex);

        if(countTickets.getText() == null || countTickets.getText().trim().isEmpty() || !pattern.matcher(countTickets.getText()).find()) {
            messageBox(Constants.MessageError.COUNT_TICKET_EMPTY);
            return false;
        }

        if(travelBox.getSelectionModel().isEmpty() || travelBox.getSelectionModel() == null) {
            messageBox(Constants.MessageError.TRAVEL_EMPTY);
            return false;
        }

        if(price.getText() == null || price.getText().trim().isEmpty() || !price.getText().matches(Constants.Regex.FOR_DOUBLE_DIGIT)) {
            messageBox(Constants.MessageError.PRICE_EMPTY);
            return false;
        }

        if(stationBox.getSelectionModel().isEmpty() || stationBox.getSelectionModel() == null) {
            messageBox(Constants.MessageError.STATION_EMPTY);
            return false;
        }
        return true;
    }

    private void notification() {
        UserService userService = UserService.getInstance();
        User byIdStation = userService.getByIdStation(stationBox.getValue().getID());
        Notification notification = new Notification(Constants.Notification.ADDED_NEW_TICKET, byIdStation);
        NotificationService notificationRepository = NotificationService.getInstance();
        notificationRepository.save(notification);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(Constants.View.WINDOW_COMPANY_OPTION);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.COMPANY);
    }

    private void fillComboBoxes() {
        StationService stationService = StationService.getInstance();
        List<Station> all = stationService.getAll();
        stationBox.setItems(FXCollections.observableArrayList(all));

        TravelService travelService = TravelService.getInstance();
        List<Travel> tr = travelService.getAll();
        travelBox.setItems(FXCollections.observableArrayList(tr));
    }


}
