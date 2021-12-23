package tuvarna.sit.busservices.presentation.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import org.controlsfx.control.action.Action;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.TravelService;
import tuvarna.sit.busservices.data.entities.UserType;
import tuvarna.sit.busservices.presentation.models.TravelListView;


public class TravelListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<TravelListView, String> companyColumn;

    @FXML
    private TableColumn<TravelListView, String> countPlacesColumn;

    @FXML
    private TableColumn<TravelListView, String> dataFromColumn;

    @FXML
    private TableColumn<TravelListView, String> dataToColumn;

    @FXML
    private TableColumn<TravelListView, String> destinationColumn;

    @FXML
    private TableColumn<TravelListView, String> stationColumn;

    @FXML
    private TableColumn<TravelListView, String> travelTypeColumn;

    @FXML
    private TableColumn<TravelListView, String> transportColumn;

    @FXML
    private TableView<TravelListView> tableView;

    @FXML
    private Button back;

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'travelList.fxml'.";
        assert tableView != null : "fx:id=\"listView\" was not injected: check your FXML file 'travelList.fxml'.";
        TravelService travelService = TravelService.getInstance();


        UserType userType = HelloApplication.getUser().getUserType();

        if(userType.getUserType().equals("Company")) {
            display(travelService.getAllTravelForCompany());
            back.setOnMouseClicked(this::backCompany);
        } else if(userType.getUserType().equals("Cashier")) {
            display(travelService.getAllTravelForCashier());
            back.setOnMouseClicked(this::backCashier);
        } else if(userType.getUserType().equals("Station")) {
            display(travelService.getAllTravelForStation());
            back.setOnMouseClicked(this::backStation);
        }

        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem = new MenuItem("View Ticket");
        menuItem.setOnAction((ActionEvent event) ->{
            TravelListView selectedItem = tableView.getSelectionModel().getSelectedItem();

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
    public void display(ObservableList<TravelListView> all){
        travelTypeColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getTravelType()));

        destinationColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDestination()));

        transportColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getTransportType()));

        dataToColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDataTo()));

        dataFromColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getDataFrom()));

        countPlacesColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getCountPlaces()));

        stationColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getStation()));

        companyColumn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getCompany()));

        tableView.setItems(all);
    }
    private void backStation(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/stationOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station");
    }


    private void backCashier(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Cashier");
    }

    private void backCompany(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company");
    }

}

