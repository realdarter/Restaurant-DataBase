package restaurant.db;

import restaurant.model.Order;
import restaurant.model.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDAO extends BaseDAO {

    // Insert a new order detail into the database
    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        String query = "INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) { // Use Statement.RETURN_GENERATED_KEYS to retrieve auto-generated ID
            stmt.setInt(1, orderDetail.getOrderID());
            stmt.setInt(2, orderDetail.getItemID());
            stmt.setInt(3, orderDetail.getQuantity());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderDetail.setOrderDetailID(generatedKeys.getInt(1)); // Retrieve the generated ID
                    }
                }
            }
        }
    }


    // Update an existing order detail
    public void updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        String query = "UPDATE OrderDetails SET ItemID = ?, Quantity = ? WHERE OrderDetailID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderDetail.getItemID());
            stmt.setInt(2, orderDetail.getQuantity());
            stmt.setInt(3, orderDetail.getOrderDetailID()); // Use OrderDetailID as the identifier
            stmt.executeUpdate();
        }
    }

    // Delete an order detail by OrderDetailID
    public void deleteOrderDetail(int orderDetailID) throws SQLException {
        String query = "DELETE FROM OrderDetails WHERE OrderDetailID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderDetailID); // Use OrderDetailID to delete a specific order detail
            stmt.executeUpdate();
        }
    }

    // Retrieve order details by OrderID
    public List<OrderDetail> getOrderDetailsByOrderId(int orderID) throws SQLException {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orderDetailsList.add(new OrderDetail(
                        rs.getInt("OrderDetailID"),  // Retrieve the generated OrderDetailID
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity")
                ));
            }
        }
        return orderDetailsList;
    }

    // Retrieve all order details
    public List<OrderDetail> getAllOrderDetails() throws SQLException {
        List<OrderDetail> orderDetailsList = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orderDetailsList.add(new OrderDetail(
                        rs.getInt("OrderDetailID"),  // Retrieve the generated OrderDetailID
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity")
                ));
            }
        }
        return orderDetailsList;
    }

    public void deleteAllOrderDetails() throws SQLException {
        String query = "DELETE FROM OrderDetails";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " order details.");
        }
    }
}