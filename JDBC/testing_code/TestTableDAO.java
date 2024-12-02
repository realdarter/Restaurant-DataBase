package database;

import java.sql.SQLException;
import java.util.List;

public class TestTableDAO {
    public static void main(String[] args) {
        TableDAO tableDAO = new TableDAO();

        try {
            // Add a new table
            Tables newTable = new Tables(0, 4, "Available");  // Capacity = 4, Status = "Available"
            tableDAO.addTable(newTable);
            System.out.println("New table added.");

            // Retrieve all tables
            List<Tables> tables = tableDAO.getAllTables();
            System.out.println("All tables:");
            for (Tables t : tables) {
                System.out.println(t);
            }

            // Update an existing table (Assume the TableID is 1)
            Tables updatedTable = new Tables(1, 6, "Occupied");  // Update Capacity to 6, Status to "Occupied"
            tableDAO.updateTable(updatedTable);
            System.out.println("Table updated.");

            // Retrieve the updated table
            Tables fetchedTable = tableDAO.getTableById(1);
            System.out.println("Updated table for TableID 1:");
            System.out.println(fetchedTable);

            // Delete a table (Assume TableID = 1)
            tableDAO.deleteTable(1);
            System.out.println("Table deleted.");

            // Retrieve all tables after deletion
            tables = tableDAO.getAllTables();
            System.out.println("All tables after deletion:");
            for (Tables t : tables) {
                System.out.println(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
