package restaurant.db;

import restaurant.model.Order;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO {

    // Insert a new order into the database
	// Insert a new order into the database
	public void addOrder(Order order) throws SQLException {
	    String query = "INSERT INTO Orders (CustomerID, TableID, OrderTime) VALUES (?, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {  // Use Statement.RETURN_GENERATED_KEYS
	        stmt.setInt(1, order.getCustomerID());
	        stmt.setInt(2, order.getTableID());
	        stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderTime())); // Convert LocalDateTime to Timestamp
	        int affectedRows = stmt.executeUpdate();

	        // If the insert was successful, retrieve the generated OrderID
	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    order.setOrderID(generatedKeys.getInt(1)); 
	                }
	            }
	        }
	    }
	}


    // Update an existing order
    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE Orders SET CustomerID = ?, TableID = ?, OrderTime = ? WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getCustomerID());
            stmt.setInt(2, order.getTableID());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderTime())); // Convert LocalDateTime to Timestamp
            stmt.setInt(4, order.getOrderID());
            stmt.executeUpdate();
        }
    }

    // Delete an order by OrderID
    public void deleteOrder(int orderID) throws SQLException {
        String query = "DELETE FROM Orders WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        }
    }

    // Retrieve an order by OrderID
    public Order getOrderById(int orderID) throws SQLException {
        String query = "SELECT * FROM Orders WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("OrderTime").toLocalDateTime() // Convert Timestamp to LocalDateTime
                );
            }
        }
        return null;
    }

    // Retrieve all orders
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("OrderTime").toLocalDateTime() // Convert Timestamp to LocalDateTime
                ));
            }
        }
        return orders;
    }
    
 // Delete all orders from the database
    public void deleteAllOrders() throws SQLException {
        String query = "DELETE FROM Orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " orders.");
        }
    }

}
