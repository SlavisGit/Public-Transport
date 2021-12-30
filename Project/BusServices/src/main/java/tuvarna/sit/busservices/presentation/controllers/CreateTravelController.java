package tuvarna.sit.busservices.presentation.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tuvarna.sit.busservices.application.HelloApplication;
import tuvarna.sit.busservices.application.NewWindowApplication;
import tuvarna.sit.busservices.business.services.*;
import tuvarna.sit.busservices.data.entities.*;

public class CreateTravelController {
    private static TravelService travelService = TravelService.getInstance();
    private static TravelTypeService travelTypeService = TravelTypeService.getInstance();
    private static TransportService transportService = TransportService.getInstance();
    private static DestinationService destinationService = DestinationService.getInstance();
    private static StationService stationService = StationService.getInstance();

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

    public ComboBox<TravelType> getTravelType() {
        return travelType;
    }

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
        back.setOnMouseClicked(this::back);
    }

    private void back(MouseEvent mouseEvent) {
        NewWindowApplication logInApplication = new NewWindowApplication();
        URL path = getClass().getResource("/tuvarna/sit/busservices/presentation.view/companyOptions.fxml");
        logInApplication.logInUser(resources, mouseEvent, path, "Company");
    }

    private void addTravelType(MouseEvent mouseEvent) {
        openNewDaughterWindow("/tuvarna/sit/busservices/presentation.view/createTravelType.fxml", "Travel Type");
    }

    private void addTransport(MouseEvent mouseEvent) {
        openNewDaughterWindow("/tuvarna/sit/busservices/presentation.view/createTransport.fxml", "Transport");
    }

    private void addDestination(MouseEvent mouseEvent) {
        openNewDaughterWindow("/tuvarna/sit/busservices/presentation.view/createDestination.fxml", "Travel Type");
    }

    private void openNewDaughterWindow(String s, String s2) {
        Parent root;
        try {
            URL path = getClass().getResource(s);
            root = FXMLLoader.load(path, resources);
            Stage stage = new Stage();
            stage.setTitle(s2);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void messageBox(String message) {
        Alert alert = new Alert(Alert.AlertType.
                ERROR);
        alert.setTitle("Incorrect data");
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void validationFields() {
        if(countPlaces.getText() == null || countPlaces.getText().trim().isEmpty() || countPlaces.getText().matches("\"[0-9]*\"")) {
            messageBox("Field countPlaces is empty");
        }
        if(limitation.getText() == null || limitation.getText().trim().isEmpty()) {
            messageBox("Field lastName is empty");
        }
        if(travelType.getSelectionModel().isEmpty() || travelType.getSelectionModel() == null) {
            messageBox("Field travelType is empty");
        }
        if(destination.getSelectionModel().isEmpty() || destination.getSelectionModel() == null) {
            messageBox("Field destination is empty");
        }
        if(transport.getSelectionModel().isEmpty() || transport.getSelectionModel() == null) {
            messageBox("Field transport is empty");
        }
        if(dataTo.getValue() == null || dataTo.getValue().isAfter(LocalDate.now())) {
            messageBox("Field dataTo is empty or invalid date");
        }
        if(dataFrom.getValue() == null || dataFrom.getValue().isAfter(LocalDate.now())) {
            messageBox("Field dataFrom is empty or invalid date");
        }
    }
    private void createTravel(MouseEvent mouseEvent) {
        validationFields();
        Travel travel = new Travel(travelType.getValue(), destination.getValue(), transport.getValue(),
                dataTo.getValue(), dataFrom.getValue(), Integer.parseInt(countPlaces.getText()), Integer.parseInt(limitation.getText()),
                HelloApplication.getUser().getCompany());

        travelService.save(travel);

        back(mouseEvent);
    }

    public void fillComboBoxTravelType() {
        travelType.getItems().clear();
        List<TravelType> all = travelTypeService.getAll();
        travelType.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxDestination() {
        List<Destination> all = destinationService.getAll();
        destination.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxTransport() {
        List<Transport> all = transportService.getAll();
        transport.setItems(FXCollections.observableArrayList(all));
    }

    public void fillComboBoxStation() {
        List<Station> all = stationService.getAll();
        station.setItems(FXCollections.observableArrayList(all));
    }

}
