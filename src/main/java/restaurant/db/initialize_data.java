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
        // DAO instances
        CustomerDAO customerDAO = new CustomerDAO();
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        OrderDAO orderDAO = new OrderDAO();
        OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
        ReservationsDAO reservationDAO = new ReservationsDAO();
        TableDAO tableDAO = new TableDAO();
        
        // Clear out old data
        reservationDAO.deleteAllReservations();
        orderDetailsDAO.deleteAllOrderDetails();
        orderDAO.deleteAllOrders();
        customerDAO.deleteAllCustomers();
        menuItemDAO.deleteAllMenuItems();
        tableDAO.deleteAllTables();
        // Deleting in reverse order so no FK errors lol

        System.out.println("Deleted everything. Ready for fresh start.");

        // Step 1: Create tables (parent table)
        List<Table> tables = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            int capacity = (i % 5) + 2;  // Random capacity 2-6
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


        // Step 2: Add customers (this is important for orders/reservations)
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

        // Step 3: Menu items (for orders)
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
        
        for (MenuItem menuItem : menuItems) {
            menuItemDAO.addMenuItem(menuItem);
        }

        // Step 4: Create orders
        customers = customerDAO.getAllCustomers();
        tables = tableDAO.getAllTables();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            for (int j = 0; j < 2; j++) {  // 2 orders per customer
                LocalDateTime orderTime = LocalDateTime.of(2024, 12, j + 1, 12, 0, 0, 0);
                orders.add(new Order(0, customers.get(i).getCustomerId(), tables.get(i % tables.size()).getTableID(), orderTime));
            }
        }

        for (Order order : orders) {
            orderDAO.addOrder(order); 
        }
        orders = orderDAO.getAllOrders();
        for (Order order : orders) {
            System.out.println("Order with OrderID: " + order.getOrderID());
        }

        // Step 5: Add order details (items for orders)
        menuItems = menuItemDAO.getAllMenuItems();
        orders = orderDAO.getAllOrders();
        List<OrderDetail> orderDetails = new ArrayList<>(); 

        for (int i = 0; i < orders.size(); i++) {
            int orderID = orders.get(i).getOrderID();
            System.out.println("Adding OrderDetails for OrderID: " + orderID);
            
            for (int j = 0; j < 3; j++) {  // 3 items per order
                MenuItem item = menuItems.get((i + j) % menuItems.size());  
                OrderDetail orderDetail = new OrderDetail(0, orderID, item.getItemID(), 2);  
                orderDetails.add(orderDetail);
            }
        }

        for (OrderDetail detail : orderDetails) {
            orderDetailsDAO.addOrderDetail(detail);  
            System.out.println("Inserted OrderDetail with ItemID: " + detail.getItemID());
        }

        // Step 6: Add reservations (for customers and tables)
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < 15; i++) {  
            LocalDateTime reservationTime = LocalDateTime.of(2024, 12, i + 2, 18, 0, 0, 0);  
            reservations.add(new Reservation(0, customers.get(i).getCustomerId(), tables.get(i).getTableID(), reservationTime)); 
        }
        for (Reservation reservation : reservations) {
            reservationDAO.addReservation(reservation);
        }

        System.out.println("Everything's done! Data is initialized.");
    }
}
