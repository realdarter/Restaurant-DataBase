package database;

import java.sql.SQLException;
import java.util.List;

public class TestOrderDetailsDAO {
    public static void main(String[] args) {
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

        try {
            // Add a new order detail
            OrderDetails orderDetail = new OrderDetails(1, 101, 2);  // Example orderID, itemID, quantity
            orderDetailsDAO.addOrderDetail(orderDetail);

            // Get all order details for a specific order (example OrderID = 1)
            List<OrderDetails> orderDetailsList = orderDetailsDAO.getOrderDetailsByOrderId(1);
            System.out.println("Order details for OrderID 1:");
            for (OrderDetails detail : orderDetailsList) {
                System.out.println(detail);
            }

            // Update an order detail (for OrderID = 1)
            OrderDetails updatedOrderDetail = new OrderDetails(1, 102, 3);  // Update ItemID and Quantity
            orderDetailsDAO.updateOrderDetail(updatedOrderDetail);

            // Get the updated order details
            orderDetailsList = orderDetailsDAO.getOrderDetailsByOrderId(1);
            System.out.println("Updated order details for OrderID 1:");
            for (OrderDetails detail : orderDetailsList) {
                System.out.println(detail);
            }

            // Delete an order detail for a specific OrderID
            orderDetailsDAO.deleteOrderDetail(1);

            // Get all order details after deletion
            orderDetailsList = orderDetailsDAO.getAllOrderDetails();
            System.out.println("All order details after deletion:");
            for (OrderDetails detail : orderDetailsList) {
                System.out.println(detail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
