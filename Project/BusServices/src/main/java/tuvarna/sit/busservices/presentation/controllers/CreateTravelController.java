package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tuvarna.sit.busservices.data.entities.Destination;
import tuvarna.sit.busservices.data.entities.Station;
import tuvarna.sit.busservices.data.entities.Transport;
import tuvarna.sit.busservices.data.entities.TravelType;
import tuvarna.sit.busservices.data.repository.DestinationRepository;
import tuvarna.sit.busservices.data.repository.StationRepository;
import tuvarna.sit.busservices.data.repository.TransportRepository;
import tuvarna.sit.busservices.data.repository.TravelTypeRepository;

public class CreateTravelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addDestination;

    @FXML
    private Button addTransport;

    @FXML
    private Button addTravelType;

    @FXML
    private Button back;

    @FXML
    private TextField countPlaces;

    @FXML
    private Button create;

    @FXML
    private DatePicker dataFrom;

    @FXML
    private DatePicker dataTo;

    @FXML
    private ComboBox<Destination> destination;

    @FXML
    private TextField limitation;

    @FXML
    private ComboBox<Station> station;

    @FXML
    private ComboBox<Transport> transport;

    @FXML
    private ComboBox<TravelType> travelType;

    @FXML
    void initialize() {
        fillComboBoxTravelType();
        fillComboBoxStation();
        fillComboBoxDestination();
        fillComboBoxTransport();

        create.setOnMouseClicked(this::createTravel);
        addDestination.setOnMouseClicked(this::addDestination);
        addTransport.setOnMouseClicked(this::addTransport);
        addTravelType.setOnMouseClicked(this::addTravelType);
    }

    private void addTravelType(MouseEvent mouseEvent) {
    }

    private void addTransport(MouseEvent mouseEvent) {
    }

    private void addDestination(MouseEvent mouseEvent) {
    }

    private void createTravel(MouseEvent mouseEvent) {
    }

    public void fillComboBoxTravelType() {
        TravelTypeRepository travelTypeRepository = TravelTypeRepository.getInstance();
        List<TravelType> all = travelTypeRepository.getAll();
        travelType.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxDestination() {
        DestinationRepository destinationRepository = DestinationRepository.getInstance();
        List<Destination> all = destinationRepository.getAll();
        destination.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxTransport() {
        TransportRepository transportRepository = TransportRepository.getInstance();
        List<Transport> all = transportRepository.getAll();
        transport.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxStation() {
        StationRepository stationRepository = StationRepository.getInstance();
        List<Station> all = stationRepository.getAll();
        station.setItems(FXCollections.observableArrayList(all));
    }

}
