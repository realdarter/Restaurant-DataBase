package restaurant.db;

import restaurant.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO {
    
    // Insert a customer into the database
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (Name, ContactInfo, Username, Password) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContactInfo());
            stmt.setString(3, customer.getUsername());  // Include username
            stmt.setString(4, customer.getPassword());  // Password (consider hashing for more security but cuz im lazy nty)
            stmt.executeUpdate();
        }
    }
    
    // Update a customer in the database
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customers SET Name = ?, ContactInfo = ?, Username = ?, Password = ? WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getContactInfo());
            stmt.setString(3, customer.getUsername());  // Update username
            stmt.setString(4, customer.getPassword());  // Update password (hashed ideally)
            stmt.setInt(5, customer.getCustomerId());
            stmt.executeUpdate();
        }
    }
    
    // Delete a customer by CustomerID
    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customers WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            stmt.executeUpdate();
        }
    }

    // Retrieve a customer by CustomerID
    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM Customers WHERE CustomerID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("ContactInfo"),
                        rs.getString("Username"),
                        rs.getString("Password")  // Retrieve password (consider hashing)
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
                        rs.getString("ContactInfo"),
                        rs.getString("Username"),
                        rs.getString("Password")  // Retrieve password (consider hashing for more security but cuz im lazy nty)
                ));
            }
        }
        return customers;
    }
    
    // Delete all customers
    public void deleteAllCustomers() throws SQLException {
        String query = "DELETE FROM Customers";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println("Deleted " + rowsAffected + " customers.");
        }
    }
}
