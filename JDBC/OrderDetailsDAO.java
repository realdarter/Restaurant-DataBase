package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO extends BaseDAO {

    // Insert a new order detail into the database
    public void addOrderDetail(OrderDetails orderDetail) throws SQLException {
        String query = "INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getItemID());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.executeUpdate();
        }
    }

    // Update an existing order detail
    public void updateOrderDetail(OrderDetails orderDetail) throws SQLException {
        String query = "UPDATE OrderDetails SET ItemID = ?, Quantity = ? WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getItemID());
            stmt.setInt(2, orderDetail.getQuantity());
            stmt.setInt(3, orderDetail.getOrderID());
            stmt.executeUpdate();
        }
    }

    // Delete an order detail by OrderID
    public void deleteOrderDetail(int orderID) throws SQLException {
        String query = "DELETE FROM OrderDetails WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            stmt.executeUpdate();
        }
    }

    // Retrieve order details by OrderID
    public List<OrderDetails> getOrderDetailsByOrderId(int orderID) throws SQLException {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderDetailsList.add(new OrderDetails(
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity")
                ));
            }
        }
        return orderDetailsList;
    }

    // Retrieve all order details
    public List<OrderDetails> getAllOrderDetails() throws SQLException {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orderDetailsList.add(new OrderDetails(
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity")
                ));
            }
        }
        return orderDetailsList;
    }
}
