package restaurant.db;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Customer;
import restaurant.model.MenuItem;
import restaurant.model.Order;
import restaurant.model.OrderDetail;
import restaurant.model.Reservation;
import restaurant.model.Table;

public class initialize_data {

    public static void main(String[] args) throws SQLException {
        // Create DAO instances
        CustomerDAO customerDAO = new CustomerDAO();
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        ReservationsDAO reservationDAO = new ReservationsDAO();
        TableDAO tableDAO = new TableDAO();

        // Step 1: Initialize Tables (parent table)
        List<Table> tables = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            int capacity = (i % 5) + 2;  // Capacity ranges from 2 to 6
            String status = (i % 3 == 0) ? "Reserved" : "Available";  // Every third table is reserved
            tables.add(new Table(i, capacity, status));
        }
        for (Table table : tables) {
            tableDAO.addTable(table);
        }

        // Step 2: Initialize Customers (parent table for Orders and Reservations)
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            customers.add(new Customer(0, "Customer" + i, "Contact" + i + "@mail.com"));
        }
        for (Customer customer : customers) {
            customerDAO.addCustomer(customer);
        }

        // Step 3: Initialize Menu Items (for Order Details)
        List<MenuItem> menuItems = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            menuItems.add(new MenuItem(0, "Item" + i, i * 5.99f, "Category" + (i % 3 + 1)));
        }
        for (MenuItem menuItem : menuItems) {
            menuItemDAO.addMenuItem(menuItem);
        }

        // Step 4: Initialize Orders (references Customers and Tables)
        List<Order> orders = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            LocalDateTime orderTime = LocalDateTime.of(2024, 12, i, 12, 0, 0, 0);
            orders.add(new Order(i, i, i, orderTime)); // OrderID, CustomerID, TableID, OrderTime
        }
        for (Order order : orders) {
            orderDAO.addOrder(order);
        }

        // Step 5: Initialize Order Details (references Orders and MenuItems)
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            orderDetails.add(new OrderDetail(i, i, 2)); // Assume 2 items for simplicity
        }
        for (OrderDetail detail : orderDetails) {
            orderDetailsDAO.addOrderDetail(detail);
        }

        // Step 6: Initialize Reservations (references Customers and Tables)
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            LocalDateTime reservationTime = LocalDateTime.of(2024, 12, i + 1, 18, 0, 0, 0);  // Dec 2nd to Dec 16th, 6:00 PM
            reservations.add(new Reservation(i, i, i, reservationTime)); // ReservationID, CustomerID, TableID, ReservationTime
        }
        for (Reservation reservation : reservations) {
            reservationDAO.addReservation(reservation);
        }

        System.out.println("All data initialized successfully.");
    }
}
