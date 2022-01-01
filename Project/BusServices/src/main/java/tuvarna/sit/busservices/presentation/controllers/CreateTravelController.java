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
import tuvarna.sit.common.Constants;

public class CreateTravelController {
    private static TravelService travelService = TravelService.getInstance();
    private static TravelTypeService travelTypeService = TravelTypeService.getInstance();
    private static TransportService transportService = TransportService.getInstance();
    private static DestinationService destinationService = DestinationService.getInstance();

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
    private ComboBox<Transport> transport;

    @FXML
    private ComboBox<TravelType> travelType;

    public ComboBox<TravelType> getTravelType() {
        return travelType;
    }

    @FXML
    void initialize() {
        fillComboBoxTravelType();
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
        URL path = getClass().getResource(Constants.View.WINDOW_COMPANY_OPTION);
        logInApplication.logInUser(resources, mouseEvent, path, Constants.Titles.COMPANY);
    }

    private void addTravelType(MouseEvent mouseEvent) {
        openNewDaughterWindow(Constants.View.WINDOW_CREATE_TRAVEL_TYPE, Constants.Titles.TRAVEL_TYPE);
    }

    private void addTransport(MouseEvent mouseEvent) {
        openNewDaughterWindow(Constants.View.WINDOW_CREATE_TRANSPORT, Constants.Titles.TRANSPORT);
    }

    private void addDestination(MouseEvent mouseEvent) {
        openNewDaughterWindow(Constants.View.WINDOW_CREATE_DESTINATION, Constants.Titles.DESTINATION);
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
        alert.setTitle(Constants.MessageError.INCORRECT_DATA);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private boolean validationFields() {
        if(countPlaces.getText() == null || countPlaces.getText().trim().isEmpty() || !countPlaces.getText().matches(Constants.Regex.FOR_INT_DIGIT)) {
            messageBox(Constants.MessageError.COUNT_PLACE_EMPTY);
            return false;
        }
        if(travelType.getSelectionModel().isEmpty() || travelType.getSelectionModel() == null) {
            messageBox(Constants.MessageError.TRAVEL_TYPE_EMPTY);
            return false;
        }
        if(destination.getSelectionModel().isEmpty() || destination.getSelectionModel() == null) {
            messageBox(Constants.MessageError.DESTINATION_EMPTY);
            return false;
        }
        if(transport.getSelectionModel().isEmpty() || transport.getSelectionModel() == null) {
            messageBox(Constants.MessageError.TRANSPORT_EMPTY);
            return false;
        }
        if(dataTo.getValue() == null || dataTo.getValue().isAfter(LocalDate.now())) {
            messageBox(Constants.MessageError.DATA_TO_EMPTY);
            return false;
        }
        if(dataFrom.getValue() == null || dataFrom.getValue().isAfter(LocalDate.now())) {
            messageBox(Constants.MessageError.DATA_FROM_EMPTY);
            return false;
        }
        return true;
    }
    private void createTravel(MouseEvent mouseEvent) {
        if(!validationFields())
        {
            return;
        }
        Travel travel = new Travel(travelType.getValue(), destination.getValue(), transport.getValue(),
                dataTo.getValue(), dataFrom.getValue(), Integer.parseInt(countPlaces.getText()),
                HelloApplication.getUser().getCompany());

        travelService.save(travel);

        back(mouseEvent);
    }

    private void fillComboBoxTravelType() {
        travelType.getItems().clear();
        List<TravelType> all = travelTypeService.getAll();
        travelType.setItems(FXCollections.observableArrayList(all));
    }

    private void fillComboBoxDestination() {
        List<Destination> all = destinationService.getAll();
        destination.setItems(FXCollections.observableArrayList(all));
    }

    private void fillComboBoxTransport() {
        List<Transport> all = transportService.getAll();
        transport.setItems(FXCollections.observableArrayList(all));
    }



}
