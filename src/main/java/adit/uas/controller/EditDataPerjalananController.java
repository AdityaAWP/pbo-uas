package adit.uas.controller;

import adit.uas.model.Travel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDataPerjalananController {
    @FXML
    private TextField originField;
    @FXML
    private TextField destinationField;
    @FXML
    private TextField scheduleField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField jumlahTiketField;

    private Stage dialogStage;
    private Travel travel;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
        originField.setText(travel.getOrigin());
        destinationField.setText(travel.getDestination());
        scheduleField.setText(travel.getSchedule());
        priceField.setText(Double.toString(travel.getPrice()));
        jumlahTiketField.setText(Integer.toString(travel.getJumlahTiket())); // Initialize jumlahTiket
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        travel.setOrigin(originField.getText());
        travel.setDestination(destinationField.getText());
        travel.setSchedule(scheduleField.getText());
        travel.setPrice(Double.parseDouble(priceField.getText()));
        travel.setJumlahTiket(Integer.parseInt(jumlahTiketField.getText())); // Update jumlahTiket

        saveClicked = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
