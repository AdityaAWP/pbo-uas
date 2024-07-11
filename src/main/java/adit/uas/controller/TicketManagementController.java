package adit.uas.controller;

import adit.uas.model.Travel;
import adit.uas.model.TravelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TicketManagementController {

    @FXML
    private TableView<Travel> travelTable;
    @FXML
    private TableColumn<Travel, Integer> idColumn;
    @FXML
    private TableColumn<Travel, String> originColumn;
    @FXML
    private TableColumn<Travel, String> destinationColumn;
    @FXML
    private TableColumn<Travel, String> scheduleColumn;
    @FXML
    private TableColumn<Travel, Double> priceColumn;
    @FXML
    private TableColumn<Travel, Integer> jumlahTiketColumn;

    private TravelDAO travelDAO;
    private ObservableList<Travel> travelData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        travelDAO = new TravelDAO();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        scheduleColumn.setCellValueFactory(new PropertyValueFactory<>("schedule"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        jumlahTiketColumn.setCellValueFactory(new PropertyValueFactory<>("jumlahTiket"));

        loadTravelData();
        checkLowTickets();
    }

    private void loadTravelData() {
        List<Travel> travels = travelDAO.getAllTravels();
        travelData.setAll(travels);
        travelTable.setItems(travelData);
    }

    private void checkLowTickets() {
        List<Travel> lowTicketTravels = travelDAO.getTravelsWithLowTickets(3);
        if (!lowTicketTravels.isEmpty()) {
            StringBuilder message = new StringBuilder("The following routes have less than 3 tickets left:\n");
            for (Travel travel : lowTicketTravels) {
                message.append(travel.getOrigin()).append(" to ").append(travel.getDestination())
                        .append(" at ").append(travel.getSchedule()).append(" - ")
                        .append(travel.getJumlahTiket()).append(" tickets left\n");
            }
            showAlert("Low Tickets", message.toString());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
