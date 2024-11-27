package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableDAO extends BaseDAO {
    
    // Insert a table into the database
    public void addTable(Tables table) throws SQLException {
        String query = "INSERT INTO Tables (Capacity, Status) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, table.getCapacity());
            stmt.setString(2, table.getStatus());
            stmt.executeUpdate();
        }
    }
    
    // Update table information
    public void updateTable(Tables table) throws SQLException {
        String query = "UPDATE Tables SET Capacity = ?, Status = ? WHERE TableID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, table.getCapacity());
            stmt.setString(2, table.getStatus());
            stmt.setInt(3, table.getTableID());
            stmt.executeUpdate();
        }
    }

    // Delete a table by ID
    public void deleteTable(int tableId) throws SQLException {
        String query = "DELETE FROM Tables WHERE TableID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tableId);
            stmt.executeUpdate();
        }
    }

    // Retrieve a table by ID
    public Tables getTableById(int tableId) throws SQLException {
        String query = "SELECT * FROM Tables WHERE TableID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tableId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Tables(
                        rs.getInt("TableID"),
                        rs.getInt("Capacity"),
                        rs.getString("Status")
                );
            }
        }
        return null;
    }

    // Get all tables
    public List<Tables> getAllTables() throws SQLException {
        List<Tables> tables = new ArrayList<>();
        String query = "SELECT * FROM Tables";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                tables.add(new Tables(
                        rs.getInt("TableID"),
                        rs.getInt("Capacity"),
                        rs.getString("Status")
                ));
            }
        }
        return tables;
    }
}
