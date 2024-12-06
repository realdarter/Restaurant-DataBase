package restaurant.db;

import restaurant.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO {
    
    // Insert a customer into the database
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (Name, ContactInfo) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContactInfo());
            stmt.executeUpdate();
        }
    }
    
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customers "
        		+ "SET Name = ?, "
        		+ "ContactInfo = ? "
        		+ "WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContactInfo());
            stmt.setInt(3, customer.getCustomerId());
            stmt.executeUpdate();
        }
    }
    
    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customers WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    // Retrieve a customer by ID
    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM Customers "
        		+ "WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("ContactInfo")
                );
            }
        }
        return null;
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("ContactInfo")
                ));
            }
        }
        return customers;
    }
    
    
    public void deleteAllCustomers() throws SQLException {
        String query = "DELETE FROM Customers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " customers.");
        }
    }

    
}
