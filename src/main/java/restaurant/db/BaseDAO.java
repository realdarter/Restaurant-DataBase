package restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.util.Enumeration;

public class BaseDAO {
    // Database credentials
    protected static final String URL = "jdbc:mysql://localhost:3306/mydb";
    protected static final String USER = "root";
    protected static final String PASSWORD = "student";
    
    // Method to get a database connection
    protected Connection getConnection() throws SQLException {
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        // Debugging: Print out the drivers registered with DriverManager
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("Registered driver: " + driver.getClass().getName());
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
