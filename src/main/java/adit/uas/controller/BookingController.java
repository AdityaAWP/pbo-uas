package adit.uas.controller;

import adit.uas.model.Booking;
import adit.uas.model.BookingDAO;
import adit.uas.model.Travel;
import adit.uas.model.TravelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BookingController {

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> idColumn;
    @FXML
    private TableColumn<Booking, String> customerNameColumn;
    @FXML
    private TableColumn<Booking, String> travelOriginColumn;
    @FXML
    private TableColumn<Booking, String> travelDestinationColumn;
    @FXML
    private TableColumn<Booking, String> travelScheduleColumn;
    @FXML
    private TableColumn<Booking, Integer> ticketCountColumn;
    @FXML
    private TableColumn<Booking, Double> totalCostColumn;

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField ticketCountField;
    @FXML
    private ChoiceBox<Travel> travelChoiceBox;

    private BookingDAO bookingDAO;
    private TravelDAO travelDAO;
    private ObservableList<Booking> bookingData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        bookingDAO = new BookingDAO();
        travelDAO = new TravelDAO();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        travelOriginColumn.setCellValueFactory(new PropertyValueFactory<>("travelOrigin"));
        travelDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("travelDestination"));
        travelScheduleColumn.setCellValueFactory(new PropertyValueFactory<>("travelSchedule"));
        ticketCountColumn.setCellValueFactory(new PropertyValueFactory<>("ticketCount"));
        totalCostColumn.setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        loadBookingData();
        loadTravelChoices();
    }

    private void loadBookingData() {
        bookingData.setAll(bookingDAO.getAllBookings());
        bookingTable.setItems(bookingData);
    }

    private void loadTravelChoices() {
        List<Travel> travels = travelDAO.getAllTravels();
        travelChoiceBox.getItems().setAll(travels);
        travelChoiceBox.setConverter(new javafx.util.StringConverter<Travel>() {
            @Override
            public String toString(Travel travel) {
                if (travel == null) {
                    return "";
                }
                return travel.getOrigin() + " to " + travel.getDestination() + " at " + travel.getSchedule();
            }

            @Override
            public Travel fromString(String string) {
                return travelChoiceBox.getItems().stream()
                        .filter(ap -> (ap.getOrigin() + " to " + ap.getDestination() + " at " + ap.getSchedule())
                                .equals(string))
                        .findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void addBooking() {
        String customerName = customerNameField.getText();
        Travel selectedTravel = travelChoiceBox.getValue();
        if (selectedTravel == null) {
            showAlert("No Travel Selected", "Please select a travel route.");
            return;
        }
        int ticketCount;
        try {
            ticketCount = Integer.parseInt(ticketCountField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Ticket Count", "Please enter a valid number for ticket count.");
            return;
        }

        double totalCost = ticketCount * selectedTravel.getPrice();

        Booking booking = new Booking(0, customerName, selectedTravel.getId(), selectedTravel.getOrigin(),
                selectedTravel.getDestination(), selectedTravel.getSchedule(), ticketCount, totalCost);
        bookingDAO.addBooking(booking);
        loadBookingData();
    }

    @FXML
    private void cancelBooking() {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            bookingDAO.cancelBooking(selectedBooking);
            loadBookingData();
        } else {
            showAlert("No Booking Selected", "Please select a booking to cancel.");
        }
    }

    @FXML
    private void printTicket() {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            showAlert("No Booking Selected", "Please select a booking to print the ticket.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/adit/uas/PrintTicketView.fxml"));
            Scene scene = new Scene(loader.load());

            PrintTicketController controller = loader.getController();
            controller.setBooking(selectedBooking);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Print Ticket");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
