package restaurant.db;

import restaurant.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO {
    
    // Insert a customer into the database
	public void addCustomer(Customer customer) throws SQLException {
	    // First, check if the username already exists
	    String checkQuery = "SELECT COUNT(*) FROM Customers WHERE Username = ?";
	    try (Connection conn = getConnection();
	         PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
	        
	        checkStmt.setString(1, customer.getUsername());
	        ResultSet rs = checkStmt.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        
	        if (count > 0) {
	            throw new SQLException("Username '" + customer.getUsername() + "' already exists.");
	        }
	    }
	    String query = "INSERT INTO Customers (Name, ContactInfo, Username, Password) VALUES (?, ?, ?, ?)";
	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setString(1, customer.getName());
	        stmt.setString(2, customer.getContactInfo());
	        stmt.setString(3, customer.getUsername());
	        stmt.setString(4, customer.getPassword());  // Password (consider hashing for more security)
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
    
 // Get CustomerID by Username
    public int getCustomerIdByUsername(String username) throws SQLException {
        String query = "SELECT CustomerID FROM Customers WHERE Username = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CustomerID");  // Return the CustomerID
            }
        }
        return -1;  // Return -1 if no customer with the given username is found
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
    
    // Verify customer credentials (username and password)
    public boolean verifyCustomerCredentials(String username, String password) throws SQLException {
        String query = "SELECT * FROM Customers WHERE Username = ? AND Password = ?";
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);  // Note: Consider hashing the password for security
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // If a row is returned, credentials are valid
        }
    }
    
    public boolean doesUsernameExist(String username) throws SQLException {
        String query = "SELECT 1 FROM Customers WHERE Username = ? LIMIT 1";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();  // Returns true if a row is found
            }
        }
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
