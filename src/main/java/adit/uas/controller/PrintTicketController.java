package adit.uas.controller;

import adit.uas.model.Booking;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PrintTicketController {

    @FXML
    private TextArea ticketTextArea;

    private Booking booking;

    public void setBooking(Booking booking) {
        this.booking = booking;
        displayTicketDetails();
    }

    private void displayTicketDetails() {
        if (booking != null) {
            String ticketDetails = String.format(
                    "Customer Name: %s\nTravel Route: %s to %s\nSchedule: %s\nTicket Count: %d\nTotal Cost: %.2f",
                    booking.getCustomerName(), booking.getTravelOrigin(), booking.getTravelDestination(),
                    booking.getTravelSchedule(), booking.getTicketCount(), booking.getTotalCost());
            ticketTextArea.setText(ticketDetails);
        }
    }
}
