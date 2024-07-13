package adit.uas.controller;

import adit.uas.model.Travel;
import adit.uas.model.TravelDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField jumlahTiketField; // New field for updating jumlahTiket

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

        travelTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTravelDetails(newValue));
    }

    private void loadTravelData() {
        List<Travel> travels = travelDAO.getAllTravels();
        travelData.setAll(travels);
        travelTable.setItems(travelData);
    }

    private void showTravelDetails(Travel travel) {
        if (travel != null) {
            jumlahTiketField.setText(Integer.toString(travel.getJumlahTiket()));
        } else {
            jumlahTiketField.setText("");
        }
    }

    @FXML
    private void updateJumlahTiket() {
        Travel selectedTravel = travelTable.getSelectionModel().getSelectedItem();
        if (selectedTravel != null) {
            int newJumlahTiket = Integer.parseInt(jumlahTiketField.getText());
            travelDAO.updateJumlahTiket(selectedTravel.getId(), newJumlahTiket);
            loadTravelData();
        } else {
            showAlert("No Travel Selected", "Please select a travel in the table.");
        }
    }

    private void checkLowTickets() {
        List<Travel> lowTicketTravels = travelDAO.getTravelsWithLowTickets(4);
        if (!lowTicketTravels.isEmpty()) {
            StringBuilder message = new StringBuilder("The following routes have less than 4 tickets left:\n");
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
