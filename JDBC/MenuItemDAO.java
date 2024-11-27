package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO extends BaseDAO {

    // Insert a new menu item into the database
    public void addMenuItem(MenuItem menuItem) throws SQLException {
        String query = "INSERT INTO MenuItems (Name, Price, Category) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setFloat(2, menuItem.getPrice());
            stmt.setString(3, menuItem.getCategory());
            stmt.executeUpdate();
        }
    }

    // Update an existing menu item
    public void updateMenuItem(MenuItem menuItem) throws SQLException {
        String query = "UPDATE MenuItems SET Name = ?, Price = ?, Category = ? WHERE ItemID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setFloat(2, menuItem.getPrice());
            stmt.setString(3, menuItem.getCategory());
            stmt.setInt(4, menuItem.getItemID());
            stmt.executeUpdate();
        }
    }

    // Delete a menu item by ID
    public void deleteMenuItem(int itemId) throws SQLException {
        String query = "DELETE FROM MenuItems WHERE ItemID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }

    // Retrieve a menu item by ID
    public MenuItem getMenuItemById(int itemId) throws SQLException {
        String query = "SELECT * FROM MenuItems WHERE ItemID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MenuItem(
                        rs.getInt("ItemID"),
                        rs.getString("Name"),
                        rs.getFloat("Price"),
                        rs.getString("Category")
                );
            }
        }
        return null;
    }

    // Get all menu items
    public List<MenuItem> getAllMenuItems() throws SQLException {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM MenuItems";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                menuItems.add(new MenuItem(
                        rs.getInt("ItemID"),
                        rs.getString("Name"),
                        rs.getFloat("Price"),
                        rs.getString("Category")
                ));
            }
        }
        return menuItems;
    }
}
