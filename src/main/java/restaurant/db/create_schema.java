package restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class create_schema extends BaseDAO {
    public static void main(String[] args) {
        // SQL queries to create tables
        String createTablesSQL1 = "CREATE TABLE IF NOT EXISTS Tables (" +
                "TableID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Capacity INT NOT NULL, " +
                "Status ENUM('Available', 'Reserved', 'Occupied') NOT NULL" +
                ");";

        String createTablesSQL2 = "CREATE TABLE IF NOT EXISTS Customers (" +
                "CustomerID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(100) NOT NULL, " +
                "ContactInfo VARCHAR(255) NOT NULL" +
                ");";

        String createTablesSQL3 = "CREATE TABLE IF NOT EXISTS Reservations (" +
                "ReservationID INT AUTO_INCREMENT PRIMARY KEY, " +
                "CustomerID INT NOT NULL, " +
                "TableID INT NOT NULL, " +
                "ReservationTime DATETIME NOT NULL, " +
                "FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID), " +
                "FOREIGN KEY (TableID) REFERENCES Tables(TableID)" +
                ");";

        String createTablesSQL4 = "CREATE TABLE IF NOT EXISTS Orders (" +
                "OrderID INT AUTO_INCREMENT PRIMARY KEY, " +
                "CustomerID INT NOT NULL, " +
                "TableID INT NOT NULL, " +
                "OrderTime DATETIME NOT NULL, " +
                "FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID), " +
                "FOREIGN KEY (TableID) REFERENCES Tables(TableID)" +
                ");";

        String createTablesSQL5 = "CREATE TABLE IF NOT EXISTS MenuItems (" +
                "ItemID INT AUTO_INCREMENT PRIMARY KEY, " +
                "Name VARCHAR(100) NOT NULL, " +
                "Price DECIMAL(10, 2) NOT NULL, " +
                "Category VARCHAR(50) NOT NULL" +
                ");";

        String createTablesSQL6 = "CREATE TABLE IF NOT EXISTS OrderDetails (" +
                "OrderDetailID INT AUTO_INCREMENT PRIMARY KEY, " +
                "OrderID INT NOT NULL, " +
                "ItemID INT NOT NULL, " +
                "Quantity INT NOT NULL, " +
                "FOREIGN KEY (OrderID) REFERENCES Orders(OrderID), " +
                "FOREIGN KEY (ItemID) REFERENCES MenuItems(ItemID)" +
                ");";

        // Create connection to the database
        try {
            // Optional: Load MySQL JDBC driver (not required for JDBC 4.0 and above)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create a connection to the database
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Connection successful!");

                // Create a Statement to execute SQL queries
                try (Statement stmt = conn.createStatement()) {
                    // Execute each SQL command to create tables if they do not exist
                    stmt.executeUpdate(createTablesSQL1);
                    stmt.executeUpdate(createTablesSQL2);
                    stmt.executeUpdate(createTablesSQL3);
                    stmt.executeUpdate(createTablesSQL4);
                    stmt.executeUpdate(createTablesSQL5);
                    stmt.executeUpdate(createTablesSQL6);

                    System.out.println("Tables created or already exist.");
                } catch (SQLException e) {
                    System.out.println("Error executing SQL commands.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found!");
            e.printStackTrace();
        }
    }
}
