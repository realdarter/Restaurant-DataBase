package restaurant.model;

import restaurant.db.MenuItemDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Order {
    private int orderID;
    private int customerID;
    private int tableID;
    private LocalDateTime orderTime;
    private List<OrderDetail> orderDetails;
    
    // Instance of MenuItemDAO to fetch MenuItem by itemID
    private MenuItemDAO menuItemDAO;

    // Constructor
    public Order(int orderID, int customerID, int tableID, LocalDateTime orderTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.tableID = tableID;
        this.orderTime = orderTime;
        this.orderDetails = new ArrayList<>();
        this.menuItemDAO = new MenuItemDAO(); // Instantiate the DAO
    }

    // Constructor with orderDetails
    public Order(int orderID, int customerID, int tableID, LocalDateTime orderTime, List<OrderDetail> orderDetails) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.tableID = tableID;
        this.orderTime = orderTime;
        this.orderDetails = orderDetails;
        this.menuItemDAO = new MenuItemDAO();
    }

    // Getters and Setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // Add a method to retrieve the names of items in the order
    public List<String> getItems() throws SQLException {
        List<String> itemNames = new ArrayList<>();
        for (OrderDetail detail : orderDetails) {
            // Fetch the MenuItem using itemID from the MenuItemDAO
            MenuItem item = menuItemDAO.getMenuItemById(detail.getItemID());
            if (item != null) {
                itemNames.add(item.getName());  // Add the name of the MenuItem
            }
        }
        return itemNames;
    }
}
