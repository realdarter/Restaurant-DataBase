package restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
    // Database credentials
    protected static final String URL = "jdbc:mysql://localhost:3306/mydb";
    protected static final String USER = "root";
    protected static final String PASSWORD = "student";
    
    // Method to get a database connection
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
