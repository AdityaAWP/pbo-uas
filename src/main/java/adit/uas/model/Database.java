package adit.uas.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:travel.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS travels (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "origin TEXT NOT NULL," +
                "destination TEXT NOT NULL," +
                "schedule TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "jumlahTiket INTEGER NOT NULL);"; // New field

        String createBookings = "CREATE TABLE IF NOT EXISTS bookings (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "customerName TEXT NOT NULL," +
                "travelId INTEGER NOT NULL," +
                "ticketCount INTEGER NOT NULL," +
                "totalCost REAL NOT NULL," +
                "FOREIGN KEY(travelId) REFERENCES travels(id));";
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(sql);
            stmt.execute(createBookings);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
