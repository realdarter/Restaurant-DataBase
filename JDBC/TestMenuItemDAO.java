package database;

import java.sql.SQLException;
import java.util.List;

public class TestMenuItemDAO {
    public static void main(String[] args) {
        MenuItemDAO menuItemDAO = new MenuItemDAO();

        try {
            // Add a new menu item
            MenuItem newItem = new MenuItem(0, "Cheeseburger", 8.99f, "Main");
            menuItemDAO.addMenuItem(newItem);

            // Get all menu items
            List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();
            System.out.println("All menu items:");
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }

            // Update a menu item (assuming ItemID = 1)
            MenuItem updatedItem = new MenuItem(1, "Cheeseburger Deluxe", 10.99f, "Main");
            menuItemDAO.updateMenuItem(updatedItem);

            // Get the updated menu item by ID
            MenuItem itemById = menuItemDAO.getMenuItemById(1);
            System.out.println("Updated menu item:");
            System.out.println(itemById);

            // Delete a menu item (assuming ItemID = 1)
            menuItemDAO.deleteMenuItem(1);

            // Verify deletion
            System.out.println("All menu items after deletion:");
            menuItems = menuItemDAO.getAllMenuItems();
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
