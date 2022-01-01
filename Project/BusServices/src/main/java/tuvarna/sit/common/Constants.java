package tuvarna.sit.common;

public class Constants {
    public static class View {
        public static final String WINDOW_COMPANY_OPTION = "/tuvarna/sit/busservices/presentation.view/companyOptions.fxml";
        public static final String WINDOW_CASHIER_OPTION = "/tuvarna/sit/busservices/presentation.view/cashierOptions.fxml";
        public static final String WINDOW_ADMIN_OPTION = "/tuvarna/sit/busservices/presentation.view/administratorOptions.fxml";
        public static final String WINDOW_STATION_OPTION = "/tuvarna/sit/busservices/presentation.view/stationOptions.fxml";

        public static final String WINDOW_CREATE_ADMIN = "/tuvarna/sit/busservices/presentation.view/createAdmin.fxml";
        public static final String WINDOW_CREATE_COMPANY = "/tuvarna/sit/busservices/presentation.view/createCompany.fxml";
        public static final String WINDOW_CREATE_STATION = "/tuvarna/sit/busservices/presentation.view/createStation.fxml";
        public static final String WINDOW_CREATE_TRAVEL = "/tuvarna/sit/busservices/presentation.view/createTravel.fxml";
        public static final String WINDOW_CREATE_TRAVEL_TYPE = "/tuvarna/sit/busservices/presentation.view/createTravelType.fxml";
        public static final String WINDOW_CREATE_DESTINATION = "/tuvarna/sit/busservices/presentation.view/createDestination.fxml";
        public static final String WINDOW_CREATE_TRANSPORT = "/tuvarna/sit/busservices/presentation.view/createTransport.fxml";
        public static final String WINDOW_CREATE_CASHIER = "/tuvarna/sit/busservices/presentation.view/createCashier.fxml";

        public static final String WINDOW_CASHIER_LIST = "/tuvarna/sit/busservices/presentation.view/cashierList.fxml";
        public static final String WINDOW_TRAVEL_LIST = "/tuvarna/sit/busservices/presentation.view/travelList.fxml";
        public static final String WINDOW_ORDER_TICKETS = "/tuvarna/sit/busservices/presentation.view/orderTickets.fxml";
        public static final String WINDOW_STATION_LIST = "/tuvarna/sit/busservices/presentation.view/stationList.fxml";
        public static final String WINDOW_TICKET_LIST = "/tuvarna/sit/busservices/presentation.view/ticketView.fxml";

        public static final String WINDOW_HELLO_VIEW = "/tuvarna/sit/busservices/presentation.view/hello-view.fxml";
        public static final String WINDOW_BUY_TICKET = "/tuvarna/sit/busservices/presentation.view/buyTickets.fxml";
        public static final String WINDOW_ADD_TICKET = "/tuvarna/sit/busservices/presentation.view/addTickets.fxml";
        public static final String WINDOW_LOGIN = "/tuvarna/sit/busservices/presentation.view/userLogIn-view.fxml";

    }
    public static class Notification {
        public static final String ADDED_NEW_TICKET = "Added new ticket!";
        public static final String BOUGHT_TICKET = "Bought ticket!";
        public static final String REFUSAL_TICKETS = "Refusal tickets!";
        public static final String CONFIRM_TICKETS = "The tickets were confirmed";
        public static final String DELETE_TRAVEL = "Delete travel!";
    }
    public static class Status {
        public static final String DISAGREE = "disagree";
        public static final String AGREE = "agree";
        public static final String NOT_SEEN = "not seen";
    }
    public static class Titles {
        public static final String COMPANY = "Company";
        public static final String CASHIER = "Cashier";
        public static final String ADMIN = "Admin";
        public static final String STATION = "Station";
        public static final String TRAVEL = "Travel";
        public static final String TICKET_VIEW = "Ticket View";
        public static final String TICKET = "Tickets";
        public static final String TRANSPORT = "Transport";
        public static final String TRAVEL_TYPE = "Travel Type";
        public static final String DESTINATION = "Destination";
        public static final String CASHIER_RATING = "Cashier Rating";
        public static final String ORDER_TICKETS = "Order Tickets";
        public static final String ADMIN_CREATION = "Admin creation";
        public static final String COMPANY_CREATION = "Company creation";
        public static final String BUS_SERVICE = "Bus services";
        public static final String STATION_CREATION = "Station creation";
        public static final String CASHIER_CREATION = "Cashier creation";
        public static final String SOLD = "Sold";
        public static final String CASHIER_LOGIN = "Cashier login";
        public static final String COMPANY_LOGIN = "Company login";
        public static final String ADMIN_LOGIN = "Admin login";
        public static final String STATION_LOGIN = "Station login";
    }
    public static class MessageError {
            public static final String COUNT_TICKET_EMPTY = "Field Count Tickets is empty";
            public static final String TRAVEL_EMPTY = "Field Travel is empty";
            public static final String PRICE_EMPTY = "Field Price is empty";
            public static final String STATION_EMPTY = "Field Station is empty";
            public static final String INCORRECT_DATA = "Incorrect data";
            public static final String FIRSTNAME_EMPTY = "Field firstName is empty";
            public static final String LASTNAME_EMPTY = "Field lastName is empty";
            public static final String PLACE_EMPTY = "Field place is empty";
            public static final String USERNAME_EMPTY = "Field username is empty";
            public static final String PASSWORD_EMPTY = "Field password is empty";
            public static final String UCN_EMPTY = "Field ucn is empty";
            public static final String HONORARIUM_EMPTY = "Field honorarium is empty";
            public static final String NAME_EMPTY = "Field name is empty";
            public static final String ADDRESS_EMPTY = "Field address is empty";
            public static final String DESTINATION_EMPTY = "Field destination is empty";
            public static final String START_TIME_EMPTY = "Field start Time is empty";
            public static final String END_TIME_EMPTY = "Field END Time is empty";
            public static final String TRANSPORT_EMPTY = "Field transport is empty";
            public static final String COUNT_PLACE_EMPTY = "Field countPlaces is empty";
            public static final String TRAVEL_TYPE_EMPTY = "Field travelType is empty";
            public static final String DATA_TO_EMPTY = "Field dataTo is empty or invalid date";
            public static final String DATA_FROM_EMPTY = "Field dataFROM is empty or invalid date";
            public static final String INVALID = "Invalid username or password. Please, try again.";
    }
    public static class Regex {
            public static final String FOR_DOUBLE_DIGIT = "\\d+(\\.\\d+)?";
            public static final String FOR_INT_DIGIT = "\\d+";
            public static final String FOR_UCN = "[0-9]{10}";
    }
}
