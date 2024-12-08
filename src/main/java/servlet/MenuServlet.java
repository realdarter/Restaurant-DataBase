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
 * Servlet implementation class MenuServlet
 */
@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MenuItemDAO menuItemDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        menuItemDAO = new MenuItemDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch all menu items from the database
            List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();

            if (menuItems == null || menuItems.isEmpty()) {
                System.out.println("No menu items fetched from the database.");
            }

            // Handle sorting based on query parameter
            String sortBy = request.getParameter("sortBy");
            if (sortBy != null) {
                switch (sortBy) {
                    case "price":
                        Collections.sort(menuItems, Comparator.comparingDouble(MenuItem::getPrice));
                        break;
                    case "category":
                        Collections.sort(menuItems, Comparator.comparing(MenuItem::getCategory));
                        break;
                }
            }

            // Handle searching based on query parameter
            String searchQuery = request.getParameter("search");
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                menuItems.removeIf(item -> !item.getName().toLowerCase().contains(searchQuery.toLowerCase()));
            }

            // Pass menu items to JSP
            request.setAttribute("menuItems", menuItems);

            // Forward request to menu.jsp
            request.getRequestDispatcher("/menu.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error while retrieving menu items", e);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
