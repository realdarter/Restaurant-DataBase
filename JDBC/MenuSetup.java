package database;

import database.MenuItem;
import database.MenuItemDAO;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MenuSetup {
    public static void main(String[] args) throws SQLException {
        MenuItemDAO menuItemDAO = new MenuItemDAO();

        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(0, "Bruschetta", 7.99f, "Appetizers"),
                new MenuItem(0, "Stuffed Mushrooms", 8.99f, "Appetizers"),
                new MenuItem(0, "Grilled Salmon", 18.99f, "Main Courses"),
                new MenuItem(0, "Spaghetti Carbonara", 14.99f, "Main Courses"),
                new MenuItem(0, "Tiramisu", 6.99f, "Desserts"),
                new MenuItem(0, "Chocolate Lava Cake", 7.99f, "Desserts"),
                new MenuItem(0, "House Red Wine", 8.00f, "Beverages"),
                new MenuItem(0, "Lemonade", 3.50f, "Beverages")
        );

        try {
            menuItemDAO.addMenuItemsIfNotExist(menuItems);
            System.out.println("Menu items have been set up.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        List<MenuItem> m = menuItemDAO.getAllMenuItems();
        for(MenuItem x: m) {
        	System.out.println(x.toString());
        }
        
    }
}
