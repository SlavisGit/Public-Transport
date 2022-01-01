package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.CashierService;
import tuvarna.sit.busservices.data.entities.Cashier;
import tuvarna.sit.common.Constants;

public class CashierListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button back;

    @FXML
    private TableColumn<Cashier, String> firstName;

    @FXML
    private TableColumn<Cashier, String> honorarium;

    @FXML
    private TableColumn<Cashier, String> lastName;

    @FXML
    private TableColumn<Cashier, String> station;

    @FXML
    private TableView<Cashier> tableView;

    @FXML
    private TableColumn<Cashier, String> ucn;

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
        if(HelloApplication.getUser().getAdministrator() != null) {
            URL path = getClass().getResource(Constants.View.WINDOW_ADMIN_OPTION);
            logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.ADMIN);
        } else {
            URL path = getClass().getResource(Constants.View.WINDOW_STATION_OPTION);
            logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.STATION);
        }
    }
    private void display(){
        CashierService cashierService = CashierService.getInstance();
        ObservableList<Cashier> all = cashierService.getAll();
        if(HelloApplication.getUser().getAdministrator() != null){
            all.sort(Comparator.comparing(Cashier::getCountTicket).thenComparing(Cashier::getHonorarium).reversed());
        }
        firstName.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getFirstName()));

        lastName.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getLastName()));

        ucn.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getUcn()));

        honorarium.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getHonorarium()));

        station.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getStation()));

        tableView.setItems(all);
    }
}
