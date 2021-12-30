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
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.StationService;
import tuvarna.sit.busservices.data.entities.Station;


public class StationListController {

    private static StationService stationService = StationService.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private TableColumn<Station, String> address = new TableColumn<>();

    @FXML
    private TableColumn<Station, String> end = new TableColumn<>();

    @FXML
    private TableColumn<Station, String> name = new TableColumn<>();

    @FXML
    private TableColumn<Station, String> start = new TableColumn<>();

    @FXML
    private URL location;

    @FXML
    private Button back;


    @FXML
    private TableView<Station> tableView;

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'stationList.fxml'.";
        assert tableView != null : "fx:id=\"listView\" was not injected: check your FXML file 'stationList.fxml'.";
        displayProductions();
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company");
    }

    public void displayProductions(){
        ObservableList<Station> all = stationService.getAll();
        name.setCellValueFactory(p -> new ReadOnlyObjectWrapper(p.getValue().getName()));

        address.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getAddress()));

        start.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getWorkTimeStart()));

        end.setCellValueFactory(b -> new ReadOnlyObjectWrapper(b.getValue().getWorkTimeEnd()));

        tableView.setItems(all);
    }
}
