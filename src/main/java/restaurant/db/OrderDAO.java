package restaurant.db;

import restaurant.model.Order;
import restaurant.model.OrderDetail;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends BaseDAO {

    // Insert a new order along with order details into the database
    public void addOrder(Order order, List<OrderDetail> orderDetails) throws SQLException {
        String insertOrderQuery = "INSERT INTO Orders (CustomerID, TableID, OrderTime) VALUES (?, ?, ?)";
        String insertOrderDetailQuery = "INSERT INTO OrderDetails (OrderID, ItemID, Quantity) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertOrderDetailStmt = conn.prepareStatement(insertOrderDetailQuery)) {

            // Insert into Orders table
            insertOrderStmt.setInt(1, order.getCustomerID());
            insertOrderStmt.setInt(2, order.getTableID());
            insertOrderStmt.setTimestamp(3, Timestamp.valueOf(order.getOrderTime())); // Convert LocalDateTime to Timestamp
            insertOrderStmt.executeUpdate();

            // Retrieve the generated OrderID
            int orderId;
            try (ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve OrderID.");
                }
            }

            // Insert order details into OrderDetails table
            for (OrderDetail orderDetail : orderDetails) {
                insertOrderDetailStmt.setInt(1, orderId);
                insertOrderDetailStmt.setInt(2, orderDetail.getItemID());
                insertOrderDetailStmt.setInt(3, orderDetail.getQuantity());
                insertOrderDetailStmt.executeUpdate();
            }
        }
    }
    
    public int addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (CustomerID, TableID, OrderTime) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, order.getCustomerID());
            stmt.setInt(2, order.getTableID());
            stmt.setTimestamp(3, Timestamp.valueOf(order.getOrderTime()));
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderID = generatedKeys.getInt(1);
                        order.setOrderID(orderID); // Set the generated OrderID
                        return orderID; // Return the generated OrderID
                    }
                }
            }
        }
        return -1; // Return -1 if no orderID is generated
    }

    // Update an existing order
    public void updateOrder(Order order, List<OrderDetail> orderDetails) throws SQLException {
        String updateOrderQuery = "UPDATE Orders SET CustomerID = ?, TableID = ?, OrderTime = ? WHERE OrderID = ?";
        String updateOrderDetailQuery = "UPDATE OrderDetails SET ItemID = ?, Quantity = ? WHERE OrderDetailID = ?";

        try (Connection conn = getConnection();
             PreparedStatement updateOrderStmt = conn.prepareStatement(updateOrderQuery);
             PreparedStatement updateOrderDetailStmt = conn.prepareStatement(updateOrderDetailQuery)) {

            // Update the order itself
            updateOrderStmt.setInt(1, order.getCustomerID());
            updateOrderStmt.setInt(2, order.getTableID());
            updateOrderStmt.setTimestamp(3, Timestamp.valueOf(order.getOrderTime())); // Convert LocalDateTime to Timestamp
            updateOrderStmt.setInt(4, order.getOrderID());
            updateOrderStmt.executeUpdate();

            // Update the order details
            for (OrderDetail orderDetail : orderDetails) {
                updateOrderDetailStmt.setInt(1, orderDetail.getItemID());
                updateOrderDetailStmt.setInt(2, orderDetail.getQuantity());
                updateOrderDetailStmt.setInt(3, orderDetail.getOrderDetailID());
                updateOrderDetailStmt.executeUpdate();
            }
        }
    }

    // Delete an order and its details
    public void deleteOrder(int orderID) throws SQLException {
        String deleteOrderDetailsQuery = "DELETE FROM OrderDetails WHERE OrderID = ?";
        String deleteOrderQuery = "DELETE FROM Orders WHERE OrderID = ?";

        try (Connection conn = getConnection();
             PreparedStatement deleteOrderDetailsStmt = conn.prepareStatement(deleteOrderDetailsQuery);
             PreparedStatement deleteOrderStmt = conn.prepareStatement(deleteOrderQuery)) {

            // Delete associated order details
            deleteOrderDetailsStmt.setInt(1, orderID);
            deleteOrderDetailsStmt.executeUpdate();

            // Delete the order itself
            deleteOrderStmt.setInt(1, orderID);
            deleteOrderStmt.executeUpdate();
        }
    }

    // Retrieve an order by OrderID (with order details)
    public Order getOrderById(int orderID) throws SQLException {
        String query = "SELECT * FROM Orders WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("TableID"),
                        rs.getTimestamp("OrderTime").toLocalDateTime() // Convert Timestamp to LocalDateTime
                );
                order.setOrderDetails(getOrderDetails(orderID)); // Fetch related order details
                return order;
            }
        }
        return null;
    }

    // Retrieve all orders for a specific customer (with order details)
    public List<Order> getOrdersByCustomerId(int customerID) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("OrderID");
                int tableID = rs.getInt("TableID");
                LocalDateTime orderTime = rs.getTimestamp("OrderTime").toLocalDateTime();

                Order order = new Order(orderID, customerID, tableID, orderTime);
                order.setOrderDetails(getOrderDetails(orderID));  // Fetch related order details
                orders.add(order);
            }
        }
        return orders;
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

    // Retrieve order details for a specific order
    private List<OrderDetail> getOrderDetails(int orderID) throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("OrderDetailID"),
                        rs.getInt("OrderID"),
                        rs.getInt("ItemID"),
                        rs.getInt("Quantity")
                );
                orderDetails.add(orderDetail);
            }
        }
        return orderDetails;
    }
    
    public void deleteAllOrders() throws SQLException {
        String deleteOrderDetailsQuery = "DELETE FROM OrderDetails";
        String deleteOrdersQuery = "DELETE FROM Orders";

        try (Connection conn = getConnection();
             PreparedStatement deleteOrderDetailsStmt = conn.prepareStatement(deleteOrderDetailsQuery);
             PreparedStatement deleteOrdersStmt = conn.prepareStatement(deleteOrdersQuery)) {

            // Delete all order details
            deleteOrderDetailsStmt.executeUpdate();

            // Delete all orders
            deleteOrdersStmt.executeUpdate();
        }
    }
}
