package restaurant.db;


import restaurant.model.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationsDAO extends BaseDAO {

    // Insert a new reservation into the database
    public void addReservation(Reservation reservation) throws SQLException {
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
    public static void updateReservation(Reservation reservation) throws SQLException {
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
    public static Reservation getReservationByID(int reservationID) throws SQLException {
        String query = "SELECT * FROM Reservations WHERE ReservationID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, reservationID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                );
            }
        }
        return null;
    }
    
    

    // Get all reservations for a specific date
    public List<Reservation> getReservationsByDate(LocalDate date) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations WHERE DATE(ReservationTime) = ?";  // Using SQL DATE() function to compare only the date part
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(date));  // Convert LocalDate to SQL Date
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        }
        return reservations;
    }
    

    
    public List<Reservation> getReservationsByCustomerId(int customerId) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations WHERE CustomerID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        }
        return reservations;
    }
    
    // Get all reservations
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservations";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("ReservationTime").toLocalDateTime()  // Convert Timestamp to LocalDateTime
                ));
            }
        }
        return reservations;
    }
    
 // Delete all reservations from the database
    public void deleteAllReservations() throws SQLException {
        String query = "DELETE FROM Reservations";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " reservations.");
        }
    }
    

}
