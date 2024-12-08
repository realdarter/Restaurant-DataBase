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
        
        // Step 1: Deleting all data before initialization
        reservationDAO.deleteAllReservations();
        orderDetailsDAO.deleteAllOrderDetails();
        orderDAO.deleteAllOrders();
        customerDAO.deleteAllCustomers();
        menuItemDAO.deleteAllMenuItems();
        tableDAO.deleteAllTables();
        // ordered in this way to remove data backwards to prevent database rejections.

        
        System.out.println("All data deleted successfully.");

        // Step 1: Initialize Tables (parent table)
        List<Table> tables = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            int capacity = (i % 5) + 2;  // Capacity ranges from 2 to 6
            String status = (i % 3 == 0) ? "Reserved" : "Available";  
            tables.add(new Table(i, capacity, status));
        }
        for (Table table : tables) {
            tableDAO.addTable(table);
        }
        tables = tableDAO.getAllTables();
        for (Table table : tables) {
        	System.out.println("Table ID: " + table.getTableID() + ", Status: " + table.getStatus());
        }


        // Step 2: Initialize Customers (parent table for Orders and Reservations)
        List<Customer> customers = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String username = "user" + i;
            String password = "password" + i; 
            
            customers.add(new Customer(0, "Customer" + i, "Contact" + i + "@mail.com", username, password));
        }

        for (Customer customer : customers) {
            customerDAO.addCustomer(customer);
        }
        
        customers = customerDAO.getAllCustomers();
        for (Customer customer : customers) {
        	System.out.println(customer.toString());
        }

        // Step 3: Initialize Menu Items (for Order Details)
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(0, "Cheeseburger", 9.99f, "Burgers"));
        menuItems.add(new MenuItem(0, "Veggie Burger", 8.49f, "Burgers"));
        menuItems.add(new MenuItem(0, "Classic Caesar Salad", 7.99f, "Salads"));
        menuItems.add(new MenuItem(0, "Greek Salad", 8.99f, "Salads"));
        menuItems.add(new MenuItem(0, "Margherita Pizza", 12.99f, "Pizzas"));
        menuItems.add(new MenuItem(0, "Pepperoni Pizza", 13.99f, "Pizzas"));
        menuItems.add(new MenuItem(0, "BBQ Chicken Pizza", 14.49f, "Pizzas"));
        menuItems.add(new MenuItem(0, "Chicken Tenders", 6.99f, "Appetizers"));
        menuItems.add(new MenuItem(0, "Mozzarella Sticks", 7.49f, "Appetizers"));
        menuItems.add(new MenuItem(0, "Garlic Bread", 4.99f, "Appetizers"));
        menuItems.add(new MenuItem(0, "Spaghetti Bolognese", 14.99f, "Pasta"));
        menuItems.add(new MenuItem(0, "Fettuccine Alfredo", 15.49f, "Pasta"));
        menuItems.add(new MenuItem(0, "Lasagna", 16.99f, "Pasta"));
        menuItems.add(new MenuItem(0, "Grilled Salmon", 18.99f, "Entrees"));
        menuItems.add(new MenuItem(0, "Ribeye Steak", 22.99f, "Entrees"));
        menuItems.add(new MenuItem(0, "Gorilla Chinese Food", 29.99f, "Specialty"));
        menuItems.add(new MenuItem(0, "Dog Meat (Traditional Chinese)", 50.00f, "Specialty"));
        menuItems.add(new MenuItem(0, "Fried Tarantulas", 12.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Century Eggs", 5.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Stinky Tofu", 8.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Cat Meat Stew", 35.00f, "Exotic"));
        menuItems.add(new MenuItem(0, "Deep Fried Snakes", 22.00f, "Exotic"));
        menuItems.add(new MenuItem(0, "Roasted Bat Wings", 18.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Boiled Dog Tail", 45.00f, "Exotic"));
        menuItems.add(new MenuItem(0, "Fried Scorpions", 10.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Chocolate-Covered Grasshoppers", 5.49f, "Exotic"));
        menuItems.add(new MenuItem(0, "Caviar of Worms", 25.00f, "Exotic"));
        menuItems.add(new MenuItem(0, "Camel Hump Steaks", 50.00f, "Exotic"));
        menuItems.add(new MenuItem(0, "Tuna Eye Soup", 8.99f, "Exotic"));
        menuItems.add(new MenuItem(0, "Cricket Flour Bread", 4.99f, "Exotic"));
        
        // Add each item to the menu
        for (MenuItem menuItem : menuItems) {
            menuItemDAO.addMenuItem(menuItem);
        }

     // Step 4: Initialize Orders (multiple orders per customer)
        customers = customerDAO.getAllCustomers();
        tables = tableDAO.getAllTables();
        List<Order> orders = new ArrayList<>();

        // Create multiple orders for each customer
        for (int i = 0; i < customers.size(); i++) {
            // Each customer gets 2 orders (this can be customized)
            for (int j = 0; j < 2; j++) {
                LocalDateTime orderTime = LocalDateTime.of(2024, 12, j + 1, 12, 0, 0, 0);  // Create orders on different days
                orders.add(new Order(0, customers.get(i).getCustomerId(), tables.get(i % tables.size()).getTableID(), orderTime));
            }
        }

        // Insert Orders and capture generated OrderIDs
        for (Order order : orders) {
            orderDAO.addOrder(order); 
        }
        orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            System.out.println("Order in system with OrderID: " + order.getOrderID());
        }

        // Step 5: Initialize Order Details (multiple items per order)
        menuItems = menuItemDAO.getAllMenuItems();
        orders = orderDAO.getAllOrders();
        List<OrderDetail> orderDetails = new ArrayList<>();  // Empty list to hold order details

        // Populate the orderDetails list by creating multiple OrderDetail instances for each order
        for (int i = 0; i < orders.size(); i++) {
            int orderID = orders.get(i).getOrderID();  // Assigning the orderID for the current Order
            System.out.println("Adding OrderDetails for OrderID: " + orderID);
            
            // For each order, add multiple order details (items)
            for (int j = 0; j < 3; j++) {  // Each order gets 3 items (this can be customized)
                MenuItem item = menuItems.get((i + j) % menuItems.size());  // Get a menu item in a cyclic manner
                OrderDetail orderDetail = new OrderDetail(0, orderID, item.getItemID(), 2);  // Quantity set to 2 for each item
                orderDetails.add(orderDetail);
            }
        }

        // Insert the populated OrderDetails into the database
        for (OrderDetail detail : orderDetails) {
            orderDetailsDAO.addOrderDetail(detail);  // Adding the order detail to the database
            System.out.println("Inserted OrderDetail with ItemID: " + detail.getItemID() + ", OrderDetailID: " + detail.getOrderDetailID());
        }
        
        
        // Step 5: Initialize Order Details (references Orders and MenuItems)
        menuItems = menuItemDAO.getAllMenuItems();
        orders = orderDAO.getAllOrders();
        orderDetails = new ArrayList<>();  // Empty list to hold order details

        // Populate the orderDetails list by creating OrderDetail instances for each menu item
        for (int i = 0; i < menuItems.size(); i++) {
            int orderID = orders.get(i % orders.size()).getOrderID();  // Assigning the orderID for the current Order
            System.out.println("Adding OrderDetail for MenuItem ID: " + menuItems.get(i).getItemID() + " for OrderID: " + orderID);
            
            // Create a new OrderDetail instance
            OrderDetail orderDetail = new OrderDetail(0, orderID, menuItems.get(i).getItemID(), 2);  // (ID, OrderID, ItemID, Quantity)
            
            // Add the OrderDetail to the list
            orderDetails.add(orderDetail);
        }

        // Insert the populated OrderDetails into the database
        for (OrderDetail detail : orderDetails) {
            orderDetailsDAO.addOrderDetail(detail);  // Adding the order detail to the database
            System.out.println("Inserted OrderDetail with ItemID: " + detail.getItemID() + ", OrderDetailID: " + detail.getOrderDetailID());
        }

        // Step 6: Initialize Reservations (references Customers and Tables)
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < 15; i++) {  
            LocalDateTime reservationTime = LocalDateTime.of(2024, 12, i + 2, 18, 0, 0, 0);  // Dec 2nd to Dec 16th, 6:00 PM
            reservations.add(new Reservation(0, customers.get(i).getCustomerId(), tables.get(i).getTableID(), reservationTime)); 
            // ReservationID, CustomerID, TableID, ReservationTime
        }
        for (Reservation reservation : reservations) {
            reservationDAO.addReservation(reservation);
        }

        System.out.println("All data initialized successfully.");
    }
}
