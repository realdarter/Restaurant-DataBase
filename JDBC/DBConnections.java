import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnections {
    public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "password"; // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
    public static void closeConnection(Connection conn) {
        System.out.println("Hi!!");
    }
}
