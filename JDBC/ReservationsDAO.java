package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDAO extends BaseDAO {

    // Insert a new reservation into the database
    public void addReservation(Reservations reservation) throws SQLException {
        String query = "INSERT INTO Reservations (CustomerID, TableID, ReservationTime) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservation.getCustomerID());
            stmt.setInt(2, reservation.getTableID());
            stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationTime()));  // Convert LocalDateTime to Timestamp
            stmt.executeUpdate();
        }
    }

    // Update an existing reservation
    public void updateReservation(Reservations reservation) throws SQLException {
        String query = "UPDATE Reservations SET CustomerID = ?, TableID = ?, ReservationTime = ? WHERE ReservationID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservation.getCustomerID());
            stmt.setInt(2, reservation.getTableID());
            stmt.setTimestamp(3, Timestamp.valueOf(reservation.getReservationTime()));  // Convert LocalDateTime to Timestamp
            stmt.setInt(4, reservation.getReservationID());
            stmt.executeUpdate();
        }
    }

    // Delete a reservation by ID
    public void deleteReservation(int reservationID) throws SQLException {
        String query = "DELETE FROM Reservations WHERE ReservationID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationID);
            stmt.executeUpdate();
        }
    }

    // Retrieve a reservation by ID
    public Reservations getReservationById(int reservationID) throws SQLException {
        String query = "SELECT * FROM Reservations WHERE ReservationID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservations(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                );
            }
        }
        return null;
    }

    // Get all reservations
    public List<Reservations> getAllReservations() throws SQLException {
        List<Reservations> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                reservations.add(new Reservations(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        }
        return reservations;
    }
}
