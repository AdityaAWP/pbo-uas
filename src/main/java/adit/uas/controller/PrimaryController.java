package adit.uas.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void handleDataPerjalanan() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/adit/uas/DataPerjalananView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBooking() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/adit/uas/BookingView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTicketManagement() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/adit/uas/TicketManagementView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
