package adit.uas.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection;

    public BookingDAO() {
        connection = Database.getConnection();
    }

    public void addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (customerName, travelId, ticketCount, totalCost) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, booking.getCustomerName());
            pstmt.setInt(2, booking.getTravelId());
            pstmt.setInt(3, booking.getTicketCount());
            pstmt.setDouble(4, booking.getTotalCost());
            pstmt.executeUpdate();

            // Reduce the number of available tickets
            TravelDAO travelDAO = new TravelDAO();
            Travel travel = travelDAO.getTravelById(booking.getTravelId());
            int newJumlahTiket = travel.getJumlahTiket() - booking.getTicketCount();
            travelDAO.updateJumlahTiket(booking.getTravelId(), newJumlahTiket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.id, b.customerName, b.travelId, t.origin, t.destination, t.schedule, b.ticketCount, b.totalCost "
                + "FROM bookings b JOIN travels t ON b.travelId = t.id";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("id"),
                        rs.getString("customerName"),
                        rs.getInt("travelId"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("schedule"),
                        rs.getInt("ticketCount"),
                        rs.getDouble("totalCost"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
