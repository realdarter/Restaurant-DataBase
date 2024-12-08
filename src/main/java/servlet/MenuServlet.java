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
