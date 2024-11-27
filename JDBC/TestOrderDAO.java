package database;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TestOrderDAO {
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();

        try {
            // Add a new order
            Order order = new Order(0, 1, 1, LocalDateTime.now());  // Example CustomerID = 1, TableID = 1, current time
            orderDAO.addOrder(order);

            // Retrieve all orders
            List<Order> orders = orderDAO.getAllOrders();
            System.out.println("All orders:");
            for (Order o : orders) {
                System.out.println(o);
            }

            // Update an existing order (Assume the OrderID is 1)
            Order updatedOrder = new Order(1, 2, 2, LocalDateTime.now().plusHours(1));  // Update CustomerID and TableID
            orderDAO.updateOrder(updatedOrder);

            // Retrieve the updated order
            Order fetchedOrder = orderDAO.getOrderById(1);
            System.out.println("Updated order for OrderID 1:");
            System.out.println(fetchedOrder);

            // Delete an order (Assume OrderID = 1)
            orderDAO.deleteOrder(1);

            // Retrieve all orders after deletion
            orders = orderDAO.getAllOrders();
            System.out.println("All orders after deletion:");
            for (Order o : orders) {
                System.out.println(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
