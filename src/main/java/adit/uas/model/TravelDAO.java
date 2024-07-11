package adit.uas.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TravelDAO {
    private Connection connection;

    public TravelDAO() {
        connection = Database.getConnection();
    }

    public void addTravel(Travel travel) {
        String sql = "INSERT INTO travels (origin, destination, schedule, price, jumlahTiket) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, travel.getOrigin());
            pstmt.setString(2, travel.getDestination());
            pstmt.setString(3, travel.getSchedule());
            pstmt.setDouble(4, travel.getPrice());
            pstmt.setInt(5, travel.getJumlahTiket());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTravel(Travel travel) {
        String sql = "UPDATE travels SET origin = ?, destination = ?, schedule = ?, price = ?, jumlahTiket = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, travel.getOrigin());
            pstmt.setString(2, travel.getDestination());
            pstmt.setString(3, travel.getSchedule());
            pstmt.setDouble(4, travel.getPrice());
            pstmt.setInt(5, travel.getJumlahTiket());
            pstmt.setInt(6, travel.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTravel(int id) {
        String sql = "DELETE FROM travels WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Travel getTravelById(int id) {
        Travel travel = null;
        String sql = "SELECT * FROM travels WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                travel = new Travel(
                        rs.getInt("id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("schedule"),
                        rs.getDouble("price"),
                        rs.getInt("jumlahTiket"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travel;
    }

    public List<Travel> getAllTravels() {
        List<Travel> travels = new ArrayList<>();
        String sql = "SELECT * FROM travels";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Travel travel = new Travel(
                        rs.getInt("id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("schedule"),
                        rs.getDouble("price"),
                        rs.getInt("jumlahTiket"));
                travels.add(travel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travels;
    }

    public List<Travel> searchTravels(String origin, String destination) {
        List<Travel> travels = new ArrayList<>();
        String sql = "SELECT * FROM travels WHERE origin LIKE ? AND destination LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, "%" + origin + "%");
            pstmt.setString(2, "%" + destination + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Travel travel = new Travel(
                        rs.getInt("id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("schedule"),
                        rs.getDouble("price"),
                        rs.getInt("jumlahTiket"));
                travels.add(travel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travels;
    }

    public void updateJumlahTiket(int travelId, int jumlahTiket) {
        String sql = "UPDATE travels SET jumlahTiket = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, jumlahTiket);
            pstmt.setInt(2, travelId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Travel> getTravelsWithLowTickets(int threshold) {
        List<Travel> travels = new ArrayList<>();
        String sql = "SELECT * FROM travels WHERE jumlahTiket < ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, threshold);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Travel travel = new Travel(
                        rs.getInt("id"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getString("schedule"),
                        rs.getDouble("price"),
                        rs.getInt("jumlahTiket"));
                travels.add(travel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return travels;
    }
}
