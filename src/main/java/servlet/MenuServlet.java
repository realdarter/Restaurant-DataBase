package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import restaurant.db.MenuItemDAO;
import restaurant.model.MenuItem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * MenuServlet stuff
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuItemDAO menuItemDAO;

    /**
     * Constructor, duh
     */
    public MenuServlet() {
        super();
        menuItemDAO = new MenuItemDAO();  // Yeah, making the DAO
    }

    /**
     * Handles the GET request, obviously
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get menu items from the database, if any
            List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();

            if (menuItems == null || menuItems.isEmpty()) {
                System.out.println("No menu items... okay?");
            }

            // Sorting by price or category? If there's a parameter for that, do it
            String sortBy = request.getParameter("sortBy");
            if (sortBy != null) {
                switch (sortBy) {
                    case "price":
                        Collections.sort(menuItems, Comparator.comparingDouble(MenuItem::getPrice));  // Sorting by price, obviously
                        break;
                    case "category":
                        Collections.sort(menuItems, Comparator.comparing(MenuItem::getCategory));  // Sorting by category
                        break;
                    // No default handling, keep it simple
                }
            }

            // Searching through items... check if search query exists
            String searchQuery = request.getParameter("search");
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                menuItems.removeIf(item -> !item.getName().toLowerCase().contains(searchQuery.toLowerCase()));  // Remove if name doesn't match search
            }

            // Set the list of items to the request
            request.setAttribute("menuItems", menuItems);

            // Send to menu.jsp, hopefully it'll work
            request.getRequestDispatcher("/menu.jsp").forward(request, response);

        } catch (SQLException e) {
            // If there’s a DB issue, just throw an error
            throw new ServletException("DB problem while fetching menu items", e);
        }
    }

    /**
     * POST handler just calls GET, whatever
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);  // Reusing GET code, lazy way but works
    }
}
