package tuvarna.sit.busservices.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.*;
import tuvarna.sit.busservices.data.entities.*;


public class TravelListController {

    TicketService ticketService = TicketService.getInstance();
    StationService stationService = StationService.getInstance();
    UserService userService = UserService.getInstance();
    NotificationService notificationService = NotificationService.getInstance();
    CashierService cashierService = CashierService.getInstance();
    TravelService travelService = TravelService.getInstance();
    UserType userType = HelloApplication.getUser().getUserType();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Travel, String> companyColumn;

    @FXML
    private TableColumn<Travel, String> countPlacesColumn;

    @FXML
    private TableColumn<Travel, String> dataFromColumn;

    @FXML
    private TableColumn<Travel, String> dataToColumn;

    @FXML
    private TableColumn<Travel, String> destinationColumn;

    @FXML
    private TableColumn<Travel, String> travelTypeColumn;

    @FXML
    private TableColumn<Travel, String> transportColumn;

    @FXML
    private TableView<Travel> tableView;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'travelList.fxml'.";
        assert tableView != null : "fx:id=\"listView\" was not injected: check your FXML file 'travelList.fxml'.";

        ContextMenu contextMenu = new ContextMenu();

        if(userType.getUserType().equals("Company")) {
            display(travelService.getAllTravelForCompany());
            back.setOnMouseClicked(this::backCompany);
            MenuItem menuItem1 = deleteTravelMenuItem(travelService);
            contextMenu.getItems().add(menuItem1);
        } else if(userType.getUserType().equals("Cashier")) {
            display(travelService.getAllTravelForCashier());
            back.setOnMouseClicked(this::backCashier);
        } else if(userType.getUserType().equals("Station")) {
            display(travelService.getAllTravelForStation());
            back.setOnMouseClicked(this::backStation);
        }
        MenuItem menuItem = ViewTicketMenuItem();
        contextMenu.getItems().add(menuItem);
        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(tableView, event.getSceneX(), event.getSceneY());
                }
            }
        });
    }

    private MenuItem ViewTicketMenuItem() {
        MenuItem menuItem = new MenuItem("View Ticket");
        menuItem.setOnAction((ActionEvent event) ->{
            Travel selectedItem = tableView.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tuvarna/sit/busservices/presentation.view/ticketView.fxml"));
                    try {
                        Parent root = (Parent) loader.load();
                        TicketViewController ticketViewController = loader.getController();
                        ticketViewController.findTicket(selectedItem);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        return menuItem;
    }

    private MenuItem deleteTravelMenuItem(TravelService travelService) {
        MenuItem menuItem1 = new MenuItem("Delete Travel");
        menuItem1.setOnAction((ActionEvent event) -> {
            Travel selectedItem = tableView.getSelectionModel().getSelectedItem();

            if(!selectedItem.getTicketSet().isEmpty()) {
                for(Ticket st : selectedItem.getTicketSet()) {
                    ticketService.delete(st);
                }
            }
            travelService.delete(selectedItem);

            checkStationForNotify();

            checkCashiersForNotify();
            tableView.getItems().remove(selectedItem);
        } );
        return menuItem1;
    }

    private void checkCashiersForNotify() {
        ObservableList<Cashier> all1 = cashierService.getAll();
        if(!all1.isEmpty()) {
            for (Cashier cashier :all1) {
                User byIdCashier = userService.getByIdCashier(cashier.getID());
                Notification notification1 = new Notification("Delete travel!", byIdCashier);
                notificationService.save(notification1);
            }
        }
    }

    private void checkStationForNotify() {
        List<Station> all = stationService.getAll();
        if(!all.isEmpty()) {
            for (Station station: all) {
                User byIdStation = userService.getByIdStation(station.getID());
                Notification notification = new Notification("Delete travel!", byIdStation);
                notificationService.save(notification);
            }
        }
    }

    public void display(ObservableList<Travel> all){
        travelTypeColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTravelType()));

        destinationColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDestination()));

        transportColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getTransportType()));

        dataToColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDataTo()));

        dataFromColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDataFrom()));

        countPlacesColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getCountPlaces()));

        companyColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getCompany()));

        tableView.setItems(all);
    }
    private void backStation(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/stationOptions.fxml", "Station");
    }


    private void backCashier(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml", "Cashier");
    }

    private void backCompany(MouseEvent mouseEvent) {
        newWindow(mouseEvent, "/tuvarna/sit/busservices/presentation.view/companyOptions.fxml", "Company");
    }

    private void newWindow(MouseEvent mouseEvent, String s, String company) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource(s);
        logInApplication.logInUser(resources, mouseEvent, path, company);
    }

}

