package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.CashierService;
import tuvarna.sit.busservices.presentation.models.CashierListView;
import tuvarna.sit.busservices.presentation.models.TravelListView;

public class CashierListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TableColumn<CashierListView, String> firstName;

    @FXML
    private TableColumn<CashierListView, String> honorarium;

    @FXML
    private TableColumn<CashierListView, String> lastName;

    @FXML
    private TableColumn<CashierListView, String> station;

    @FXML
    private TableView<CashierListView> tableView;

    @FXML
    private TableColumn<CashierListView, String> ucn;

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert honorarium != null : "fx:id=\"honorarium\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert station != null : "fx:id=\"station\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'cashierList.fxml'.";
        assert ucn != null : "fx:id=\"ucn\" was not injected: check your FXML file 'cashierList.fxml'.";
        display();
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/stationOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Station");
    }
    public void display(){
        CashierService cashierService = CashierService.getInstance();
        ObservableList<CashierListView> all = cashierService.getAll();
        firstName.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getFirstName()));

        lastName.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getLastName()));

        ucn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getUcn()));

        honorarium.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getHonorarium()));

        station.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getStation()));

        tableView.setItems(all);
    }
}
